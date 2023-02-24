/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.Book;
import dto.I_Book;
import dto.Publisher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import utils.Utils;

/**
 *
 * @author asus
 */
public class BookList extends ArrayList<Book> implements I_Book {
    
    transient Scanner sc = new Scanner(System.in);

    private static final String Book_File = "src/file/Book.dat";
    
    @Override
    public ArrayList<Book> createBook(ArrayList<Publisher> publisher) {
        if (publisher.isEmpty()){
            System.out.println("Empty list. Please input data");
        }
        String pubID;
        String bookID, bookName, status;
        int quantity;
        double price;
        boolean check = true;
        do {
            bookID = Utils.getString("Insert Book's ID (Bxxxxx): ");
            check = this.contains(new Book(bookID));
            if (!bookID.matches("B\\d{5}")) {
                System.out.println("Invalid format!");
                check = true;
            }
        } while (check);
        System.out.println("Publisher's Data:");
        publisher.forEach((o) -> {
            System.out.println(o.toString() + "\n");
        });
        boolean checkId = true;
        //Check Publisher ID is exist or not?
        do{
            pubID = Utils.getString("Enter Publisher's ID: ");
            for(Publisher id: publisher){
                if (id.getPubID().equals(pubID)){
                    checkId = true;
                }
            }
        }while(!checkId);
        bookName = Utils.getString("Book's Name: ", 5, 30);
        price = Utils.getDouble("Book's Price: ", 0);
        quantity = Utils.getInt("Quantity: ", 0);
        status = Utils.getStatus("Book's Status: ");
        Book newData = new Book(bookID, bookName, price, quantity, status, pubID);
        this.add(newData);
        System.out.println("Success");
        boolean ask = Utils.askUser("Do you want to return Menu? (Y/N): ");
        if(!ask) createBook(publisher);
        return this;
    }

    @Override
    public void searchBook() {
        //Tao searchBook arraylist la de lay ket qua cua trong ArrayList book
        ArrayList<Book> searchBook = new ArrayList<>();
        String search = Utils.getString("Enter the Publisher's ID (or part of Book’s Name) to find a Book: ");
        if(this.isEmpty()) {
            System.out.println("Have no any Book"); return;
        }
        //Quet de tim arraylist book chua Publisher ID do
        for(Book b : this) {
            if(b.getPubID().equals(search) || b.getBookName().contains(search)) {
                searchBook.add(b);
            }
        }
        Collections.sort(searchBook, (b1, b2) -> b1.getBookName().compareToIgnoreCase(b2.getBookName()));
        System.out.println("Search results:");
        Utils.printBookList(searchBook);
        boolean ask = Utils.askUser("Do you want to return Menu? (Y/N): ");
        if(!ask) searchBook();
    }

    @Override
    public void updateBook(ArrayList<Book> book) {
        int index = 0;
        String oldName, newName;
        double oldPrice, newPrice;
        int oldQuantity, newQuantity;
        String oldStatus, newStatus;
        int statusNumber;
        this.forEach((o) -> {
            System.out.println(o.toString() + "\n");
        });
        String bookID = Utils.getString(" Enter Book's ID you want to update: ", "B\\d{5}");
        if (!this.contains(new Book(bookID))) {
            System.out.println("The Book does not exist.");
            return;
        }       
        index = this.indexOf(new Book(bookID));
        do {
            oldName = ((Book) this.get(index)).getBookName();
            newName = Utils.updateName(" Input new name: ", oldName);
        } while (newName.length() < 5 && newName.length() > 30);
        ((Book) this.get(index)).setBookName(newName);
        oldPrice = ((Book) this.get(index)).getPrice();
        newPrice = Utils.updatePrice(" Input new price: ", oldPrice);
        ((Book) this.get(index)).setPrice(newPrice);
        oldQuantity = ((Book) this.get(index)).getQuantity();
        newQuantity = Utils.updateQuantity(" Input new quantity: ", oldQuantity);
        ((Book) this.get(index)).setQuantity(newQuantity);
        oldStatus = ((Book) this.get(index)).getStatus();
        if (oldStatus.equals("Available")) {
            statusNumber = 1;
        } else {
            statusNumber = 0;
        }
        newStatus = Utils.updateStatus(" Input new status: ", statusNumber);
        ((Book) this.get(index)).setStatus(newStatus);
        String publisherID = ((Book) this.get(index)).getPubID();
        Book newData = new Book(bookID, newName, newPrice, newQuantity, newStatus, publisherID);
        this.add(newData);
        System.out.println("Success");
        boolean ask = Utils.askUser("Do you want to return Menu? (Y/N): ");
        if(!ask) updateBook(book);
    }

    @Override
    public void delBook() {
        int test = 0;
        this.forEach((o) -> {
            System.out.println(o.toString() + "\n");
        });
        String id = Utils.getString(" Enter Book's ID you want to delete: ", "B\\d{5}");
        if (!this.contains(new Book(id))) {
            System.out.println("Book's Name does not exist");
            return;
        }
        test = this.indexOf(new Book(id));
        this.remove(test);
        if (!this.contains(new Book(id))) {
            System.out.println("Success");
        } else {
            System.out.println("Error");
        }
        boolean ask = Utils.askUser("Do you want to return Menu? (Y/N): ");
        if(!ask) delBook();
    }

    @Override
    public void saveBookFile() {
       try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Book_File))) {
            oos.writeObject(this);
            System.out.println("Success");
        } catch (FileNotFoundException ex) {
            System.out.println("Error: "+ex.getMessage());
        } catch (IOException ex) {
             System.out.println("Error: "+ex.getMessage());
        }
        boolean ask = Utils.askUser("Do you want to return Menu? (Y/N): ");
        if(!ask) saveBookFile();
    }
    
    @Override
    public void printBookFile() {
        PublisherList pub = new PublisherList();
        File file = new File(Book_File);
        if (!file.canWrite() || !file.exists()) {
            System.out.println("Error to print from File.");
        } else {
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                BookList temp = (BookList) ois.readObject();
                //Sort by Book's Quantity descending
                Collections.sort(temp, new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        return o2.getQuantity() - o1.getQuantity();
                    }
                });
                Utils.displayBook(temp, pub);
                this.addAll(temp);
            } catch (FileNotFoundException ex) {
                System.out.println("[!] Failed to print files at " + Book_File + " due to exception " + ex);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("[!] Failed to print files at " + Book_File + " due to exception " + ex);
            }

        }
        boolean ask = Utils.askUser("Do you want to return Menu? (Y/N): ");
        if(!ask) printBookFile();
    }

}
