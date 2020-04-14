import controllers.AccessController;
import main_controllers.MainControllerClient;

import java.nio.channels.AcceptPendingException;

public class Main {
    public static void main(String[] args) {
        DatabaseContentLoader dbcl = new DatabaseContentLoader();
        dbcl.fillDatabase();

        AccessController accessController = new AccessController();
        accessController.accessControllerMenu();
    }
}
