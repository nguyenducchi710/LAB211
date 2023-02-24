/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dto.Order;
import dto.Pet;
import java.io.File;
import utils.Utils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import dto.I_PetList;

/**
 *
 * @author asus
 */
public class PetList extends HashMap<String, Pet> implements I_PetList {

    final String filePet = "src/petstore/output/pets.dat";
    final String fileOrder = "src/petstore/output/orders.dat";

    private final transient Scanner sc = new Scanner(System.in);
    private HashMap<String, Order> order;

    public PetList() {
        // Initialize the Order with an empty map
        order = new HashMap<>();
    }

    @Override
    public void addPet() {
        String id, description, date, category;
        int price;
        boolean check;
        do {
            id = Utils.getString("Input ID: ");
            check = this.containsKey(id);

        } while (check);
        description = Utils.getString("Input description: ", 3, 50);
        date = Utils.inputDate("Input date: ");
        price = Utils.getPrice("Input price: ");
        category = Utils.getCategory("Input category (Dog/Cat/Parrot): ");
        Pet pet = new Pet(id, description, date, price, category);
        this.put(id, pet);
        askContinue();
    }

    @Override
    public void findPet() {
        if (this.isEmpty()) {
            System.out.println("The store is empty!!!");
            return;
        }
        System.out.println("Enter the Pet's ID: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        if (this.containsKey(id)) {
            this.get(id).toString();
        } else {
            System.out.println("The pet does not exist");
        }
    }

    @Override
    public void updatePet() {
        if (this.isEmpty()) {
            System.out.println("The store is empty!!!");
            return;
        }
        System.out.println("Enter the Pet's ID: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        if (this.containsKey(id)) {
            double price;
            String importDate, category, description;
            description = Utils.getString(" Update description: ", 3, 50);
            price = Utils.getPrice(" Update price: ");
            importDate = Utils.inputDate(" Update date [DD/MM/YYYY]: ");
            category = Utils.getCategory(" Update category [Cat, Dog, Parrot]: ");
            Pet pet = new Pet(id, description, importDate, price, category);
            this.put(id, pet);
            System.out.print("-Update Success-\n");
        } else {
            System.out.println("The pet does not exist");
        }
    }

    @Override
    public void deletePet() {
        if (this.isEmpty()) {
            System.out.println("The store is empty!!!");
            return;
        }
        Menu m = new Menu();
        System.out.println("Enter the Pet's ID: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        boolean confirm;
        if (this.containsKey(id)) {
            confirm = m.confirmYesNo("Are you sure to delete this pet (Y/N): ");
            if (confirm) {
                this.remove(id);
            }
        } else {
            System.out.println("The pet does not exist");
        }
    }

    public void askContinue() {

        System.out.println("Do you want to continue this job?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice;
        System.out.print("Choice: ");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                addPet();
                break;
            case 2:
                break;
        }

    }

    @Override
    public void addOrder() {
        if (this.isEmpty()) {
            System.out.println("The store is empty!!!");
            return;
        }
        String orderID, date, name, petID;
        int price;
        int quantity = 0, cost;
        boolean check = true;
        System.out.println("The pets in store:");
        this.forEach((k, v) -> System.out.println(k + ": " + v));
        do {
            orderID = Utils.getString("Input new Order ID: ");
            check = this.containsKey(orderID);
        } while (check);
        name = Utils.getString("Input customer's name: ");
        date = Utils.inputDate("Input the order's date (DD/MM/YYYY): ");
        petID = Utils.getString("Input the pet ID: ");
        check = this.containsKey(petID);
        if (check) {
            price = (int) this.get(petID).getPrice();
            cost = quantity * price;
            Order allOrder = new Order(orderID, date, name, petID, quantity, cost);
            order.put(petID, allOrder);
        } else {
            System.out.println("The pet does not exist");
        }
        askContinue();
    }

    @Override
    public void listOrder() {
        int totalPet = 0, totalOrder = 0;
        boolean found = false;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Input start and end date to list orders in range.");
        try {
            String startDate = Utils.getString(" Enter the start date: ");
            String endDate = Utils.getString(" Enter the end date: ");
            Date start = formatter.parse(startDate);
            Date end = formatter.parse(endDate);
            System.out.format("| %-9s| %-13s| %-17s| %-10s| %-12s|\n", "Order Id", "Order Date", "Customer", "Pet Count", "Order Total");
            for (Order o : this.order.values()) {
                Date orderDate = formatter.parse(o.getOrderDate());
                if (start.before(orderDate) && end.after(orderDate)) {
                    totalPet += o.getQuantity();
                    totalOrder += o.getCost();
                    System.out.format("| %-9s| %-13s| %-17s| %-10s| %-12s|\n", o.getOrderDetailID(), o.getOrderDate(), o.getCustomerName(), o.getQuantity(), o.getCost());
                    found = true;
                }
            }
            System.out.format("| %-9s| %-13s| %-17s| %-10s| %-12s|\n", " Total", " ", " ", totalPet, totalOrder);
            if (!found) {
                System.out.println("[!] There is no order in specified date range.");
            }
        } catch (ParseException ex) {
            System.out.println("[!] Invalid date!");
            System.out.println(ex);
        }
    }

    @Override
    public void sortOrder() {
        if (order.isEmpty()) {
            System.out.print("[!] Collection of orders is empty.");
        } else {
            System.out.println("LIST OF ORDERS\n");
            System.out.print("Sort by (Date, Code, Name, Total): ");
            String sortBy = sc.nextLine();

            this.sortOrder(sortBy);
        }
    }

    public void sortOrder(String sortBy) {
        //Sort ascending orders by default, except
        //User specifically input 'DESC' to sort descending orders 
        System.out.print("Sort order (or DESC): ");
        String sortOrder = sc.nextLine();

        switch (sortBy.toLowerCase()) {
            case "date":
                order = this.sortDate(sortOrder);
                break;
            case "code":
                order = this.sortByKey(sortOrder);
                break;
            case "name":
                order = this.sortByName(sortOrder);
                break;
            case "total":
                order = this.sortByTotal(sortOrder);
                break;
            default:
                return;
        }

        this.outputOrder();
    }

    public void outputOrder() {
        int totalPet = 0, totalOrder = 0;
        System.out.format("| %-9s| %-13s| %-17s| %-10s| %-12s|\n", "Order Id", "Order Date", "Customer", "Pet Count", "Order Total");
        for (Order o : this.order.values()) {
            totalPet += o.getQuantity();
            totalOrder += o.getCost();
            System.out.format("| %-9s| %-13s| %-17s| %-10s| %-12s|\n", o.getOrderDetailID(), o.getOrderDate(), o.getCustomerName(), o.getQuantity(), o.getCost());
        }
        System.out.format("| %-9s| %-13s| %-17s| %-10s| %-12s|\n", " Total", " ", " ", totalPet, totalOrder);
    }

    public HashMap<String, Order> sortDate(String sortOrder) {
        // Convert the HashMap to a List of Map.Entry objects
        List<Map.Entry<String, Order>> list = new LinkedList<>(order.entrySet());

        // Sort the list based on the "Date" field in each Order object
        Collections.sort(list, (o1, o2) -> {
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = o1.getValue().getDate();
                date2 = o2.getValue().getDate();
            } catch (ParseException ex) {
                System.out.println("[!] Error.");
                System.out.println(ex);
            }
            int result = date1.compareTo(date2);

            // If sortOrder is "DESC", reverse the result
            if ("desc".equalsIgnoreCase(sortOrder)) {
                result = -result;
            }

            return result;
        });

        // Convert the sorted list back to a HashMap
        HashMap<String, Order> sortedOrder = new LinkedHashMap<>();
        for (Map.Entry<String, Order> entry : list) {
            sortedOrder.put(entry.getKey(), entry.getValue());
        }

        return sortedOrder;
    }

    public HashMap<String, Order> sortByKey(String sortOrder) {
        // Convert the HashMap to a List of Map.Entry objects
        List<Map.Entry<String, Order>> list = new LinkedList<>(order.entrySet());

        // Sort the list based on the Key (i.e. the id in the Order hashmap)
        Collections.sort(list, (o1, o2) -> {
            int result = o1.getKey().compareTo(o2.getKey());

            // If sortOrder is "DESC", reverse the result
            if ("desc".equalsIgnoreCase(sortOrder)) {
                result = -result;
            }

            return result;
        });

        // Convert the sorted list back to a HashMap
        HashMap<String, Order> sortedOrder = new LinkedHashMap<>();
        for (Map.Entry<String, Order> entry : list) {
            sortedOrder.put(entry.getKey(), entry.getValue());
        }

        return sortedOrder;
    }

    public HashMap<String, Order> sortByName(String sortOrder) {
        List<Map.Entry<String, Order>> list = new LinkedList<>(order.entrySet());

        Collections.sort(list, (o1, o2) -> {
            String name1 = o1.getValue().getCustomerName();
            String name2 = o2.getValue().getCustomerName();
            int result = name1.compareTo(name2);

            if ("desc".equalsIgnoreCase(sortOrder)) {
                result = -result;
            }
            return result;
        });

        HashMap<String, Order> sortedOrder = new LinkedHashMap<>();
        for (Map.Entry<String, Order> entry : list) {
            sortedOrder.put(entry.getKey(), entry.getValue());
        }
        return sortedOrder;
    }

    public HashMap<String, Order> sortByTotal(String sortOrder) {
        List<Map.Entry<String, Order>> list = new LinkedList<>(order.entrySet());

        Collections.sort(list, (o1, o2) -> {
            int total1 = o1.getValue().getCost();
            int total2 = o2.getValue().getCost();
            int result = Integer.compare(total1, total2);

            if ("desc".equalsIgnoreCase(sortOrder)) {
                result = -result;
            }
            return result;
        });

        HashMap<String, Order> sortedOrder = new LinkedHashMap<>();
        for (Map.Entry<String, Order> entry : list) {
            sortedOrder.put(entry.getKey(), entry.getValue());
        }
        return sortedOrder;
    }

    @Override
    public void saveData() {
        File file1 = new File(filePet);
        if (file1.exists() && file1.length() > 0) {
            System.out.println("The file already contains data.");
            System.out.println("Do you want to override the save? (Y/N): ");
            String choice = sc.nextLine();
            if (!"Y".equalsIgnoreCase(choice)) {
                System.out.println("Cancel!");
                return;
            }

        }
        try {
            FileOutputStream fos = new FileOutputStream(filePet);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            System.out.println("Saved pets at\n" + filePet);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Unsuccess");
            System.out.println(e);
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(fileOrder);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(order);
            System.out.println("Saved orders at\n" + fileOrder);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Unsuccess");
            System.out.println(e);
        }
    }

    @Override
    public void loadData() {
        HashMap<String, Pet> pets = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(filePet);
            ObjectInputStream ois = new ObjectInputStream(fis);
            pets = (HashMap<String, Pet>) ois.readObject();
            System.out.println("Success");
            this.putAll(pets);
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Unsuccess");
            System.out.println(e);
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(fileOrder);
            ObjectInputStream ois = new ObjectInputStream(fis);
            order = (HashMap<String, Order>) ois.readObject();
            System.out.println("Success");
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Unsuccess");
            System.out.println(e);
        }
    }
}
