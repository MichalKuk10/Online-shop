package view;

import basic.basket.Basket;
import basic.product.Product;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;

public class BasketControllerView extends View {

    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public BasketControllerView(){
    }

    public void showBasket(Basket basket){
        Map<Product, Integer> products = basket.getproducts();
        Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator();

        System.out.println(new String(new char[53]).replace("\0", "-") + "BASKET" + new String(new char[55]).replace("\0", "-") + "\n");
        System.out.println("------------------PRODUCT-------------------|------UNIT-PRICE------|-------QUANTITY-------|----SUMMARIC-VALUE-----");

        int i = 1;
        while(iterator.hasNext())
        {
            Map.Entry<Product, Integer> entry = iterator.next();

            System.out.print(
                 i + ". " + entry.getKey().getName() + new String(new char[40-entry.getKey().getName().length()]).replace("\0", " ") +
                 " | "  + new String(new char[20-decimalFormat.format(entry.getKey().getPrice()).length()]).replace("\0", " "));
            System.out.printf("%.2f", entry.getKey().getPrice());
                 System.out.print(" | "  + new String(new char[20-Integer.toString(entry.getValue()).length()]).replace("\0", " ") +
                 entry.getValue() + " | "  + new String(new char[20-decimalFormat.format((entry.getValue() * entry.getKey().getPrice())).length()]).replace("\0", " "));
                         System.out.printf("%.2f",(entry.getValue() * entry.getKey().getPrice()));
            System.out.print(" |\n");
            i++;
        }
        System.out.println(new String(new char[114]).replace("\0", "-"));
        System.out.print("TOTAL : ");
        System.out.printf("%.2f", basket.getValue());
        System.out.println("\n" + new String(new char[114]).replace("\0", "-"));
    }

}

