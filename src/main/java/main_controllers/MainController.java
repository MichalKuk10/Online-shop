package main_controllers;

import basic.user.User;
import controllers.BasketController;
import controllers.NewsletterController;
import controllers.ProductsController;
import controllers.PurchaseController;
import input_manager.InputManager;

import java.sql.SQLException;

public class MainController {
    private final String[] menuOptions = {"Browse products", "See your basket", "Finalize purchase", "Manage newsletter preferences", "Log out"};
    private final User user;
    private final ProductsController productsController;
    private final BasketController basketController;
    private final PurchaseController purchaseController;
    private final NewsletterController newsletterController;
    private final InputManager input;

    public MainController(User user) {
        this.user = user;
        this.purchaseController = new PurchaseController();
        this.basketController = new BasketController(purchaseController);
        this.productsController = new ProductsController(basketController);
        this.newsletterController = new NewsletterController();
        this.input = new InputManager();
    }

    public void run() {
        try {
            int choice = input.askForMenuOption(menuOptions, "Welcome to our exclusive toy store! What do you want to do?");
            reactToUserChoice(choice);
            while (choice != menuOptions.length) {
                choice = input.askForMenuOption(menuOptions, "Welcome to our exclusive toy store! What do you want to do?");
                reactToUserChoice(choice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void reactToUserChoice(int choice) throws SQLException {
        switch(choice) {
            case 1:
                productsController.run();
                break;
            case 2:
                basketController.run();
                break;
            case 3:
                purchaseController.run();
                break;
            case 4:
                newsletterController.run();
                break;
        }
    }
}
