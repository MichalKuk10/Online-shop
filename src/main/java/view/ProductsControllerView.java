package basic.product;

import view.View;

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
    public static void printMessage(String message){
        System.out.println(message);
    }

}
