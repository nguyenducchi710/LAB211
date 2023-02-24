/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controllers.Menu;
import dto.I_Menu;
import controllers.PetList;
import dto.I_PetList;

/**
 *
 * @author asus
 */
public class PetStoreManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        I_Menu menu = new Menu();
        menu.addItem("1. Add a pet");
        menu.addItem("2. Find a pet");
        menu.addItem("3. Update a pet");
        menu.addItem("4. Delete a pet");
        menu.addItem("5. Add an order");
        menu.addItem("6. List orders");
        menu.addItem("7. Sort orders");
        menu.addItem("8. Save data");
        menu.addItem("9. Load data");
        menu.addItem("10. Quit");
        int choice;
        boolean check = true;
        I_PetList list = new PetList();
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    list.addPet();
                    break;
                case 2:
                    list.findPet();
                    break;
                case 3:
                    list.updatePet();
                    break;
                case 4:
                    list.deletePet();
                    break;
                case 5:
                    list.addOrder();
                    break;
                case 6:
                    list.listOrder();
                    break;
                case 7:
                    list.sortOrder();
                    break;
                case 8:
                    list.saveData();
                    break;
                case 9:
                    list.loadData();
                    break;
                case 10:
                    check = menu.confirmYesNo("Do you want to quit?(Y/N)");
                    break;

            }
        } while (choice >= 0 && choice <= 10 && check);
    }
}
