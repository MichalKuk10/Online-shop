package main_controllers;

import basic.product.ProductJDBCDAOAdmin;
import basic.user.User;
import basic.user.UserDAO;
import controllers.OfferController;

import java.sql.SQLException;

public class MainControllerAdmin extends MainController {
    private OfferController offerController;
    private ProductJDBCDAOAdmin productDAOAdmin;

    public MainControllerAdmin(User user, UserDAO userDAO) {
        super(user, userDAO);
        this.productDAOAdmin = new ProductJDBCDAOAdmin();
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
        menuOptions = new String[]{"Browse products", "See your basket", "Finalize purchase", "Manage newsletter preferences", "Add/delete products", "Log out"};
    }
}
