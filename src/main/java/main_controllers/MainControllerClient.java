package main_controllers;

import basic.product.ProductJDBCDAOClient;
import basic.user.User;
import basic.user.UserDAO;
import controllers.RunnableController;
import exceptions.MyExceptions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainControllerClient extends MainController implements RunnableController {

    public MainControllerClient(User user, UserDAO userDAO) {
        super(user, userDAO);
    }

    @Override
    void reactToUserChoice(int choice) throws SQLException, MyExceptions {
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

    @Override
    void initializeMenu() {
        menuOptions = new ArrayList<>(Arrays.asList("Browse products", "See your basket", "Finalize purchase",
                "Manage newsletter preferences", "Log out"));
    }
}
