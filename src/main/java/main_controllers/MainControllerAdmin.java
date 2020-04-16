package main_controllers;

import basic.product.ProductJDBCDAOAdmin;
import basic.user.User;
import controllers.OfferController;

import java.sql.SQLException;

public class MainControllerAdmin extends MainController {
    private OfferController offerController;

    public MainControllerAdmin(User user) {
        super(user);
        offerController = new OfferController(new ProductJDBCDAOAdmin());
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
}
