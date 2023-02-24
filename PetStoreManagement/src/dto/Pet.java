/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;
/**
 *
 * @author asus
 */
public class Pet {
    private String id;
    private String description;
    private String importDate;
    private double price;
    private String category;

    public Pet() {
        super();
    }

    public Pet(String id, String description, String importDate, double price, String category) {
        this.id = id;
        this.description = description;
        this.importDate = importDate;
        this.price = price;
        this.category = category;
    }

    Pet(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImportDate() {
        return importDate;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Pet{" + "ID=" + id + ", description=" + description + ", importDate=" + importDate + ", price=" + price + ", category=" + category + '}';
    }

    
    
            
    
}
