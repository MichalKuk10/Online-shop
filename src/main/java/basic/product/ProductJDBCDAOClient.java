package basic.product;

import connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class ProductJDBCDAOClient implements ProductDAO {
    private Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        return factory.getConnection();
    }

    @Override
    public ArrayList<Product> getAllProducts() throws SQLException {
        Connection connection = getConnection();
        ArrayList<Product> productsList = new ArrayList<>();
        ResultSet rs = null;
        Statement statement = null;

        try {
            String query = "SELECT * FROM products;";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("name"),
                        rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getFloat("price"),
                        rs.getInt("age_category"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return productsList;
    }

    @Override
    public Product getProductById(int id) throws SQLException {
        Connection connection = getConnection();
        Product product = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt("product_id"), rs.getString("name"),
                        rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getFloat("price"),
                        rs.getInt("age_category"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return product;
    }

    @Override
    public ArrayList<Product> getProductsByCategory(int categoryId) throws SQLException {
        Connection connection = getConnection();
        ArrayList<Product> productsList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE product_category_id =?");
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("name"),
                        rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getFloat("price"),
                        rs.getInt("age_category"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            connection.close();
        }
        return productsList;
    }
}
