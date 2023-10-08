/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SaleManagement;

import Tools.MyTools;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class BillOfSale {
    private String bsID;
    private Date bsDate;
    
    public BillOfSale() {
        
    }

    public BillOfSale(String bsID, Date bsDate) {
        this.bsID = bsID;
        this.bsDate = bsDate;
    }

    public String getBsID() {
        return bsID;
    }

    public void setBsID(String bsID) {
        this.bsID = bsID;
    }

    public Date getBsDate() {
        return bsDate;
    }

    public void setBsDate(Date bsDate) {
        this.bsDate = bsDate;
    }

    @Override
    public String toString() {
    return bsID + ", " + MyTools.toString(bsDate, "dd-MM-yyyy");
    }
}
