package controllers;

import basic.discount_code.DiscountCodeDAO;
import basic.order.OrderDAO;
import basic.user.UserDAO;
import input_manager.InputManager;
import basic.basket.Basket;
import basic.user.User;
import basic.order.Order;
import view.PurchaseControllerView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PurchaseController {
    private String[] menuOptions = {"Manage delivery address", "Choose delivery service", "Enter discount code",
            "Choose method of payment", "Proceed to payment", "Go back"};
    private String[] deliveryMethods = {"Personal pickup  (free of charge)", "DPD (+15,00)",
            "Traditional Postal Service (+10,00)", "Parcel Locker Delivery (+5,00)"};
    private String[] paymentMethods = {"Payment on personal pickup (free of charge)", "Bank transfer (free of charge)",
            "PayPal (+2,00)", "Payment on delivery (+5,00)"};

    private InputManager inputManger = new InputManager();
    private PurchaseControllerView purchaseControllerView = new PurchaseControllerView();
    private Basket basket;
    private User user;
    private Order order;
    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private DiscountCodeDAO discountCodeDAO;
    private LocalDate today = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private boolean isRunning = true;

    public PurchaseController(User user, UserDAO userDAO, OrderDAO orderDAO, DiscountCodeDAO discountCodeDAO) {
        this.user = user;
        this.userDAO = userDAO;
        this.order = new Order(user.getUserId(), basket.getproducts(), user.getAddress());
        this.orderDAO = orderDAO;
        this.discountCodeDAO = discountCodeDAO;
    }

    public void run() {
        while (isRunning) {
            purchaseControllerView.showPurchaseScreen(order, deliveryMethods, paymentMethods);
            int userChoice = inputManger.askForMenuOption(menuOptions, "Purchase Menu");
            switch (userChoice) {
                case 1:
                    askForShipmentDetails();
                    break;
                case 2:
                    askForDeliveryService();
                    break;
                case 3:
                    askForDiscountCode();
                    break;
                case 4:
                    askForPaymentMethod();
                    break;
                case 5:
                    finalisePurchase();
                    break;
                case 6:
                    isRunning = false;
                    break;
            }
        }
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void askForShipmentDetails() {
        ArrayList<String> userAdressInput = new ArrayList<String>();
        String[] shipmentDetailsMenu = {"Enter street: ", "Enter location number: ", "Enter city: ", "Enter postal code: "};
        userAdressInput = inputManger.getMultipleStringInput(shipmentDetailsMenu);
        String userAdress = "";
        int i = 1;
        for (String input : userAdressInput) {
            if (i == userAdressInput.size()) {
                userAdress += input;
            } else {
                userAdress += input + " ";
            }
            i++;
        }
        userDAO.updateUserAdress(user.getUserId(), userAdress);
        user.setAddress(userAdress);
    }

    public void askForDeliveryService() {
        int userChoice = inputManger.askForMenuOption(deliveryMethods, "Adress Manager");
        switch (userChoice) {
            case 1:
                order.setDeliveryMethod("Personal pickup");
                break;
            case 2:
                order.setDeliveryMethod("DPD");
                break;
            case 3:
                order.setDeliveryMethod("Traditional Postal Service");
                break;
            case 4:
                order.setDeliveryMethod("Parcel Locker Delivery");
                break;
        }
    }

    public void askForPaymentMethod() {
        int userChoice = inputManger.askForMenuOption(paymentMethods, "Payement Manager");
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
            order.activateDiscount();
        }
    }

    public String calculateTimeOfArrival() {
        return formatter.format(today.plusDays(1));
    }

    public void finalisePurchase() {
        //orderDAO.addOrder(order);
        purchaseControllerView.print("Your order has been successfully placed!\nThank you for choosing " +
                "our exclusive Toy Store!\nWe hope to see you soon!");
    }
}