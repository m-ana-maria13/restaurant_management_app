package com.example.restaurant_management_app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

        public enum Status{
            OPEN, CLOSED
        }
       // private static int counter=1;
        private String id;
        private String customerId;
        private String tableId;
        private Status status; //open/closed
        private List<OrderLine> orderLines;
        private List<OrderAssignment> assignments;
        private Boolean takeAway; // daca e takeaway, atunci nu se mai ocupa masa, ! nu se face delivery, vine clientul

        public Order(String customerId, String tableId, boolean takeAway) {
          //  this.id = String.valueOf(counter);
           // counter++;
            this.id= UUID.randomUUID().toString();
            this.customerId = customerId;
            this.tableId = tableId;
            this.status = Status.OPEN;
            this.takeAway= takeAway;
            this.orderLines = new ArrayList<OrderLine>();
            this.assignments = new ArrayList<OrderAssignment>();

        }

        public Order(){
            this.orderLines = new ArrayList<>();
            this.assignments = new ArrayList<>();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getTableId() {
            return tableId;
        }

        public void setTableId(String tableId) {
            this.tableId = tableId;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public List<OrderLine> getOrderLines() {
            return orderLines;
        }

        public void setOrderLines(List<OrderLine> orderLines) {
            this.orderLines = orderLines;
        }

        public List<OrderAssignment> getAssignments() {
            return assignments;
        }

        public void setAssignments(List<OrderAssignment> assignments) {
            this.assignments = assignments;
        }

        public Boolean getTakeAway() {
            return takeAway;
        }

        public void setTakeAway(Boolean takeAway) {
            this.takeAway = takeAway;
        }
    }

