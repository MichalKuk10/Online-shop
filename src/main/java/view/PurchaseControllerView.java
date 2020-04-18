package view;

import basic.order.Order;
import basic.product.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PurchaseControllerView extends View {

    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public PurchaseControllerView(){
    }
    
    public void showPurchaseScreen(Order order, Map<String, Float> deliveryMethods, List<String> paymentMethods, boolean isDiscountActive, String userAddress, Float orderValue, String timeOfArrival){
        printProducts(order, orderValue);
        printDiscountStatus(isDiscountActive);
        printUserAddress(userAddress + "\n");
        indicateChosenDeliveryMethod(deliveryMethods, order.getDeliveryMethod());
        print("Estimated time of delivery: " + timeOfArrival + "\n");
        indicateChosenPaymentMethod(paymentMethods, order.getPaymentMethod());
    }

    private void printProducts(Order order, Float orderValue){
        Map<Product, Integer> products = order.getProducts();
        Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator();

        print("\n" + new String(new char[53]).replace("\0", "-") + "CHECKOUT" + new String(new char[53]).replace("\0", "-") + "\n");
        print("------------------PRODUCT-------------------|------UNIT-PRICE------|-------QUANTITY-------|----SUMMARIC-VALUE-----");

        int i = 1;
        while(iterator.hasNext())
        {
            Map.Entry<Product, Integer> entry = iterator.next();

            System.out.print(i + ". " + entry.getKey().getName() +
                            new String(new char[40-entry.getKey().getName().length()]).replace("\0", " ") +
                            " | "  + new String(new char[20-decimalFormat.format(entry.getKey().getPrice()).length()]).replace("\0", " "));
            System.out.printf("%.2f", entry.getKey().getPrice());
            System.out.print(" | "  + new String(new char[20-Integer.toString(entry.getValue()).length()]).replace("\0", " "));
            System.out.print(entry.getValue());
            System.out.print(" | "  + new String(new char[20-decimalFormat.format(entry.getValue() * entry.getKey().getPrice()).length()]).replace("\0", " "));
            System.out.printf("%.2f", (entry.getValue() * entry.getKey().getPrice()));
            System.out.print("|\n");
            i++;
        }
        print(new String(new char[114]).replace("\0", "-"));
        print("TOTAL without discount : " + orderValue);
        System.out.print("DISCOUNT : ");
        System.out.printf("%.2f", order.getDiscountValue());
        System.out.print("\nTOTAL : ");
        System.out.printf("%.2f", (orderValue - order.getDiscountValue()));
        print("\n" + new String(new char[114]).replace("\0", "-") + "\n");
    }

    private void printUserAddress(String shipmentAddress){
        print("Your delivery adress: ");
        print(shipmentAddress);
    }

    private void printDiscountStatus(boolean isDiscounted){
        print("Discount status: ");
        if (isDiscounted){
            System.out.print("Activated!");
        }
        else{
            System.out.print("Not active");
        }
        print("\n");
    }

    private void indicateChosenDeliveryMethod(Map<String, Float> deliveryMethods, Map<String, Float> chosenDeliveryMethod){
        print("Your chosen delivery method: ");

        Map.Entry<String, Float> entry = chosenDeliveryMethod.entrySet().iterator().next();
        String chosenDeliveryMethodName = entry.getKey();
        int i = 0;
        switch (chosenDeliveryMethodName) {
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

        Iterator<Map.Entry<String, Float>> iterator = deliveryMethods.entrySet().iterator();

        while(iterator.hasNext()){
                Map.Entry<String, Float> entry2 = iterator.next();
                String arrow = "";
                if (y == i-1){
                    arrow = "=>";
                }
                System.out.print(new String(new char[3-arrow.length()]).replace("\0", " ") + arrow + " " + entry2.getKey() + " " + entry2.getValue() + "\n");
                y++;
            }
        }

    private void indicateChosenPaymentMethod(List<String> paymentMethods, String chosenPaymentMethod){
        print("Your chosen payment method: ");
        int i = 0;
        switch (chosenPaymentMethod) {
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
        for (String paymentMethod : paymentMethods){
            String arrow = "";
            if (y == i-1){
                arrow = "=>";
            }
            System.out.print(new String(new char[3-arrow.length()]).replace("\0", " ") + arrow + " " + paymentMethod + "\n");

            y++;
        }
    }
}
