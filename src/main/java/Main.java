import basic.user.UserDAO;
import basic.user.UserJDBCDAO;
import controllers.AccessController;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("loadDB")) {
            DatabaseContentLoader dbcl = new DatabaseContentLoader();
            dbcl.fillDatabase();
        }

        UserDAO userDAO = new UserJDBCDAO();
        AccessController accessController = new AccessController(userDAO);
        accessController.run();
    }
}
