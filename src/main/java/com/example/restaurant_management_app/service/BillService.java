package com.example.restaurant_management_app.service;


import com.example.restaurant_management_app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurant_management_app.repository.BillRepository;
import com.example.restaurant_management_app.repository.CustomerRepository;
import com.example.restaurant_management_app.repository.MenuRepository;
import com.example.restaurant_management_app.repository.OrderRepository;

import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MenuRepository menuItemRepository;
    private final TableService tableService;
    private final OrderService orderService;
    @Autowired
    public BillService(BillRepository billRepository, OrderRepository orderRepository,
                       CustomerRepository customerRepository,
                       MenuRepository menuItemRepository, TableService tableService,
                        OrderService orderService) {
        this.billRepository = billRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.tableService=tableService;
        this.orderService=orderService;
    }


    public Bill generateBill(String orderId){
        Order order=orderRepository.findById(orderId);
        if(order==null){
            throw new IllegalArgumentException("Order not found!");
        }
        double subtotal=0.0;
        if(order.getOrderLines() != null){
            for(OrderLine line : order.getOrderLines()){
                MenuItem item= menuItemRepository.findById(line.getMenuItemId());
                if(item != null){
                    subtotal += (item.getPrice()*line.getQuantity());
                }
            }
        }
        double discount=0.0;
        if(order.getCustomerId() != null){
            Customer customer= customerRepository.findById(order.getCustomerId());
            if ( customer!=null && customer.getDiscount()>0){
                discount= customer.getDiscount();
            }
        }

        double total= subtotal-((discount/100)*subtotal);
        Bill bill= new Bill(orderId,total);
        return billRepository.save(bill);
    }



    public Order payBill(String orderId){
        if(orderId==null){
            throw new IllegalArgumentException("OrderId null!");
        }

       return orderService.closeOrder(orderId);

    }
//cand generazi un bill sa se inchida si order cu verificare, Daca order e deja inchis sa apara un pop up. (in close order facut)


    public Bill getBillById(String id){
        return billRepository.findById(id);
    }

    public List<Bill> getAllBills(){
        return billRepository.findAll();
    }

    public Bill deleteBill(String billId){
        if(billId == null)
            throw new IllegalArgumentException("Id null!");

        Bill bill = billRepository.findById(billId);
        if(bill == null)
            throw new IllegalArgumentException("Invalid id!");

        billRepository.delete(bill);
        return bill;
    }

    public Bill update(Bill bill){
        Order orderVerify= orderRepository.findById(bill.getOrderId());
        if(orderVerify.getStatus() == Order.Status.CLOSED){
            throw new IllegalArgumentException("Cannot assign bill to a closed Order ");
        }

        for(Bill oneBill : billRepository.findAll()){
            if(oneBill.getOrderId().equals(bill.getOrderId())){
                throw new IllegalArgumentException("Cannot assign the bill to an order that's already assigned to another bill!");
            }
        }

        for(Order order: orderRepository.findAll()){
            if(order.getId().equals(bill.getOrderId()) && order.getStatus()!= Order.Status.CLOSED){
                Bill temporaryBill= generateBill(order.getId());
                bill.setTotalAmount(temporaryBill.getTotalAmount());
                deleteBill(temporaryBill.getId());
            }
        }

        billRepository.save(bill);
        return bill;
    }
}
