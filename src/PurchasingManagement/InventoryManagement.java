/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PurchasingManagement;

import SaleManagement.BsProduct;
import Tools.MyTools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */

public class InventoryManagement {

    private static List<Product> products = new ArrayList<>();
    private static List<PurchaseReceipt> purchaseReceipts = new ArrayList<>();

    public static void sortByID() {
        Collections.sort(products, Product.byId);
    }

    public void inputProduct() {
        String prID = MyTools.generateCode("IM", 6, purchaseReceipts.size() + 1);
        Date date = MyTools.readDate("Enter date input", "dd-MM-yyyy");
        PurchaseReceipt purchaseReceipt = new PurchaseReceipt(prID, date);

        int quanType = Integer.parseInt(MyTools.readStr("Enter quantity type of prouct", "[\\d]+"));

        for (int i = 0; i < quanType; i++) {
            System.out.println();
            System.out.println("Enter information product " + (i + 1) + ":");
            String pID = MyTools.generateCode("P", 7, products.size() + 1);
            String name = MyTools.readStr("Enter name product", "[aA-zZ0-9 ]+");
            Date production = MyTools.readDateBefore("Enter production date", "dd-MM-yyyy", date);
            Date expiration = MyTools.readDateAfter("Enter expiraiton date", "dd-MM-yyyy", date);
            double price = Double.parseDouble(MyTools.readStr("Enter price product", "[\\d]+"));
            double salePrice = Double.parseDouble(MyTools.readStr("Enter sale price product", "[\\d]+"));
            int quantity = Integer.parseInt(MyTools.readStr("enter quantity product", "[\\d]+"));
            int curquantity = quantity;
            Product product = new Product(prID, pID, name, production, expiration, salePrice, price, curquantity, curquantity);
            products.add(product);
            System.out.println("Added successfully!");
            System.out.println();
        }
        purchaseReceipts.add(purchaseReceipt);
    }

    public void displayWithQuantity() {
        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getCurQuantity() > 0) {
                result.add(product);
            }
        }

        if (result.isEmpty()) {
            System.out.println("List empty!");
        } else {
            System.out.println("List product with quantity > 0:");

            for (Product product : result) {
                System.out.println(product.toString());
            }
        }
    }

    public void viewCheckExpiration() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 10);
        List<Product> checkExpiration = new ArrayList<>();

        for (Product product : products) {
            if (product.getCurQuantity() > 0 && product.getExpiration().before(calendar.getTime())) {
                checkExpiration.add(product);
            }
        }

        if (checkExpiration.isEmpty()) {
            System.out.println("List check Expiration Empty");
        } else {
            System.out.println("List check expiration product: ");
            for (Product product : checkExpiration) {
                System.out.println(product.toString());
            }
        }
    }

    public void viewProduct() {
        String name = MyTools.readStr("Enter name product to file", "[aA-zZ0-9 ]+");
        List<Product> foundProduct = new ArrayList<>();

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                foundProduct.add(product);
            }
        }

        if (foundProduct.isEmpty()) {
            System.out.println("List IsEmpty");
        } else {
            System.out.println("List Product: ");
            for (Product product : foundProduct) {
                System.out.println(product.toString());
            }
        }
    }

    public void viewDiscontinuedProducts() {
        List<Product> disContinuedProduct = new ArrayList<>();
        for (Product product : products) {
            if (product.getCurQuantity() == 0) {
                disContinuedProduct.add(product);
            }
        }

        if (disContinuedProduct.isEmpty()) {
            System.out.println("List empty!");
        } else {
            System.out.println("List product discontinue product: ");
            for (Product product : disContinuedProduct) {
                System.out.println(product.toString());
            }
        }
    }

    public void viewWithQuantity() {
        int quantity = Integer.parseInt(MyTools.readStr("Enter quantity to find", "[\\d]+"));

        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCurQuantity() < quantity) {
                result.add(product);
            }
        }

        if (result.isEmpty()) {
            System.out.println("List empty!");
        } else {
            System.out.println("List product need find: ");
            for (Product product : result) {
                System.out.println(product.toString());
            }
        }
    }

    public void updateProduct() {
        int choice = 0;
        String id = MyTools.readStr("Enter id product to update", "[aA-zZ0-9]+");
        Product foundProduct = null;

        for (Product product : products) {
            if (product.getpID().equalsIgnoreCase(id)) {
                foundProduct = product;
                break;
            }
        }

        if (foundProduct == null) {
            System.out.println("Product with ID " + id + " not found");
        } else {
            do {
                System.out.println("UPDATE PRODUCT: ");
                System.out.println("1. Update Name.");
                System.out.println("2. Update Price.");
                System.out.println("3. Update Quantity.");
                System.out.println("4. QUIT!");

                try {
                    choice = Integer.parseInt(MyTools.readStr("Enter your choice", "[\\d]+"));
                    switch (choice) {
                        case 1:
                            String name = MyTools.readStr("Update Name", "[aA-zZ0-9 ]+");
                            foundProduct.setName(name);
                            System.out.println("Name update successfully.");
                            break;
                        case 2:
                            double price = Double.parseDouble(MyTools.readStr("Update price", "[\\d]+"));
                            foundProduct.setPricePurchase(price);
                            System.out.println("Price update successfully.");
                            break;
                        case 3:
                            int quantity = Integer.parseInt(MyTools.readStr("Upate quantity", "[\\d]+"));
                            foundProduct.setCurQuantity(quantity);
                            System.out.println("Quantity update successfully.");
                            break;
                        default:
                            System.out.println("You have exists!");
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (choice > 0 && choice < 3);
        }

    }

    public void saveToFile(String fileProduct, String filePurchase) {
        PrintWriter f1 = null;
        PrintWriter f2 = null;

        try {
            f1 = new PrintWriter(fileProduct);
            for (Product product : products) {
                f1.println(product.toString());
            }
            System.out.println("Data saved to file: " + fileProduct);

            f2 = new PrintWriter(filePurchase);
            for (PurchaseReceipt purchaseReceipt : purchaseReceipts) {
                f2.println(purchaseReceipt.toString());
            }
            System.out.println("Data saved to file: " + filePurchase);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (f1 != null) {
                    f1.close();
                }
                if (f2 != null) {
                    f2.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadToFile(String fileProduct, String filePurchase) {
        FileReader f1 = null;
        FileReader f2 = null;
        BufferedReader fr1 = null;
        BufferedReader fr2 = null;

        try {
            File productFile = new File(fileProduct);
            File purchaseFile = new File(filePurchase);

            if (productFile.exists() && purchaseFile.exists()) {
                f1 = new FileReader(productFile);
                fr1 = new BufferedReader(f1);
                while (fr1.ready()) {
                    String str = fr1.readLine();
                    String[] arr = str.split(",");
                    if (arr.length == 9) {
                        Product product = new Product(arr[0], arr[1], arr[2], MyTools.parseDate(arr[3], "dd-MM-yyyy"),
                                MyTools.parseDate(arr[4], "dd-MM-yyyy"), Double.parseDouble(arr[5].trim()),
                                Double.parseDouble(arr[6].trim()), Integer.parseInt(arr[7].trim()), Integer.parseInt(arr[8].trim()));
                        products.add(product);
                    }
                }

                f2 = new FileReader(filePurchase);
                fr2 = new BufferedReader(f2);
                while (fr2.ready()) {
                    String str = fr2.readLine();
                    String[] arr = str.split(",");
                    if (arr.length == 2) {
                        PurchaseReceipt purchase = new PurchaseReceipt(arr[0], MyTools.parseDate(arr[1], "dd-MM-yyyy"));
                        purchaseReceipts.add(purchase);
                    }
                }
                System.out.println("Data loaded successfully from files.");
            } else {
                System.out.println("One or both files do not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (f1 != null) {
                    f1.close();
                }
                if (f2 != null) {
                    f2.close();
                }
                if (fr1 != null) {
                    fr1.close();
                }
                if (fr2 != null) {
                    fr2.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
