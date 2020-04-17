package basic.user;

import java.sql.SQLException;

public interface UserDAO {
    boolean insertUser(User user) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    boolean checkIfEmailInDatabase(String email) throws SQLException;

    User getUserByEmailAndPassword(String email, String password);

    boolean checkIfPasswordMatchesEmail(String email, String password);

}
