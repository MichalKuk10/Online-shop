package basic.product;

import exceptions.MyExceptions;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO {

    ArrayList<Product> getAllProducts() throws SQLException;
    Product getProductById(int id) throws SQLException;
    ArrayList<Product> getProductsByCategory(int categoryId) throws SQLException, MyExceptions;

}
