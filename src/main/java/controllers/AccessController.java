package controllers;

import input_manager.InputManager;
import view.AbstractView;
import view.AccessControllerView;

public class AccessController {

    private final String[] menuOptions = {"Log in", "Create new account", "Quit"};
    private InputManager input;
    private AbstractView view;

    public AccessController() {
        input = new InputManager();
        view = new AccessControllerView();
    }

    public void purchaseControllerMenu() {
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
        // 1. Ask for email (dorobić metodę w input managerze)
        // 2. Ask for password (dorobić metodę w input managerze)
        // 3. Check if email in database.
        //    - Jeśli nie to dorobić metodę handleIfEmailNotInDatabase(), która prowadzi do rejestracji lub wyłącza program
        //    - Jeśli tak to punkt 4
        // 4. Check if password matches email
        //    - Jeśli nie to spytać 5 razy (może zamknąć w metodę handleWrongPassword(). Po 5 razach wyświetlić komunikat "yu're blocked, contact our customer service"
        // 5. Create user based on email
        // 6. Run main controller (zamknąć w metodę chooseRightControllerForUser())
    }

    private void coordinateRegistrationProcess() {
        // 1. Ask for email (input manager)
        // 2. Check if email already in database
        //    - if yes, wyświetl komunikat i daj opcję poprowadzenia do logowania (metoda handleIfEmailAlreadyInDatabase)
        //    - in not, punkt 3
        // 3. Ask for password (input manager)
        // 4. Ask for password again and see if it matches (optional)
        // 5. Insert user to database (using dao)
        // 6. Print message (registration completed) and run coordinateLoginProcess();
    }
}
