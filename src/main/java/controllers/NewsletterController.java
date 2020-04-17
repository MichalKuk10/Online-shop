package controllers;

import basic.user.User;
import basic.user.UserDAO;
import input_manager.InputManager;
import view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsletterController implements RunnableController {
    private final List<String> menuOptions = new ArrayList<>(Arrays.asList("Change newsletter preferences", "Back to main menu"));
    private final User user;
    private final UserDAO userDAO;
    private final InputManager input;
    private final View view;
    private boolean isRunning = true;

    public NewsletterController(User user, UserDAO userDAO) {
        this.user = user;
        this.userDAO = userDAO;
        this.input = new InputManager();
        this.view = new View();
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            shareCurrentPreferences();
            int choice = input.askForMenuOption(menuOptions, "Preferences menu");
            reactToUserChoice(choice);
        }
    }

    private void shareCurrentPreferences() {
        if (user.isAgreedToNewsletter()) {
            view.print("You are signed up for our newsletter");
        } else {
            view.print("You're not signed up for our newsletter");
        }
    }

    private void reactToUserChoice(int choice) {
        switch (choice) {
            case 1:
                user.setAgreedToNewsletter(!user.isAgreedToNewsletter());
                userDAO.updateUser(user);
                view.print("Thanks for your choice! We value your privacy.");
                break;
            case 2:
                isRunning = false;
                break;
        }
    }
}
