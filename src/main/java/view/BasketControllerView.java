package view;

import basic.basket.Basket;
import basic.product.Product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BasketControllerView extends AbstractView {

    BasketControllerView(){

    }

    public void printMenu(String[] menuItems, String title) {
        int index = 1;
        System.out.println(String.format("\n%s:", title.toUpperCase()));
        for (String item : menuItems) {
            System.out.println(String.format("%d. %s", index, item));
            index++;
        }
    }

    public void showBasket(Basket basket){
        System.out.println("--Basket--\n");
        Map<Product, Integer> products = basket.getproducts();
        Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator();
        int i = 1;
        while(iterator.hasNext())
        {
            Map.Entry<Product, Integer> entry = iterator.next();
            System.out.println(i + ". " + entry.getKey().getProductName() + " | quantity: " + entry.getValue() + " amount: " + (entry.getKey().getProductName() * entry.getValue()) + "\n");
        }

    }

}
}
