package basic.basket;

import basic.product.Product;

import java.util.*;

public class Basket {

    private Map<Product, Integer> products = new HashMap<Product, Integer>();
    private float value;

    public Basket(){

    }

    public void addProduct(Product product, Integer quantity){
        products.put(product,quantity);
    }

    public void deleteProduct(String productName){
        Product productToDelete = null;

        for (Product product_ : products.keySet()){
            if (product_.getProductName() == productName){
                productToDelete = product_;
            }
        }
        products.remove(productToDelete);
    }

    public void changeProductQuantity(String productName, Integer quantity) {
        Product productToBeChanged = null;

        for (Product product : products.keySet()) {
            if (product.getProductName() == productName) {
                productToBeChanged = product;
            }
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
            value += entry.getKey().getprice() * entry.getValue();
        }
    }

    public Map<Product, Integer> getproducts(){
        return products;
    }


}
