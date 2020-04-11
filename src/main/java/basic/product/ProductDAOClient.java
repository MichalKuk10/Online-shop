package basic.product;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAOClient implements ProductDAOInterface {
    @Override
    public ArrayList<Product> getAllProducts() throws SQLException {
        ConnectionFactory conn = new ConnectionFactory();
        Connection connection = conn.getConnection();
        ArrayList<Product> productsList = new ArrayList<>();
        ResultSet rs = null;
        Statement statement = null;

        try {
            String query = "SELECT * FROM products;";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("name"),
                        rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getDouble("price"),
                        rs.getInt("min_age"));
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
        ConnectionFactory conn = new ConnectionFactory();
        Connection connection = conn.getConnection();
        Statement statement =  null;
        ResultSet rs = null;
        Product product = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM products WHERE product_id = " + id + ";");
            product = new Product(rs.getInt("product_id"), rs.getString("name"),
                    rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getDouble("price"),
                    rs.getInt("age_category"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return product;
    }

    @Override
    public ArrayList<Product> getProductsByCategory(int categoryId) throws SQLException {
        ConnectionFactory conn = new ConnectionFactory();
        Connection connection = conn.getConnection();
        ArrayList<Product> productsList = new ArrayList<>();
        ResultSet rs = null;

        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM products WHERE product_category_id = " + categoryId + ";");

            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("name"),
                        rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getDouble("price"),
                        rs.getInt("min_age"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            connection.close();
        }
        return  productsList;
    }
}
