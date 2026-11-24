package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("mysql")
interface JpaBillRepository extends JpaRepository<Bill, String> {
    //generează automat tot codul SQL în spate la pornirea aplicației.
    //neaparat private!
}

@Repository
public class BillRepository implements IRepository<Bill>{
    private final IRepository<Bill>  repository;
                                 // numele proprietatii pe care o cauta file/memory/db
    public BillRepository(@Value("${spring.profiles.active:file}") String activeProfile, //VALUE-> cauta in app properties
                          @Autowired(required = false) JpaBillRepository jpaBillRepository) {//
        if ("memory".equalsIgnoreCase(activeProfile)){
            this.repository= new InMemoryRepository<Bill>(){
                @Override
                public String getId(Bill bill){
                    return bill.getId();
                }
            };
        } else if("mysql".equalsIgnoreCase(activeProfile)){
            this.repository=new MySQLRepository<Bill>(jpaBillRepository) {
                @Override
                public String getId(Bill bill){
                    return bill.getId();
                }
            };
        }else{
            this.repository=new InFileRepository<Bill>("src/main/resources/data/bills.json",Bill.class) {
                @Override
                public String getId(Bill bill) {
                    return bill.getId();
                }
            };
        }



    }


    @Override
    public Bill save(Bill bill) {
        return repository.save(bill);
    }

    @Override
    public List<Bill> findAll() {
        return repository.findAll();
    }

    @Override
    public Bill findById(String id) {
        return  repository.findById(id);
    }

    @Override
    public Boolean delete(Bill bill) {
        return  repository.delete(bill);
    }
}
