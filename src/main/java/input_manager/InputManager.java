package input_manager;

import view.View;
import view.MainControllerView;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputManager {
    private View view;

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
        int statNumber = getIntInput("What do you choose? Type the number:");

        while (statNumber < 1 || statNumber > menuOptions.length) {
            statNumber = getIntInput(String.format("Wrong input! type the number between 1 and %d:",
                    menuOptions.length));
        }
        return statNumber;
    }

    public String askForEmail() {
        String email = getStringInput("Please write your email:");

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        while (!matcher.matches()) {
            email = getStringInput("Wrong email format. Please try again:");
            matcher = pattern.matcher(email);
        }
        return email;
    }

    public String askForPassword() {
        String password = getStringInput("Please write your password:");
        int minimalPasswordLength = 6;

        while (password.length() < minimalPasswordLength) {
            password = getStringInput("Wrong! Your password should be at least 6 characters long. Try again:");
        }
        return password;
    }
}
