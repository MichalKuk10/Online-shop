package basic.order;


import basic.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private Map<Product, Integer> products;
    private Map<String, Float> deliveryMethod;
    private String paymentMethod;
    private float discountValue = 0;

    public Order(){
        this.deliveryMethod = new HashMap<>();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<String, Float> getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod_, Float cost) {
        deliveryMethod.put(deliveryMethod_, cost);
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(float discountValue) {
        this.discountValue = discountValue;
    }
}
