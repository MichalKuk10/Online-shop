package basic.discount_code;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountCodeJDBCDAO implements DiscountCodeDAO {

    private Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        return factory.getConnection();
    }

    @Override
    public boolean insertDiscountCode(String discountCode) {
        Connection connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO discount_code (discount_code) VALUES (?);");
            statement.setString(1, discountCode);

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
    public boolean updateDiscountCode(int discountCodeId, String updatedDiscountCode) {
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE discount_codes SET discount_code = ? WHERE discount_code_id = ?;");
            statement.setString(1, updatedDiscountCode);
            statement.setInt(2, discountCodeId);

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
    public boolean checkIfDiscountCodeInDatabase(String discountCode) {
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(discount_code_id) from discount_codes where discount_code = ?;");

            statement.setString(1, discountCode);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getInt("count") > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}