package controllers;

import basic.user.User;
import basic.user.UserDAO;
import basic.user.UserJDBCDAO;
import input_manager.InputManager;
import main_controllers.MainControllerAdmin;
import main_controllers.MainControllerClient;
import main_controllers.MainControllerUser;
import view.View;

public class AccessController {

    private final String[] menuOptions = {"Log in", "Create new account", "Quit"};
    private final InputManager input;
    private final View view;
    private final UserDAO userDAO;

    public AccessController(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.input = new InputManager();
        this.view = new View();
    }

    public void accessControllerMenu() {
        int choice = input.askForMenuOption(menuOptions, "Welcome to our exclusive toy store! What do you want to do?");
        reactToUserInput(choice);
        while (choice != menuOptions.length) {
            choice = input.askForMenuOption(menuOptions, "Welcome to our exclusive toy store! What do you want to do?");
            reactToUserInput(choice);
        }
    }

    private void reactToUserInput(int number) {
        switch(number) {
            case 1:
                coordinateLoginProcess();
                break;
            case 2:
                coordinateRegistrationProcess();
                break;
        }
    }

    private void coordinateLoginProcess() {
        String email = input.askForEmail();
        String password = input.askForPassword();

        if (!userDAO.checkIfEmailInDatabase(email)) {
            showMenuIfEmailNotInDatabase();
        }

        while (!userDAO.checkIfPasswordMatchesEmail(email, password)) {
            password = handleWrongPassword(email, password);
        }

        User user = userDAO.getUserByEmailAndPassword(email, password);
        runRightControllerForUser(user);
    }

    private void coordinateRegistrationProcess() {
        String email = input.askForEmail();

        if (userDAO.checkIfEmailInDatabase(email)) {
            view.print("This email is already in our database. You will be redirected to login page");
            coordinateLoginProcess();
        }

        String password = input.askForPassword();
        String repeatedPassword = input.askForPassword();
        comparePasswords(password, repeatedPassword);

        User newUser = new User(email, password);
        userDAO.insertUser(newUser);
        view.print("Your registration process is completed. Please log in to the app");
        coordinateLoginProcess();
    }

    private void showMenuIfEmailNotInDatabase() {
        String[] options = {"Go to registration", "Quit"};
        int choice = input.askForMenuOption(options, "What would you like to do?");
        while (choice != options.length) {
            choice = input.askForMenuOption(menuOptions, "What would you like to do?");
        }
        handleIfEmailNotInDatabase(choice);
    }

    private void handleIfEmailNotInDatabase(int choice) {
        switch(choice) {
            case 1:
                coordinateRegistrationProcess();
                break;
            case 2:
                System.exit(0);
        }
    }

    private String handleWrongPassword(String email, String password) {
        int count = 0;
        while (!userDAO.checkIfPasswordMatchesEmail(email, password) && count <= 5) {
            password = input.askForPassword();
            count ++;
        }
        if (count > 6) {
            showMenuIfWrongPasswordAfterPermittedAttempts();
        }
        // consider other precautions?
        return password;
    }

    private void showMenuIfWrongPasswordAfterPermittedAttempts() {
        String[] options = {"Restart login process", "Quit"};
        int choice = input.askForMenuOption(options, "What would you like to do?");
        while (choice != options.length) {
            choice = input.askForMenuOption(menuOptions, "What would you like to do?");
        }
        handleIfWrongPasswordAfterPermittedAttempts(choice);
    }

    private void handleIfWrongPasswordAfterPermittedAttempts(int choice) {
        switch(choice) {
            case 1:
                accessControllerMenu();
                break;
            case 2:
                System.exit(0);
        }
    }

    private void comparePasswords(String password1, String password2) {
        if (!password1.equals(password2)) {
            view.print("Your passwords don't match. Your registration process will be restarted!");
            coordinateRegistrationProcess();
        }
    }

    private void runRightControllerForUser(User user) {
        MainControllerUser mainController;

        if (user.getRole().equals("admin")) {
            mainController = new MainControllerAdmin();
        } else {
            mainController = new MainControllerClient();
        }
//        mainController.mainMenu(); - uncomment when MainController is ready
    }
}
