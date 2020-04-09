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
    public boolean updateUser(User user) {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET email = ?, password = ?, first_name = ?, last_name = ?, phone_number = ?, address = ?, newsletter_subscription = ? WHERE user_id = ?;");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getAddress());
            statement.setBoolean(7, user.isAgreedToNewsletter());
            statement.setInt(8, user.getUserId());

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
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean checkIfPasswordMatchesEmail(String email, String password) {
        return false;
    }

}
