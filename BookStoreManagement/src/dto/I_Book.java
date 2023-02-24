/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author asus
 */
public interface I_Book {
    ArrayList<Book> createBook(ArrayList<Publisher> pub);
    void searchBook();
    void updateBook(ArrayList<Book> book);
    void delBook();
    void saveBookFile();
    void printBookFile();
}
