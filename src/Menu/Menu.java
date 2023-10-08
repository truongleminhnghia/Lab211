/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Menu {
    public static final Scanner scanner = new Scanner(System.in);
    
    public void Main() {
        int choice = 0; 
        do {
            System.out.println("########STORE########");
            System.out.println("1. Purchase Receipt Management.");
            System.out.println("2. Sale Management.");
            System.out.println("3. QUIT!");
            
            try {
                System.out.print("Enter your choice: "); 
                choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        MenuPurchase menupurchase = new MenuPurchase();
                        menupurchase.menuInventory();
                        break;
                    case 2:
                        MenuSale menusale = new MenuSale();
                        menusale.menuSale();
                        break;
                    default:
                        System.out.println("You have exit!");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (choice > 0 && choice <3);
    } 
}
