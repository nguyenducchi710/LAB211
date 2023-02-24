/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author asus
 */
public class Order {
    //Order Header
    private String orderID;
    private String orderDate;
    private String customerName;
    
    //Order Details
    private String orderDetailID;
    private String petID;
    private int quantity;
    private int cost;
    
    public Order() {
        
    }

    public Order(String orderID, String orderDate, String customerName, String orderDetailID, String petID, int quantity, int cost) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.orderDetailID = orderDetailID;
        this.petID = petID;
        this.quantity = quantity;
        this.cost = cost;
    }

    public Order(String orderID, String date, String name, String petID, int quantity, int cost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
       return "Order{" + "orderID=" + orderID + ", orderDate=" + orderDate + ", customerName=" + customerName + ", petID=" + petID + ", quantity=" + quantity + ", cost=" + cost + '}';
        
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(getOrderDate());
    }
}