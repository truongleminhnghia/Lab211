/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PurchasingManagement;

import Tools.MyTools;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class PurchaseReceipt {
    String prID;
    Date date;
    
    public PurchaseReceipt() {
        
    }
    
    public PurchaseReceipt(String prID, Date date) {
        this.prID= prID;
        this.date = date;
    }
    
    public String getPrID() {
        return prID;
    }
    
    public void setPrID(String prID) {
        this.prID = prID;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String toString() {
        return prID + ", " + MyTools.toString(date, "dd-MM-yyyy");
    }
}
