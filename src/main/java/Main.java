import basic.user.UserDAO;
import basic.user.UserJDBCDAO;
import controllers.AccessController;
import main_controllers.MainControllerClient;

import java.nio.channels.AcceptPendingException;

public class Main {
    public static void main(String[] args) {
        DatabaseContentLoader dbcl = new DatabaseContentLoader();
        dbcl.fillDatabase();

        UserDAO userDAO = new UserJDBCDAO();
        AccessController accessController = new AccessController(userDAO);
        accessController.accessControllerMenu();
    }
}
