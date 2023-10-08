/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import PurchasingManagement.InventoryManagement;
import Tools.MyTools;

/**
 *
 * @author Administrator
 */
public class MenuPurchase {

    private int choice = 0;
    private final String fileProduct = "./data/product.txt";
    private final String filePurchase = "./data/imports.txt";

    public void menuInventory() {
        InventoryManagement inventory = new InventoryManagement();
        inventory.loadToFile(fileProduct, filePurchase);
        boolean change = false;
        do {
            System.out.println("Menu INVENTORY:");
            System.out.println("1. Add");
            System.out.println("2. View inventory.");
            System.out.println("3. View and check expiration.");
            System.out.println("4. Find product");
            System.out.println("5. View product discontinues.");
            System.out.println("6. View product with quantity.");
            System.out.println("7. Update product.");
            System.out.println("8. Save data to file.");
            System.out.println("9. QUIT!");
            try {
                choice = Integer.parseInt(MyTools.readStr("Enter your choice", "[\\d]"));
                switch (choice) {
                    case 1:
                        inventory.inputProduct();
                        change = true;
                        break;
                    case 2:
                        inventory.displayWithQuantity();
                        break;
                    case 3:
                        inventory.viewCheckExpiration();
                        break;
                    case 4:
                        inventory.viewProduct();
                        break;
                    case 5:
                        inventory.viewDiscontinuedProducts();
                        break;
                    case 6:
                        inventory.viewWithQuantity();
                        break;
                    case 7:
                        inventory.updateProduct();
                        change = true;
                        break;
                    case 8:
                        inventory.saveToFile(fileProduct, filePurchase);
                        change = false;
                        break;
                    default:
                        if (change) {
                            boolean b = MyTools.readBoolean("Data changed. Save to file? Y/N");
                            if (b == true) {
                                inventory.saveToFile(fileProduct, filePurchase);
                            }
                        }
                        System.out.println("YOU EXITED!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (choice > 0 && choice < 9);
    }
}
