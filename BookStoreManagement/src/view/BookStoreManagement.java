/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.BookList;
import controller.Menu;
import controller.PublisherList;
import dto.Book;
import dto.I_Book;
import dto.I_Menu;
import dto.I_Publisher;
import dto.Publisher;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class BookStoreManagement {
    public static void main(String[] args) {
        I_Menu mainmenu = new Menu();
        I_Menu pubmenu = new Menu();
        I_Menu bookmenu = new Menu();
        I_Book book = new BookList();
        I_Publisher pub = new PublisherList();
        ArrayList<Publisher> pubArrl = new ArrayList<>();
        ArrayList<Book> bookList = new ArrayList<>();
        mainmenu.addItem("BOOK_STRORE_MANAGEMENT");
        mainmenu.addItem("1. Publisher Management");
        mainmenu.addItem("2. Book Management");
        mainmenu.addItem("3. Quit");
        int mainchoice;
        boolean maincheck = true;
        do {
            mainmenu.showMenu();
            mainchoice = mainmenu.getChoice(1,3);
            switch(mainchoice){
                case 1: pubmenu.addItem("Publisher Management");
                        pubmenu.addItem("1. Create Publisher");
                        pubmenu.addItem("2. Delete Publisher");
                        pubmenu.addItem("3. Save the Publishers list to file");
                        pubmenu.addItem("4. Print the Publisher list from the file");
                        pubmenu.addItem("5. Quit");
                        int pubchoice;
                        boolean pubcheck = true;
                        do{
                            pubmenu.showMenu();
                            pubchoice = pubmenu.getChoice(1, 5);
                            switch(pubchoice){
                                case 1: pubArrl = pub.createPublisher();
                                        break;
                                case 2: pub.delPublisher();
                                        break;
                                case 3: pub.savePubFile();
                                        break;
                                case 4: pub.printPubFile();
                                        break;
                                case 5: pubcheck = pubmenu.confirmYesNo("Are you sure?(Y/N) ");
                                        break;
                            }
                        }while(pubchoice >= 1 && pubchoice < 5 && pubcheck);
                        break;
                case 2: bookmenu.addItem("Book Management");
                        bookmenu.addItem("1. Create a Book");
                        bookmenu.addItem("2. Search the Book ");
                        bookmenu.addItem("3. Update a Book");
                        bookmenu.addItem("4. Delete the Book");
                        bookmenu.addItem("5. Save the Books list to file");
                        bookmenu.addItem("6. Print the Books list from the file");
                        bookmenu.addItem("7. Quit");
                        int bookchoice;
                        boolean bookcheck = true;
                        do{
                            bookmenu.showMenu();
                            bookchoice = bookmenu.getChoice(1, 7);
                            switch(bookchoice){
                                case 1: bookList = book.createBook(pubArrl);
                                        break;
                                case 2: book.searchBook();
                                        break;
                                case 3: book.updateBook(bookList);
                                        break;
                                case 4: book.delBook();
                                        break;
                                case 5: book.saveBookFile();
                                        break;
                                case 6: book.printBookFile();
                                        break;
                                case 7: bookcheck = bookmenu.confirmYesNo("Are you sure?(Y/N) ");
                                        break;
                            }
                        }while(bookchoice >= 1 && bookchoice < 7 && bookcheck);;
                case 3: maincheck = mainmenu.confirmYesNo("Are you sure? (Y/N): ");
                        break;
            }
            
            
        }while(mainchoice >= 1 && mainchoice <3 && maincheck);
    }   
}
