package basic.product;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;

public class ProductDAOAdmin implements ProductDAOInterface {


    private Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        return factory.getConnection();
    }

    @Override
    public ArrayList<Product> getAllProducts() throws SQLException {
        Connection connection = getConnection();
        ArrayList<Product> productsList = new ArrayList<>();
        ResultSet rs;

        try {
            PreparedStatement statement  = connection.prepareStatement("SELECT * FROM products;");
            rs =  statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("name"),
                        rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getDouble("price"),
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

            while(rs.next()) {
                product = new Product(rs.getInt("product_id"), rs.getString("name"),
                        rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getDouble("price"),
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
                        rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getDouble("price"),
                        rs.getInt("age_category"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            connection.close();
        }
        return  productsList;
    }

    public boolean insertNewProduct(int productCategoryId, String name, String brand_name, float price, int age_category) throws SQLException {
        Connection connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products (product_category_id, name, brand_name, price, age_category)" +
                    "VALUES (" + productCategoryId + ",'" + name + "', '"  + brand_name + "'," + price  + "," + age_category + ");");
                    statement.executeUpdate();
                    System.out.println("This product has been added successfully to database! ");
                    return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            connection.close();
        } }

    public void deleteProductById(int id) throws SQLException {
        Connection connection = getConnection();

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
