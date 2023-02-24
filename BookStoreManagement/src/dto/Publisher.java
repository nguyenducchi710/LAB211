/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author asus
 */
public class Publisher implements Serializable{
    private String pubID;
    private String pubName;
    private String phoneNumber;

    public Publisher() {
        super();
    }

    public Publisher(String pubID, String pubName, String phoneNumber) {
        this.pubID = pubID;
        this.pubName = pubName;
        this.phoneNumber = phoneNumber;
    }

    public String getPubID() {
        return pubID;
    }

    public void setPubID(String pubID) {
        this.pubID = pubID;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Publisher{" + "Publisher ID=" + pubID + ", Publisher Name=" + pubName + ", PhoneNumber=" + phoneNumber + '}';
    }
    
    
}
