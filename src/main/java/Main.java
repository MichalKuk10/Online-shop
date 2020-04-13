import main_controllers.MainControllerClient;

public class Main {
    public static void main(String[] args) {
        DatabaseContentLoader dbcl = new DatabaseContentLoader();
        dbcl.fillDatabase();

        MainControllerClient mainController = new MainControllerClient();
        mainController.mainMenu(); // or we can call it "run()"?
    }
}
