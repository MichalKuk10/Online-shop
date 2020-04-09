package basic.user;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO implements UserDAOInterface {

    @Override
    public boolean insertUser(User user) {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email, password, user_role, newsletter_subscription) VALUES (?, ?, 'customer', false);");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());

            int i = statement.executeUpdate();

            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean checkIfPasswordMatchesEmail(String email, String password) {
        return false;
    }
}
