package controllers;

import basic.discount_code.DiscountCodeDAO;
import basic.order.OrderDAO;
import basic.product.Product;
import basic.user.UserDAO;
import input_manager.InputManager;
import basic.user.User;
import basic.order.Order;
import view.PurchaseControllerView;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PurchaseController {

    private final List<String> menuOptions = new ArrayList<>(Arrays.asList("Manage delivery address",
            "Choose delivery service", "Enter discount code", "Choose method of payment", "Proceed to payment", "Go back to basket", "Go back to products"));
    private final List<String> deliveryMethodMenu = new ArrayList<>(Arrays.asList("Personal pickup (free)", "DPD (+15,00)", "Traditional Postal Service (+10,00)", "Parcel Locker Delivery (+5,00)"));
    private final List<String> paymentMethodMenu = new ArrayList<>(Arrays.asList("Payment on personal pickup", "Bank transfer", "PayPal", "Payment on delivery"));

    private Map<String, Float> deliveryMethods;

    private InputManager inputManger = new InputManager();
    private PurchaseControllerView purchaseControllerView;

    private User user;
    private Order order;
    private Float orderValue;
    private Float discountedOrderValue;
    private boolean isDiscountActive = false;

    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private DiscountCodeDAO discountCodeDAO;
    private LocalDate today = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String timeOfArrival;
    private boolean isRunning = true;
    private String goingBackDirector = "";

    public PurchaseController(User user, UserDAO userDAO, OrderDAO orderDAO, DiscountCodeDAO discountCodeDAO) {
        this.user = user;
        this.userDAO = userDAO;
        this.order = new Order();
        this.orderDAO = orderDAO;
        this.discountCodeDAO = discountCodeDAO;
        this.purchaseControllerView = new PurchaseControllerView();
        setDeliveryMethods();
    }

    public String run() throws SQLException {
        isRunning = true;

        this.order.setDeliveryMethod("DPD", deliveryMethods.get("DPD"));
        this.order.setPaymentMethod("Bank transfer");

        while (isRunning) {
            calculateOrderValue();
            calculateTimeOfArrival();
            purchaseControllerView.showPurchaseScreen(order, deliveryMethods, paymentMethodMenu, isDiscountActive, user.getAddress(), orderValue, timeOfArrival);
            int userChoice = inputManger.askForMenuOption(menuOptions, "Purchase Menu");
            switch (userChoice) {
                case 1:
                    askForUserAddress();
                    break;
                case 2:
                    askForDeliveryMethod();
                    break;
                case 3:
                    askForDiscountCode();
                    break;
                case 4:
                    askForPaymentMethod();
                    break;
                case 5:
                    finalisePurchase();
                    isRunning = false;
                    goingBackDirector = "Products menu";
                    break;
                case 6:
                    isRunning = false;
                    goingBackDirector = "Basket menu";
                    break;
                case 7:
                    isRunning = false;
                    goingBackDirector = "Products menu";
                    break;
            }
        }
        return goingBackDirector;
    }

    private void setDeliveryMethods(){
        this.deliveryMethods = new HashMap<>();
        deliveryMethods.put("Personal pickup", (float) 0);
        deliveryMethods.put("DPD", (float) 15);
        deliveryMethods.put("Traditional Postal Service", (float) 10);
        deliveryMethods.put("Parcel Locker Delivery", (float) 5);
    }

    public void setBasket(Map<Product, Integer> products){
        this.order.setProducts(products);
    }

    public void askForUserAddress() throws SQLException {
        ArrayList<String> userAddressInput;
        String[] UserAddressMenu = {"Enter street: ", "Enter location number: ", "Enter city: ", "Enter postal code: "};
        userAddressInput = inputManger.getMultipleStringInput(UserAddressMenu);
        StringBuilder userAddress = new StringBuilder();
        int i = 1;
        for (String input : userAddressInput) {
            if (i == userAddressInput.size()) {
                userAddress.append(input);
            } else {
                userAddress.append(input).append(" ");
            }
            i++;
        }
        user.setAddress(userAddress.toString());
        userDAO.updateUser(user);
    }

    public void askForDeliveryMethod() {

        int userChoice = inputManger.askForMenuOption(deliveryMethodMenu, "Delivery Method Menu");
        switch (userChoice) {
            case 1:
                order.setDeliveryMethod("Personal pickup", deliveryMethods.get("Personal pickup"));
                break;
            case 2:
                order.setDeliveryMethod("DPD", deliveryMethods.get("DPD"));
                break;
            case 3:
                order.setDeliveryMethod("Traditional Postal Service", deliveryMethods.get("Traditional Postal Service"));
                break;
            case 4:
                order.setDeliveryMethod("Parcel Locker Delivery", deliveryMethods.get("Parcel Locker Delivery"));
                break;
        }
    }

    public void askForPaymentMethod() {
        int userChoice = inputManger.askForMenuOption(paymentMethodMenu, "Payment Method Menu");
        switch (userChoice) {
            case 1:
                order.setPaymentMethod("Payment on personal pickup");
                break;
            case 2:
                order.setPaymentMethod("Bank transfer");
                break;
            case 3:
                order.setPaymentMethod("PayPal");
                break;
            case 4:
                order.setPaymentMethod("Payment on delivery");
                break;
        }
    }

    public void askForDiscountCode() {
        String discountCode = inputManger.getStringInput("Insert discount code: ");
        boolean isCodeValid = discountCodeDAO.checkIfDiscountCodeInDatabase(discountCode);
        if (isCodeValid) {
            this.isDiscountActive = true;
            order.setDiscountValue(50);
        }
    }

    public void calculateTimeOfArrival() {
        this.timeOfArrival = formatter.format(today.plusDays(1));
    }

    public void calculateOrderValue() {
        float calculatedOrderValue = 0;

        Map<Product, Integer> products = order.getProducts();
        Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Product, Integer> entry = iterator.next();

            calculatedOrderValue += (entry.getKey().getPrice()) * entry.getValue();
        }
        this.orderValue = calculatedOrderValue;
        if ((calculatedOrderValue - order.getDiscountValue()) < 0){
            this.discountedOrderValue = (float) 0;
        }
        else{
            this.discountedOrderValue = calculatedOrderValue - order.getDiscountValue();
        }
    }


    public void finalisePurchase() throws SQLException {
        orderDAO.insertOrder(order, user.getUserId());
        purchaseControllerView.print("\n\nYour order has been successfully placed!\nThank you for choosing " +
                "our exclusive Toy Store!\n\n");
    }
}