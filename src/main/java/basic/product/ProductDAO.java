package basic.product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO {

    public ArrayList<Product> getAllProducts() throws SQLException;
    public Product getProductById(int id) throws SQLException;
    public ArrayList<Product> getProductsByCategory(int categoryId) throws SQLException;

}
