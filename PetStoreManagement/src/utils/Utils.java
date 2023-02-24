/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class Utils {

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
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

    public static String getString(String welcome, int minCharacter, int maxCharacter) {
        String result = " ";
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        } else if (tmp.length() > 3 && tmp.length() < 50) {
            result = tmp;
        }
        return result;
    }

    public static String inputDate(String welcome) {
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //Dinh dang ngay thang nam //MM = month , mm = minutes
        dateFormat.setLenient(false);
        while (true) {
            String input = sc.nextLine();
            try {
                Date date = dateFormat.parse(input);
                Date curDate = Calendar.getInstance().getTime();
                if (curDate.compareTo(date) < 0) {
                    System.out.println("Please input date that before the current date!");
                    continue;
                }
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(date);
            } catch (Exception e) {
                System.out.println("Input valid date (dd/MM/yyyy): ");
            }
        }
    }

    public static String getString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int getInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
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
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int getPrice(String welcome) {
        Scanner sc = new Scanner(System.in);
        int price;
        do {
            System.out.print(welcome);
            price = sc.nextInt();
        } while (price < 1);
        return price;
    }

    public static String getCategory(String welcome) {
        String category;
        System.out.print(welcome);
        Scanner sc = new Scanner(System.in);
        category = sc.nextLine();
        while (true) {
            if ("Cat".equalsIgnoreCase(category) || "Dog".equalsIgnoreCase(category) || "Parrot".equalsIgnoreCase(category)) {
                break;
            }
        }
        return category;
    }

}
