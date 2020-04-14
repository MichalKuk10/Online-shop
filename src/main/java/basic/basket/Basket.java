package basic.basket;

import basic.product.Product;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;


public class Basket {

    private Map<Product, Integer> products = new HashMap<Product, Integer>();
    private float value;

    public Basket(){

    }

    public void addProduct(Product product, Integer quantity) {
        Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator();
        int i = 1;
        boolean alreadyInBasket = false;
        while (iterator.hasNext()) {
            Map.Entry<Product, Integer> entry = iterator.next();
            System.out.println("iterating through basket products");
            System.out.println("product to add = " + product.getName() + "productinloop = :" + entry.getKey().getName());
            if (product.getName() == entry.getKey().getName()) {
                System.out.println("true");
                quantity += entry.getValue();
                products.replace(product, quantity);
                alreadyInBasket = true;
            }
        }
        System.out.println("alreadyinbasket: " + alreadyInBasket);
        if (alreadyInBasket == false) {
            products.put(product, quantity);
        }
    }

    public void deleteProduct(int productIDtoDelete){
        Product productToDelete = null;
        int i = 1;
        for (Product product : products.keySet()){
            if (i == productIDtoDelete){
                productToDelete = product;
            }
            i++;
        }
        products.remove(productToDelete);
    }

    public void changeProductQuantity(Integer productId, Integer quantity) {
        Product productToBeChanged = null;
        int i = 1;
        for (Product product : products.keySet()) {
            if (i == productId) {
                productToBeChanged = product;
            }
            i++;
        }
        products.replace(productToBeChanged, quantity);
    }

    public void clear(){
        products.clear();
    }

    public void calculateBasket() {
        Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator();

        while(iterator.hasNext())
        {
            Map.Entry<Product, Integer> entry = iterator.next();
            value += entry.getKey().getPrice() * entry.getValue();
        }
    }

    public Map<Product, Integer> getproducts(){
        return products;
    }

    public float getValue(){
        return value;
    }


    public String toString(){
        return "Basket{" +
                "Products: " + productsToString() +
                "Value = " + value +
                '}';
    }

    private String productsToString(){
        String productsToString = "";
        Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator();
        int i = 1;
        while(iterator.hasNext())
        {
            Map.Entry<Product, Integer> entry = iterator.next();
            productsToString += "\n"+ i + ". " + entry.getKey().getName() + " | quantity: " + entry.getValue();
        }
        return productsToString;
    }
}
