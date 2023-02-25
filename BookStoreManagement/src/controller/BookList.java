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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import utils.Controller;

/**
 *
 * @author asus
 */
public class BookList extends ArrayList<Book> implements I_Book, Serializable {

    transient Scanner sc = new Scanner(System.in);

    private static final String Book_File = "src/file/Book.dat";

    @Override
    public ArrayList<Book> createBook(ArrayList<Publisher> publisher) {
        PublisherList pub = new PublisherList();
        String pubID;
        String bookID, bookName, status;
        int quantity;
        double price;
        boolean check = true;
        do {
            bookID = Controller.getString("Insert Book's ID (Bxxxxx): ");
            check = this.contains(new Book(bookID));
            if (!bookID.matches("B\\d{5}")) {
                System.out.println("Invalid format!");
                check = true;
            }
        } while (check);
        System.out.println("Publisher's Data: ");
        publisher = pub.printPubFile();
        boolean checkId = true;
        //Check Publisher ID is exist or not?
        do {
            pubID = Controller.getString("Enter Publisher's ID: ");
            for (Publisher id : publisher) {
                if (id.getPubID().equals(pubID)) {
                    checkId = true;
                }
            }
        } while (!checkId);
        bookName = Controller.getStringMinMax("Book's Name: ", 5, 30);
        price = Controller.getDouble("Book's Price: ", 0);
        quantity = Controller.getInt("Quantity: ", 0);
        status = Controller.getBookStatus("Book's Status: ");
        Book newData = new Book(bookID, bookName, price, quantity, status, pubID);
        this.add(newData);
        System.out.println("Add Success");
        boolean ask = Controller.askUser("Do you want to return Menu? (Y/N): ");
        if (!ask) {
            createBook(publisher);
        }
        return this;
    }

    @Override
    public void searchBook() {
        //Tao searchBook arraylist la de lay ket qua cua trong ArrayList book
        ArrayList<Book> searchBook = new ArrayList<>();
        String search = Controller.getString("Enter the Publisher's ID (or part of Book’s Name) to find a Book: ");
        if (this.isEmpty()) {
            System.out.println("Have no any Book");
            return;
        }
        //Quet de tim arraylist book chua Publisher ID do
        for (Book b : this) {
            if (b.getPubID().equals(search) || b.getBookName().contains(search)) {
                searchBook.add(b);
            }
        }
        Collections.sort(searchBook, (b1, b2) -> b1.getBookName().compareToIgnoreCase(b2.getBookName()));
        System.out.println("Search results:");
        Controller.printBookList(searchBook);
        boolean ask = Controller.askUser("Do you want to return Menu? (Y/N): ");
        if (!ask) {
            searchBook();
        }
    }

    @Override
    public void updateBook() {
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }
        printBook();
        String bookID;
        boolean check = true;
        bookID = Controller.getString("Choice the ID you want to update: ");
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBookID().equals(bookID)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Book does not exist");
            return;
        }
        //Update Book's Name
        String bookName = this.get(index).getBookName();
        String newName = Controller.getString("Enter new Book's name: ");
        if (newName == null) {
            newName = bookName;
        } else if (newName.length() < 5 || newName.length() > 30) {
            return;
        }
        this.get(index).setBookName(newName);
        //Update Book's Price
        Double priceBook = this.get(index).getPrice();
        Double newPrice = Controller.getDouble("New Book's price: ", 0);
        if (newPrice == null) {
            newPrice = priceBook;
        }
        this.get(index).setPrice(newPrice);
        //Update Book's Quantity
        int bookQuantity = this.get(index).getQuantity();
        int newQuantity = Controller.getInt("New quantity of Book: ", 0);
        if (newQuantity == 0) {
            newQuantity = bookQuantity;
        }
        this.get(index).setQuantity(newQuantity);
        //Update Book's status
        String bookStatus = this.get(index).getStatus();
        String newStatus = Controller.getBookStatus("Enter new Book's status: ");
        if (newStatus == null) {
            newStatus = bookStatus;
        }
        this.get(index).setStatus(newStatus);
        System.out.println("Update Success");
    }

    @Override
    public void delBook() {
        this.forEach((o) -> {
            System.out.println(o.toString() + "\n");
        });
        String bookID = Controller.getString("Choice the ID you want to delete: ");
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBookID().equals(bookID)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Book does not exist");
            return;
        }
        this.remove(index);
        System.out.println("Delete Success");
        boolean ask = Controller.askUser("Do you want to return Menu? (Y/N): ");
        if (!ask) {
            delBook();
        }
    }

    @Override
    public void saveBookFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Book_File))) {
            oos.writeObject(this);
            System.out.println("Save Success");
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        boolean ask = Controller.askUser("Do you want to return Menu? (Y/N): ");
        if (!ask) {
            saveBookFile();
        }
    }

    @Override
    public ArrayList<Book> printBookFile(ArrayList<Publisher> publisher) {
        File file = new File(Book_File);
        if (!file.canWrite() || !file.exists()) {
            System.out.println("Error to print from File.");
            return null;
        }
        try {
            FileInputStream fis = new FileInputStream(Book_File);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Book> temp = (ArrayList<Book>) ois.readObject();
            //Sort by Book's Quantity descending
            Collections.sort(temp, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o2.getQuantity() - o1.getQuantity();
                }
            });
            Controller.displayBook(temp, publisher);
            this.addAll(temp);
        } catch (FileNotFoundException ex) {
            System.out.println("Err: " + ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Err: " + ex);
        }
        boolean ask = Controller.askUser("Do you want to return Menu? (Y/N): ");
        if (!ask) {
            printBookFile(publisher);
        }
        return this;
    }

    private void printBook() {
        for (Book o : this) {
            System.out.println(o.toString() + "\n");
        }

    }
}
