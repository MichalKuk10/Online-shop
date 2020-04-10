package controllers;

import input_manager.InputManager;

public class AccessController {

    // example code to show how relations between main controller and other controllers might look like:

    private final String[] menuOptions = {"Print 'blabla'", "Print 'dupa'", "Print 'nothing'", "Quit"};
    private final InputManager input = new InputManager();

    public void accessControllerMenu() {
        int choice = input.askForMenuOption(menuOptions, "Access controller menu");
        reactToUserInput(choice);
        while (choice != menuOptions.length) {
            choice = input.askForMenuOption(menuOptions, "Access controller menu");
            reactToUserInput(choice);
        }
    }

    private void reactToUserInput(int number) {
        switch(number) {
            case 1:
                System.out.println("blabla");
                break;
            case 2:
                System.out.println("dupa");
                break;
            case 3:
                System.out.println("nothing");
                break;
        }
    }
}
