package PurchasingManagement;

import java.util.Date;
import Tools.MyTools;
import java.util.Comparator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Administrator
 */
public class Product {

    String prID;
    String pID;
    String name;
    Date production;
    Date expiration;
    double salePrice;
    double pricePurchase;
    int intitialQuantity;
    int curQuantity;

    public Product() {

    }

    public Product(String prID, String pID, String name, Date production, Date expiration, double salePrice, double pricePurchase, int intitialQuantity, int curQuantity) {
        this.prID = prID;
        this.pID = pID;
        this.name = name;
        this.production = production;
        this.expiration = expiration;
        this.salePrice = salePrice;
        this.pricePurchase = pricePurchase;
        this.intitialQuantity = intitialQuantity;
        this.curQuantity = curQuantity;
    }

    public String getPrID() {
        return prID;
    }

    public void setPrID(String prID) {
        this.prID = prID;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getProduction() {
        return production;
    }

    public void setProduction(Date production) {
        this.production = production;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(double pricePurchase) {
        this.pricePurchase = pricePurchase;
    }

    public int getIntitialQuantity() {
        return intitialQuantity;
    }

    public void setIntitialQuantity(int intitialQuantity) {
        this.intitialQuantity = intitialQuantity;
    }

    public int getCurQuantity() {
        return curQuantity;
    }

    public void setCurQuantity(int curQuantity) {
        this.curQuantity += intitialQuantity ;
    }

    @Override
    public String toString() {
        return prID + ", " + pID + ", " + name + ", " + MyTools.toString(production, "dd-MM-yyyy")
                + ", " + MyTools.toString(expiration, "dd-MM-yyyy") + ", " + salePrice + ", "
                + pricePurchase + ", " + intitialQuantity + ", " + curQuantity;
    }

    public static Comparator<Product> byId = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getpID().compareTo(o2.getpID());
        }
    };

    public static Comparator<Product> byrID = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.prID.compareTo(o2.getPrID());
        }
    };
}
