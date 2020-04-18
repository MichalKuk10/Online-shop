package view;

import basic.order.Order;
import basic.product.Product;
import java.util.Iterator;
import java.util.Map;

public class PurchaseControllerView extends View {

    public PurchaseControllerView(){
    }
    
    public void showPurchaseScreen(Order order, String[] deliveryServiceOptions, String[] paymentOptions){
        printProducts(order);
        printDiscountCode(order.getDiscountStatus());
        printUserAddress(order.getShipmentAddress());
        printChosenDeliveryService(deliveryServiceOptions, order.getDeliveryMethod());
        printChosenPaymentOption(paymentOptions, order.getPaymentMethod());
    }

    private void printProducts(Order order){
        Map<Product, Integer> products = order.getProducts();
        Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator();

        print(new String(new char[53]).replace("\0", "-") + "CHECKOUT" + new String(new char[54]).replace("\0", "-") + "\n");
        print("------------------PRODUCT-------------------|------UNIT-PRICE------|-------QUANTITY-------|----SUMMARIC-VALUE----");

        int i = 1;
        while(iterator.hasNext())
        {
            Map.Entry<Product, Integer> entry = iterator.next();

            print(
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
        print(new String(new char[113]).replace("\0", "-"));
        print("TOTAL : " + order.getValue());
        print(new String(new char[113]).replace("\0", "-") + "\n");
    }

    private void printUserAddress(String shipmentAddress){
        print("Your delivery adress: ");
        print(shipmentAddress);
    }

    private void printDiscountCode(boolean isDiscounted){
        print("Discount status: ");
        if (isDiscounted){
            System.out.print("Activated!");
        }
        else{
            System.out.print("Not active");
        }
    }

    private void printChosenDeliveryService(String[] deliveryServiceOptions, String chosenDeliveryOption){
        print("Your chosen delivery method: ");
            int i = 0;
            switch (chosenDeliveryOption) {
                case "Personal pickup":
                    i = 1;
                    break;
                case "DPD":
                    i = 2;
                    break;
                case "Traditional Postal Service":
                    i = 3;
                    break;
                case "Parcel Locker Delivery":
                    i = 4;
                    break;
            }
            int y = 0;
        for (String deliveryMethod : deliveryServiceOptions){
            String arrow = "";
            if (y == i-1){
                arrow = "=>";
            }
            System.out.print(new String(new char[2-arrow.length()] + arrow + deliveryMethod + "\n"));
        }
    }

    private void printChosenPaymentOption(String[] paymentOptions, String chosenPaymentOption){
        print("Your chosen payment method: ");
        int i = 0;
        switch (chosenPaymentOption) {
            case "Payment on personal pickup":
                i = 1;
                break;
            case "Bank transfer":
                i = 2;
                break;
            case "PayPal":
                i = 3;
                break;
            case "Payment on delivery":
                i = 4;
                break;
        }
        int y = 0;
        for (String deliveryMethod : paymentOptions){
            String arrow = "";
            if (y == i-1){
                arrow = "=>";
            }
            System.out.print(new String(new char[2-arrow.length()] + arrow + deliveryMethod + "\n"));
        }
    }
}
