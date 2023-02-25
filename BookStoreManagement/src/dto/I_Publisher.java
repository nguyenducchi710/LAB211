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
public interface I_Publisher {
    ArrayList<Publisher> createPublisher();
    void delPublisher();
    void savePubFile();
    ArrayList<Publisher> printPubFile();
    
}
