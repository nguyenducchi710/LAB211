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
public interface I_Menu {
     void addItem(String s);
     int getChoice(int min, int max);
     void showMenu();
     boolean confirmYesNo(String welcome);
}
