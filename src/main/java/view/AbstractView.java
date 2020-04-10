package view;

public abstract class AbstractView {
    public void print(String message) {
        System.out.println(message);
    }

    public void printMenu(String[] menuItems, String title) {
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
