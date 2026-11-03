package com.example.restaurant_management_app.service;

import com.example.restaurant_management_app.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurant_management_app.repository.TableRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {
    private final TableRepository tableRepository;

    @Autowired
    TableService(TableRepository tables){
        this.tableRepository = tables;
    }

    public Table occupyTable(String tableId) {
        Table table = existingTable(tableId);

        if ((table.getOccupiedStatus()).equals(Table.Status.OCCUPIED)) {
            throw new IllegalStateException("Table already occupied!");
        }

        table.setOccupiedStatus(Table.Status.OCCUPIED);
        return tableRepository.save(table);
    }

    public Table releaseTable(String tableId) {
        Table table = existingTable(tableId);

        table.setOccupiedStatus(Table.Status.FREE);
        return tableRepository.save(table);
    }

    public Table getTableByNumber(int number) {
        return tableRepository.findByNumber(number);
    }

    public Table getTableById(String id){
        return tableRepository.findById(id);
    }

    public List<Table> getAllTables(){
        return tableRepository.findAll();
    }



    public List<Table> getAvailableTables() {
        List<Table> available = new ArrayList<>();
        for (Table table : tableRepository.findAll()) {
            if ((table.getOccupiedStatus()).equals(Table.Status.FREE)) {
                available.add(table);
            }
        }
        return available;
    }

    private Table existingTable(String id) {
        Table table = tableRepository.findById(id);
        if (table == null) {
            throw new IllegalArgumentException("Id not valid!");
        }
        return table;
    }
}
