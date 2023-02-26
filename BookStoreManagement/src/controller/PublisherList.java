/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.I_Publisher;
import dto.Publisher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import utils.Controller;

/**
 *
 * @author asus
 */
public class PublisherList extends ArrayList<Publisher> implements I_Publisher, Serializable {

    public PublisherList() {
        super();
    } 
       
    private static final String Pub_File = "src/file/Publisher.dat";
    
    @Override
    public ArrayList<Publisher> createPublisher() {
        String ID, pubName, phoneNumber;
        boolean check = true;
        System.out.println("Enter the information: ");
        do {
            ID = Controller.getString("Publisher's ID (Pxxxxx): ");
            check = this.contains(ID);
            if (!ID.matches("P\\d{5}")) {
                System.out.println("Invalid format");
                check = true;
            }
        }
        while (check);
        pubName = Controller.getStringMinMax("Publisher's Name: ", 5, 30);
        phoneNumber = Controller.getStringMinMax("Publisher's phone number: ", 10, 12);
        Publisher data = new Publisher(ID, pubName, phoneNumber);
        this.add(data);
        System.out.println("Success");
        this.forEach((o) -> {System.out.println(o.toString() + "\n");});
        boolean ask = Controller.askUser("Do you want to return Menu? (Y/N): ");
        if(!ask) createPublisher();
        return this;
    }
    
    @Override 
    public void delPublisher(){
        this.forEach((o) -> {System.out.println(o.toString() + "\n");});
        String ID = Controller.getString("Choice the ID you want to delete: ");
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getPubID().equals(ID)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Publisher’s Id does not exist");
            return;
        }
        this.remove(index);
        System.out.println("Success!");  
        boolean ask = Controller.askUser("Do you want to return Menu? (Y/N): ");
        if(!ask) delPublisher();
    }
    
    @Override
    public void savePubFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Pub_File))) {
            oos.writeObject(this);
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        boolean ask = Controller.askUser("Do you want to return Menu? (Y/N): ");
        if(!ask) savePubFile();
    }
    
    @Override
    public ArrayList<Publisher> printPubFile() {
        try {
            File file = new File(Pub_File);
            if (!file.exists() || !file.canRead()) {
                System.out.println("Failed to read file at "+Pub_File); return null;
            }
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            PublisherList temp = (PublisherList) ois.readObject();
            this.addAll(temp);
            Collections.sort(temp, Comparator.comparing(Publisher::getPubName));
            Controller.displayPublisher(temp);
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: "+e.getMessage());
        }
        return this;
    }     
}
