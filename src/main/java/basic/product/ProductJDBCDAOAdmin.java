package basic.product;

import exceptions.MyExceptions;
import connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class ProductJDBCDAOAdmin implements ProductDAO {

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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products;");
            rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("name"),
                        rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getBigDecimal("price"),
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
                        rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getBigDecimal("price"),
                        rs.getInt("age_category"));
            }
        } catch (SQLException e) {
            System.out.println("Error: Please try again!");
        } finally {
            connection.close();
        }
        return product;
    }

    @Override
    public ArrayList<Product> getProductsByCategory(int categoryId) throws MyExceptions, SQLException {
        Connection connection = getConnection();
        ArrayList<Product> productsList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE product_category_id =?");
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("name"),
                        rs.getInt("product_category_id"), rs.getString("brand_name"), rs.getBigDecimal("price"),
                        rs.getInt("age_category"));
                productsList.add(product);
            }
        } finally {
            connection.close();
        }
        return productsList;
    }

    public boolean insertNewProduct(Product product) throws SQLException {
        Connection connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products (product_category_id, " +
                    "name, brand_name, price, age_category) VALUES (?, ?, ?, ?, ?);");
            statement.setInt(1, product.getCategoryId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getBrand());
            statement.setBigDecimal(4, product.getPrice());
            statement.setInt(5, product.getMinAge());
            statement.executeUpdate();
            System.out.println("This product has been added successfully to database! ");
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to insert new product, please try again");
        } finally {
            connection.close();
        }
        return false;
    }

    public void deleteProductById(int id) throws SQLException {
        Connection connection = getConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM products WHERE product_id = " + id + ";");
            System.out.println("Product deleted successfully");
        } catch (SQLException e) {
            System.out.println("Error: please try again");
        } finally {
            connection.close();
        }
    }

    public void modifyProduct(Product product, int id) throws SQLException {
        Connection connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE products SET product_category_id = ?," +
                    " name  = ?, brand_name = ?, price = ?, age_category = ? WHERE product_id = ?;");
            statement.setInt(1, product.getCategoryId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getBrand());
            statement.setBigDecimal(4, product.getPrice());
            statement.setInt(5, product.getMinAge());
            statement.setInt(6, id);
            statement.executeUpdate();
            System.out.println(" Information about this product updated ");

        } catch (SQLException e) {
            System.out.println("Error: Please try again!");
        } finally {
            connection.close();
        }
    }
}

