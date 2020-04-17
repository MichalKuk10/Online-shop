package view;

import java.util.List;

public class View {
    public void print(String message) {
        System.out.println(message);
    }

    public void printMenu(List<String> menuItems, String title) {
        int index = 1;
        System.out.println(String.format("\n%s:", title.toUpperCase()));
        for (String item : menuItems) {
            System.out.println(String.format("%d. %s", index, item));
            index ++;
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
