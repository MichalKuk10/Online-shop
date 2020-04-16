package main_controllers;

import basic.user.User;

import java.sql.SQLException;

public class MainControllerClient extends MainController {

    public MainControllerClient(User user) {
        super(user);
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
        }
    }
}
