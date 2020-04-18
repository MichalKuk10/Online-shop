package view;

import basic.product.Product;;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductsControllerView extends View {

    public static void printProduct(Product product) {

        System.out.println("-------------------------");
        System.out.println("|   " + product.getName());
        System.out.println("-------------------------");
        System.out.println("|ID: " + product.getProductId());
        System.out.println("|Brand: " + product.getBrand());
        System.out.println("|Price: " + product.getPrice());
        System.out.println("|Age Category: " + product.getMinAge());
        System.out.println("-------------------------");
    }
    public static void printProduct(ArrayList<Product> products) {

        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        ArrayList<ArrayList<Product>> listOfListOf3Products = new ArrayList<ArrayList<Product>>();

        ArrayList<Product> tempListOf3Products = new ArrayList<Product>();
        int i = 0;
        for (Product product : products){
            tempListOf3Products.add(products.get(i));
            if ((i +1) % 3 == 0){
                listOfListOf3Products.add(tempListOf3Products);
                tempListOf3Products = new ArrayList<Product>();
            }
            i++;
        }

        for (ArrayList<Product> listOf3Products : listOfListOf3Products) {
            for (Product product : listOf3Products) {
                System.out.print("----------------------------" + "        ");
            }
            System.out.print("\n");
            for (Product product : listOf3Products) {
                System.out.print("----------------------------" + "        ");
            }
            System.out.print("\n");
            for (Product product : listOf3Products) {
                System.out.print("| " + product.getName() + new String(new char[34-product.getName().length()]).replace("\0", " "));
            }
            System.out.print("\n");
            for (Product product : listOf3Products) {
                System.out.print("----------------------------" + "        ");
            }
            System.out.print("\n");
            for (Product product : listOf3Products) {
                System.out.print("|ID: " + product.getProductId() + new String(new char[23-Integer.toString(product.getProductId()).length()]).replace("\0", " ") + "        ");
            }
            System.out.print("\n");
            for (Product product : listOf3Products) {
                System.out.print("|Brand: " + product.getBrand() + new String(new char[20-product.getBrand().length()]).replace("\0", " ") + "        ");
            }
            System.out.print("\n");
            for (Product product : listOf3Products) {
                System.out.print("|Price: "); System.out.printf("%.2f", product.getPrice()); System.out.print( new String(new char[20-decimalFormat.format(product.getPrice()).length()]).replace("\0", " ") + "        ");
            }
            System.out.print("\n");
            for (Product product : listOf3Products) {
                System.out.print("|Age Category: " + product.getMinAge() + new String(new char[13-Integer.toString(product.getMinAge()).length()]).replace("\0", " ") + "        ");
            }
            System.out.print("\n");
            for (Product product : listOf3Products) {
                System.out.print("----------------------------" + "        ");
            }
            System.out.print("\n\n");
        }
    }
    public static void printMessage(String message){
        System.out.println(message);
    }

}
