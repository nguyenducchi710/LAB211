/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author asus
 */
public class Book implements Serializable{
    private String bookID;
    private String bookName;
    private double price;
    private int quantity;
    private String status;
    private String pubID;

    public Book() {
        super();
    }
    
    public Book(String id) {
        this.bookID = id;
    }


    public Book(String bookID, String bookName, double price, int quantity, String status, String pubID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.pubID = pubID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPubID() {
        return pubID;
    }

    public void setPubID(String pubID) {
        this.pubID = pubID;
    }

    @Override
    public String toString() {
        return "Book {" + "Book's ID : " + bookID + ", Book's Name : " + bookName + ", Price : " + price + ", Quantity : " + quantity + ", Status : " + status + '}';
    }
    
}
