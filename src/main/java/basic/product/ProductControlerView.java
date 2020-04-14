package basic.product;

import java.util.ArrayList;
import java.util.Iterator;

public class ProductControlerView {

    public static void printProduct(Product product) {
        String str = " ";

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

        for (Product product : products){
            System.out.println("-------------------------");
            System.out.println("|   " + product.getName());
            System.out.println("-------------------------");
            System.out.println("|ID: " + product.getProductId());
            System.out.println("|Brand: " + product.getBrand());
            System.out.println("|Price: " + product.getPrice());
            System.out.println("|Age Category: " + product.getMinAge());
            System.out.println("-------------------------");}
    }
}
