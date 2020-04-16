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
        boolean productInBasket = false;
        Product productToReplace = null;
        int currentQuantity = 0;
        for (Product p : products.keySet()) {
            if (p.getProductId() == product.getProductId()) {
                productToReplace = p;
                productInBasket = true;
                currentQuantity = products.get(p);
            }
        }
        if (productInBasket) {
            products.replace(productToReplace, currentQuantity + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public void deleteProduct(int productIndex){
        Product productToDelete = null;
        int i = 1;
        for (Product product : products.keySet()){
            if (i == productIndex){
                productToDelete = product;
            }
            i++;
        }
        products.remove(productToDelete);
    }

    public void changeProductQuantity(Integer productIndex, Integer quantity) {
        Product productToBeChanged = null;
        int i = 1;
        for (Product product : products.keySet()) {
            if (i == productIndex) {
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
        value = 0;
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
