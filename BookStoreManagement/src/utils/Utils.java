/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controller.BookList;
import controller.PublisherList;
import dto.Book;
import dto.Publisher;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class Utils {

    public static Scanner sc = new Scanner(System.in);

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static double getDouble(String welcome, int min) {
        boolean check = true;
        double number = 0;
        do {
            System.out.print(welcome);
            String tmp = sc.nextLine();
            if (tmp.isEmpty()) {
                check = false;
            } else {
                number = Double.parseDouble(tmp);
                check = false;
            }
        } while (check || number < min);
        return number;
    }

    public static String getStatus(String welcome) {
        String status = "Not Available";
        System.out.println("Statuses:\n Default - Not Available\n 1 - Available");
        System.out.print(welcome);
        int temp;
        temp = Integer.parseInt(sc.nextLine());
        if (temp == 1) {
            status = "Available";
            return status;
        }
        return status;
    }

    public static String getString(String welcome, int minCharacter, int maxCharacter) {
        boolean check = true;
        String result = "";
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else if (result.length() < minCharacter || result.length() > maxCharacter) {
                System.out.println("Your description must be above " + minCharacter + " and below " + maxCharacter + " characters.");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getString(String welcome, String format) {
        boolean check = true;
        String result = "";
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (!result.matches(format)) {
                System.out.println("Invalid ID format. Please enter a valid ID starting with 'B' and followed by 5 digits.");
            } else {
                if (result.isEmpty()) {
                    System.out.println("Input text!!!");
                } else {
                    check = false;
                }
            }
        } while (check);
        return result;
    }

    public static int getInt(String welcome, int min) {
        boolean check = true;
        int number = 0;
        do {
            try {
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static boolean askUser(String welcome) {
        System.out.print(welcome);
        String confirm = sc.nextLine();
        if("Y".equalsIgnoreCase(confirm)) {
            System.out.println("Exit to the menu");
            return true;
        } else {
            return false;
        }
    }

    public static void display(PublisherList obj) {
        System.out.println(repeat("_",70));
        System.out.print(String.format("| %-15s | %-25s | %-20s |\n", "Publisher ID", "Publisher Name", "Publisher Phone"));
        System.out.println(repeat("_",70));
        obj.forEach((publisher) -> {
            System.out.print(String.format("| %-15s | %-25s | %-20s |\n", publisher.getPubID(), publisher.getPubName(), publisher.getPhoneNumber()));
        });
        System.out.println(repeat("_",70));
    }
    
     public static void displayBook(BookList temp, PublisherList pub) {
        ArrayList<Publisher> publishers = pub;
        System.out.println(repeat("_",127));
        System.out.format("| %-10s | %-25s | %-10s | %-15s | %-10s | %-25s | %-10s |\n", "Book ID", "Book Name", "Price", "Quantity", "Subtotal", "Publisher", "Status");
        System.out.println(repeat("_",127));
        for (Book b : temp) {
            for (Publisher p : publishers) {
                if (p.getPubID().equals(b.getPubID())) {
                    int subtotal = (int) (b.getQuantity() * b.getPrice());
                    System.out.format("| %-10s | %-25s | %-10s | %-15s | %-10s | %-25s| %-10s |\n", b.getBookID(), b.getBookName(), b.getPrice(), b.getQuantity(), subtotal, p.getPubName(), b.getStatus());
                }
            }
        }
        System.out.println(repeat("_",127));
    }

    public static void printBookList(ArrayList<Book> searchResults) {
        searchResults.forEach((o) -> {
            System.out.println(o.toString());
        });
    }

    public static String updateName(String welcome, String oldName) {
        String result = oldName;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static double updatePrice(String welcome, double oldPrice) {
        boolean check = true;
        double number = oldPrice;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check);
        return number;
    }

    public static int updateQuantity(String welcome, int oldQuantity) {
        boolean check = true;
        int number = oldQuantity;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check);
        return number;
    }

    public static String updateStatus(String welcome, int oldData) {
        String status;
        boolean check = true;
        int statusNumber;
        if (oldData == 1) {
            status = "Available";
        } else {
            status = "Not Available";
        }
        do {
            try {
                System.out.print(welcome);
                System.out.println("Statuses:\n Default - Not Available\n 1 - Available");
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    statusNumber = Integer.parseInt(tmp);
                    if (statusNumber == 1) {
                        status = "Available";
                    } else {
                        status = "Not Available";
                    }
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check);
        return status;
    }
    
     private static String repeat(String s, int n){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(s);
        }
        return result.toString();
    }
}
