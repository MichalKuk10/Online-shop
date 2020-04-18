package basic.order;

import basic.product.Product;
import connection.ConnectionFactory;

import java.sql.*;
import java.util.Iterator;
import java.util.Map;

public class OrderJDBCDAO implements OrderDAO {

    private Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        return factory.getConnection();
    }

    @Override
    public void insertOrder(Order order, int userId) throws SQLException {
        int orderId;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (user_id, delivery_method_cost, discount_value) values (?, ?, ?) RETURNING order_id;");
            statement.setInt(1, userId);
            statement.setFloat(2, order.getDeliveryCost());
            statement.setFloat(3, order.getDiscountValue());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                orderId = resultSet.getInt("order_id");
                insertOrderProductRelation(order, orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertOrderProductRelation(Order order, int orderId) throws SQLException {

        Iterator<Map.Entry<Product, Integer>> iterator = order.getProducts().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Product, Integer> entry = iterator.next();

            try (Connection connection = getConnection()) {
                PreparedStatement statement = connection.prepareStatement("insert into order_products (order_id, product_id, quantity) values (?, ?, ?);");
                statement.setInt(1, orderId);
                statement.setFloat(2, entry.getKey().getProductId());
                statement.setFloat(3, entry.getValue());
                statement.executeUpdate();
            }
            catch(SQLException e){
            e.printStackTrace();
        }
    }
    }
}