package com.example.restaurant_management_app.service;
import com.example.restaurant_management_app.model.*;
import com.example.restaurant_management_app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {
    private final OrderRepository orderRepository ;
    private final CustomerRepository customerRepository ;
    private final TableRepository tableRepository ;
    private final TableService tableService;
    private final MenuRepository menuItemRepository ;
    private final StaffRepository staffRepository ;
    private final StaffService staffService;


    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        TableRepository tableRepository, TableService tableService, MenuRepository menuItemRepository,
                        StaffRepository staffRepository, StaffService staffService) {
        this.orderRepository = orderRepository;
        this.customerRepository=customerRepository;
        this.tableRepository= tableRepository;
        this.tableService=tableService;
        this.menuItemRepository=menuItemRepository;
        this.staffRepository=staffRepository;
        this.staffService=staffService;
    }




    public Order createOrder(String customerId, String tableId, boolean takeAway){
        if(customerId==null || customerRepository.findById(customerId)==null){
            throw new IllegalArgumentException("Invalid customer!");
        }

        if(!takeAway){
            if(tableId==null || tableRepository.findById(tableId)==null){
                throw new IllegalArgumentException("Invalid table!");
            }
            tableService.occupyTable(tableId);
        }
        else{
            tableId=null;
        }

        Order order= new Order(customerId,tableId,takeAway);

        //customerRepository.assignOrder(customerId,order);
        Customer customer= customerRepository.findById(customerId);
        customer.addOrders(order);
        customerRepository.save(customer);

        if(!takeAway) {
            //tableRepository.assignOrder(tableId, order);
            Table table= tableRepository.findById(tableId);
            table.addOrders(order);
            tableRepository.save(table);
        }
        return orderRepository.save(order);

    }

    public Order addOrderLine(String orderId, String menuItemId, double quantity){

        if(quantity<0){
            throw new IllegalArgumentException("Invalid quantity!");
        }

        Order order= orderRepository.findById(orderId);
        if(order==null){
            throw new IllegalArgumentException("Invalid Order!");
        }
        if(!Order.Status.OPEN.equals(order.getStatus())){
                throw new IllegalStateException("Order not opened, cannot add Item!");
        }


        MenuItem menuItem= menuItemRepository.findById(menuItemId);
        if(menuItem==null){
            throw new IllegalArgumentException("Invalid Menu Item!");
        }


        if (order.getOrderLines()==null){
            order. setOrderLines(new ArrayList<>());
        }

        boolean itemExists=false; //produsul exista deja, schimb doar cantitatea
        for(OrderLine line : order.getOrderLines()){
            if (menuItemId.equals(line.getMenuItemId())){
                line.setQuantity(quantity);
                itemExists=true;
                break;
            }
        }

        if(!itemExists){//prima data cand adaug produsul
            OrderLine orderLine= new OrderLine(menuItemId,quantity);
            order.getOrderLines().add(orderLine); //imi adaug in arrayul de orderlines produsul nou
        }

        return orderRepository.save(order);

    }

    public Order assignStaff(String orderId, String staffId){
        Order order= orderRepository.findById(orderId);
        if(order==null){
            throw new IllegalArgumentException("Invalid Order!");
        }

        Staff staff = staffRepository.findById(staffId);
        if(staff==null){
            throw new IllegalArgumentException("Invalid staff memeber!");
        }

        if(order.getAssignments()==null){
            order.setAssignments(new ArrayList<>());
        }

        OrderAssignment orderAssignment= new OrderAssignment(orderId,staffId);
        order.getAssignments().add(orderAssignment);

        staffService.changeStatus(staffId,Staff.Status.BUSY);

        return orderRepository.save(order);
    }


    public Order closeOrder(String orderId){
        Order order= orderRepository.findById(orderId);
        if(order==null){
            throw new IllegalArgumentException("Invalid Order!");
        }

        order.setStatus(Order.Status.CLOSED);

        if(!order.getTakeAway() &&  order.getTableId() != null){
           tableService.releaseTable(order.getTableId());
        }

        return orderRepository.save(order);
    }



    public Order getOrderById(String orderId){
        return orderRepository.findById(orderId);
    }


    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }



    public Order deleteOrder(String orderId){
        if(orderId == null)
            throw new IllegalArgumentException("Id null!");

        Order order = orderRepository.findById(orderId);
        if(order == null)
            throw new IllegalArgumentException("Invalid id!");

        orderRepository.delete(order);

        return order;
    }

    public Order update(Order order){
        orderRepository.save(order);
        return order;
    }

}
