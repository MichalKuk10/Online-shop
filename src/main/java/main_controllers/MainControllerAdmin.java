package main_controllers;

import basic.product.ProductJDBCDAOAdmin;
import basic.user.User;
import basic.user.UserDAO;
import controllers.OfferController;
import controllers.RunnableController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainControllerAdmin extends MainController implements RunnableController {
    private final OfferController offerController;

    public MainControllerAdmin(User user, UserDAO userDAO) {
        super(user, userDAO);
        ProductJDBCDAOAdmin productDAOAdmin = new ProductJDBCDAOAdmin();
        this.offerController = new OfferController(productDAOAdmin);
    }

    @Override
    void reactToUserChoice(int choice) throws SQLException {
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
            case 5:
                offerController.run();
                break;
        }
    }

    @Override
    void initializeMenu() {
        menuOptions = new ArrayList<>(Arrays.asList("Browse products", "See your basket", "Finalize purchase",
                "Manage newsletter preferences", "Add/delete products", "Log out"));
    }
}
