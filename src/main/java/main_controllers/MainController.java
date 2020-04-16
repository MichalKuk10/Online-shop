package main_controllers;

import basic.user.User;
import basic.user.UserJDBCDAO;
import controllers.BasketController;
import controllers.NewsletterController;
import controllers.ProductsController;
import controllers.PurchaseController;
import input_manager.InputManager;

import java.sql.SQLException;

public abstract class MainController {
    private final User user;
    protected final ProductsController productsController;
    protected final BasketController basketController;
    protected final PurchaseController purchaseController;
    protected final NewsletterController newsletterController;
    private final InputManager input;
    private String[] menuOptions;

    public MainController(User user) {
        this.user = user;
        this.purchaseController = new PurchaseController();
        this.basketController = new BasketController(purchaseController);
        this.productsController = new ProductsController(basketController);
        this.newsletterController = new NewsletterController(user, new UserJDBCDAO());
        this.input = new InputManager();
        setMenuOptions();
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

    abstract void reactToUserChoice(int choice) throws SQLException;

    private void setMenuOptions() {
        if (user.getRole().equals("admin")) {
            menuOptions = new String[]{"Browse products", "See your basket", "Finalize purchase", "Manage newsletter preferences", "Add/delete products", "Log out"};
        } else {
            menuOptions = new String[]{"Browse products", "See your basket", "Finalize purchase", "Manage newsletter preferences", "Log out"};
        }
    }
}
