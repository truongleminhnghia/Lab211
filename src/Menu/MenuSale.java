/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import PurchasingManagement.InventoryManagement;
import SaleManagement.SaleManagement;
import Tools.MyTools;

/**
 *
 * @author Administrator
 */
public class MenuSale {

    private int choice = 0;
    private final String fileBill = "./data/exports.txt";
    private final String fileBs = "./data/bsproducts.txt";
    private final String fileProduct = "./data/product.txt";

    public void menuSale() {
        SaleManagement sale = new SaleManagement(fileProduct);
        sale.loadToFile(fileBill, fileBs);
        boolean change = false;
        do {
            sale.readProductFromFile(fileProduct);
            System.out.println("1. Add Bill");
            System.out.println("2. View product");
            System.out.println("3. Save file");
            System.out.println("4. QUIT!");

            try {
                choice = Integer.parseInt(MyTools.readStr("Enter your choice", "[\\d]+"));
                switch (choice) {
                    case 1:
                        sale.createBillOfSale();
                        change = true;
                        break;
                    case 2:
                        sale.displayProduct();
                        break;
                    case 3:
                        sale.saveToFile(fileBill, fileBs);
                        change = false;
                        break;
                    default:
                        if (change) {
                            boolean b = MyTools.readBoolean("Data changed. Save to file? Y/N");
                            if (b == true) {
                                sale.saveToFile(fileBill, fileBs);
                            }
                        }
                        System.out.println("YOU EXITED!");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (choice > 0 && choice < 4);
    }

    public static void main(String[] args) {
        MenuSale menu = new MenuSale();
        menu.menuSale();
    }
}
