import basic.user.User;
import basic.user.UserDAO;

public class Main {
    public static void main(String[] args) {
        DatabaseContentLoader dbcl = new DatabaseContentLoader();
        dbcl.fillDatabase();

        User user = new User("test@email.pl", "fgfgfgfg");
        UserDAO dao = new UserDAO();
        dao.insertUser(user);
    }
}
