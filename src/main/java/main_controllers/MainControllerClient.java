package main_controllers;

import controllers.AccessController;
import input_manager.InputManager;

public class MainControllerClient implements MainControllerUser {

    // example code to show how relations between main controller and other controllers might look like:

    private final AccessController accessController = new AccessController();
    private final String[] menuOptions = {"Go to access controller", "Print some example data", "Quit"};
    private final InputManager input = new InputManager();

    public void mainMenu() {
        int choice = input.askForMenuOption(menuOptions, "Main menu");
        reactToUserInput(choice);
        while (choice != menuOptions.length) {
            choice = input.askForMenuOption(menuOptions, "Main menu");
            reactToUserInput(choice);
        }
    }

    private void reactToUserInput(int number) {
        switch(number) {
            case 1:
                accessController.accessControllerMenu();
                break;
            case 2:
                System.out.println("Some example data");
                break;
        }
    }
}
