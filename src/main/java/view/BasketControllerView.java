package view;

import basic.basket.Basket;
import basic.product.Product;

import java.util.Iterator;
import java.util.Map;

import static java.lang.Math.round;

public class BasketControllerView extends View {

    public BasketControllerView(){
    }

    public void showBasket(Basket basket){
        Map<Product, Integer> products = basket.getproducts();
        Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator();

        System.out.println(new String(new char[53]).replace("\0", "-") + "BASKET" + new String(new char[54]).replace("\0", "-") + "\n");
        System.out.println("------------------PRODUCT-------------------|------UNIT-PRICE------|-------QUANTITY-------|----SUMMARIC-VALUE----");

        int i = 1;
        while(iterator.hasNext())
        {
            Map.Entry<Product, Integer> entry = iterator.next();

            System.out.println(
                 i + ". " + entry.getKey().getName() +
                 new String(new char[40-entry.getKey().getName().length()]).replace("\0", " ") +
                 " | "  + new String(new char[20-Double.toString(entry.getKey().getPrice()).length()]).replace("\0", " ") +
                 entry.getKey().getPrice() +
                 " | "  + new String(new char[20-Integer.toString(entry.getValue()).length()]).replace("\0", " ") +
                 entry.getValue() +
                 " | "  + new String(new char[20-Double.toString((entry.getValue() * entry.getKey().getPrice())).length()]).replace("\0", " ") +
                 (entry.getValue() * entry.getKey().getPrice()) + "|");
            i++;
        }
        System.out.println(new String(new char[113]).replace("\0", "-"));
        System.out.println("TOTAL : " + basket.getValue());
        System.out.println(new String(new char[113]).replace("\0", "-"));
    }

}

