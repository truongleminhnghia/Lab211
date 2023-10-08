/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SaleManagement;

import PurchasingManagement.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import Tools.MyTools;
import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class SaleManagement {

    private static List<BillOfSale> billOfSales;
    private static List<BsProduct> bsProducts;
    private static List<Product> products;

    public SaleManagement(String productFilePat) {
        this.billOfSales = new ArrayList<>();
        this.bsProducts = new ArrayList<>();
        this.products = readProductFromFile(productFilePat);
    }

    // Tất cả các thủ thuật để thêm sản phẩm và update kết quả sản phẩm sau khi mua.
    public static void writeProductToFile(String fileName) {
        PrintWriter f = null;
        try {
            f = new PrintWriter(fileName);
            for (Product product : products) {
                f.println(product.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Product> readProductFromFile(String fileName) {
        List<Product> productList = new ArrayList<>();
        FileReader f = null;
        BufferedReader fr = null;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                f = new FileReader(file);
                fr = new BufferedReader(f);
                while (fr.ready()) {
                    String str = fr.readLine();
                    String[] arr = str.split(",");
                    if (arr.length == 9) {
                        Product product = new Product(arr[0].trim(), arr[1].trim(), arr[2].trim(), MyTools.parseDate(arr[3].trim(), "dd-MM-yyyy"),
                                MyTools.parseDate(arr[4].trim(), "dd-MM-yyyy"), Double.parseDouble(arr[5].trim()),
                                Double.parseDouble(arr[6].trim()), Integer.parseInt(arr[7].trim()), Integer.parseInt(arr[8].trim()));
                        productList.add(product);
                    }
                }
            } else {
                System.out.println("fiel not exists!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

    public Product findProduct(String productId) {
        for (Product product : products) {
            if (product.getpID().equalsIgnoreCase(productId)) {
                return product;
            }
        }
        return null;
    }
    
    // kết thúc

    public void createBillOfSale() {
        String bsID = MyTools.generateCode("BS", 6, billOfSales.size() + 1);
        Date date = new Date();
        BillOfSale bill = new BillOfSale(bsID, date);
        billOfSales.add(bill);

        int number = Integer.parseInt(MyTools.readStr("Enter number product", "[\\d]+"));

        for (int i = 0; i < number; i++) {
            String ID = bsID;
            String pID = MyTools.readStr("Enter Id Product", "[aA-zZ0-9]+");
            Product foundProduct = findProduct(pID);
            if (foundProduct != null && foundProduct.getCurQuantity() > 0) {
                System.out.println("Product found: " + foundProduct.toString());
                double price;
                do {
                    price = Double.parseDouble(MyTools.readStr("Enter price sale product", "[\\d]+"));

                } while (price <= foundProduct.getPricePurchase());

                int quantity;
                do {
                    System.out.println("Quantity in inventory current: " + foundProduct.getCurQuantity());
                    quantity = Integer.parseInt(MyTools.readStr("Enter quantity product", "[\\d]+"));
                } while (quantity < 0 || quantity > foundProduct.getCurQuantity());
                foundProduct.setCurQuantity(foundProduct.getCurQuantity() - quantity);
                BsProduct soldProduct = new BsProduct(ID, pID, price, quantity);
                bsProducts.add(soldProduct);
            } else {
                System.out.println("No Found product by ID");
            }
        }
        writeProductToFile("./data/product.txt");
    }

    public void displayProduct() {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCurQuantity() > 0) {
                result.add(product);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No Product!");
        } else {
            System.out.println("List Product: ");
            for (Product product : result) {
                System.out.println(product.toString());
            }
        }
    }

    public void saveToFile(String fileBill, String fileBs) {
        PrintWriter f1 = null;
        PrintWriter f2 = null;

        try {
            f1 = new PrintWriter(fileBill);
            for (BillOfSale bill : billOfSales) {
                f1.println(bill.toString());
            }
            System.out.println("Data saved to file: " + fileBill);

            f2 = new PrintWriter(fileBs);
            for (BsProduct solodProduct : bsProducts) {
                f2.println(solodProduct.toString());
            }
            System.out.println("Data saved to file: " + fileBs);
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

    public void loadToFile(String fileBill, String filebs) {
        FileReader f1 = null;
        FileReader f2 = null;
        BufferedReader fr1 = null;
        BufferedReader fr2 = null;

        try {
            File billFile = new File(fileBill);
            File bsFile = new File(filebs);

            if (billFile.exists() && bsFile.exists()) {
                f1 = new FileReader(billFile);
                fr1 = new BufferedReader(f1);
                while (fr1.ready()) {
                    String str = fr1.readLine();
                    String[] arr = str.split(",");
                    if (arr.length == 2) {
                        BillOfSale bill = new BillOfSale(arr[1].trim(), MyTools.parseDate(arr[1].trim(), "dd-MM-yyyy"));
                        billOfSales.add(bill);
                    }
                }

                f2 = new FileReader(filebs);
                fr2 = new BufferedReader(f2);
                while (fr2.ready()) {
                    String str = fr2.readLine();
                    String[] arr = str.split(",");
                    if (arr.length == 4) {
                        BsProduct bsproduct = new BsProduct(arr[0].trim(), arr[1].trim(), Double.parseDouble(arr[2].trim()), Integer.parseInt(arr[3].trim()));
                        bsProducts.add(bsproduct);
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
