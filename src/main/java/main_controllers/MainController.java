package main_controllers;

import basic.basket.Basket;
import basic.order.OrderDAO;
import basic.order.OrderJDBCDAO;
import basic.product.ProductDAO;
import basic.product.ProductJDBCDAOClient;
import basic.discount_code.DiscountCodeDAO;
import basic.discount_code.DiscountCodeJDBCDAO;
import basic.user.User;
import basic.user.UserDAO;
import controllers.*;
import exceptions.MyExceptions;
import input_manager.InputManager;

import java.sql.SQLException;
import java.util.List;

public abstract class MainController implements RunnableController {
    protected List<String> menuOptions;
    protected final ProductsController productsController;
    protected final BasketController basketController;
    protected final PurchaseController purchaseController;
    protected final NewsletterController newsletterController;
    private final InputManager input;

    public MainController(User user, UserDAO userDAO) {
        initializeMenu();
        Basket basket = new Basket();
        OrderDAO orderDAO = new OrderJDBCDAO();
        ProductDAO productDAO = new ProductJDBCDAOClient();
        DiscountCodeDAO discountCodeDAO = new DiscountCodeJDBCDAO();
        this.purchaseController = new PurchaseController(user, userDAO, orderDAO, discountCodeDAO);
        this.basketController = new BasketController(basket, purchaseController);
        this.productsController = new ProductsController(basketController, productDAO);
        this.newsletterController = new NewsletterController(user, userDAO);
        this.input = new InputManager();
    }

    @Override
    public void run() {
        try {
            int choice = input.askForMenuOption(menuOptions, "Welcome to our exclusive toy store! What do you want to do?");
            reactToUserChoice(choice);
            while (choice != menuOptions.size()) {
                choice = input.askForMenuOption(menuOptions, "Welcome to our exclusive toy store! What do you want to do?");
                reactToUserChoice(choice);
            }
        } catch (SQLException | MyExceptions e) {
            e.printStackTrace();
        }
    }

    abstract void reactToUserChoice(int choice) throws SQLException, MyExceptions;

    abstract void initializeMenu();
}
