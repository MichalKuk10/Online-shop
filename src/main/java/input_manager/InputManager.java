package input_manager;

import view.AbstractView;
import view.MainControllerView;

import java.util.Scanner;

public class InputManager {
    private AbstractView view;

    public InputManager() {
        view = new MainControllerView();
    }

    public String getStringInput(String message) {
        view.print(message);
        Scanner scannerFromUser = new Scanner(System.in);

        return scannerFromUser.nextLine();
    }

    public int getIntInput(String message) {
        view.print(message);
        Scanner scannerFromUser = new Scanner(System.in);

        while(!scannerFromUser.hasNextInt()){
            view.print("Wrong input! Please insert the correct number");
            scannerFromUser.next();
        }
        return scannerFromUser.nextInt();
    }

    public int askForMenuOption(String[] menuOptions, String menuTitle) {

        view.printMenu(menuOptions, menuTitle);
        int statNumber = getIntInput("What do you choose? Type the number.");

        while (statNumber < 1 || statNumber > menuOptions.length) {
            statNumber = getIntInput(String.format("Wrong input! type the number between 1 and %d:",
                    menuOptions.length));
        }
        return statNumber;
    }
}
