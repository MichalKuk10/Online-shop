package basic.product;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;

public class ProductDAOAdmin implements ProductDAOInterface {

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
                    rs.getInt("min_age"));

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

    public boolean insertNewProduct(Product product) throws SQLException {
        ConnectionFactory conn = new ConnectionFactory();
        Connection connection = conn.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products (productId, name, brand, price, minAge )" +
                    "VALUES (" + product.getProductId() + "," + product.getName() + ","  + product.getBrand() + "," +
                    product.getPrice() + "," + product.getMinAge() + ");");
                    statement.executeUpdate();
                    return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            connection.close();
        } }

    public void deleteProductById(int id) throws SQLException {
        ConnectionFactory conn = new ConnectionFactory();
        Connection connection = conn.getConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM products WHERE product_id = " + id + ";");
            System.out.println("Record deleted successfully");
            } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            connection.close();
        }
        }}
