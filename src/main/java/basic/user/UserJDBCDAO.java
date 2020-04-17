package basic.user;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJDBCDAO implements UserDAO {

    private Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        return factory.getConnection();
    }

    @Override
    public boolean insertUser(User user) {
        Connection connection = getConnection();

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
        Connection connection = getConnection();
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
    public boolean checkIfEmailInDatabase(String email) {
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT email FROM users WHERE email = ?;");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkIfPasswordMatchesEmail(String givenEmail, String givenPassword) {
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT password FROM users WHERE email = ?;");
            statement.setString(1, givenEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String dbPassword = resultSet.getString("password");
                if (dbPassword.equals(givenPassword)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        Connection connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?;");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next() && resultSet.getString("password").equals(password)) {
                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getString("email"), resultSet.getString("password"));
        user.setUserId(resultSet.getInt("user_id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setPhoneNumber(resultSet.getString("phone_number"));
        user.setAddress(resultSet.getString("address"));
        user.setRole(resultSet.getString("user_role"));
        user.setAgreedToNewsletter(resultSet.getBoolean("newsletter_subscription"));

        return user;
    }
}
