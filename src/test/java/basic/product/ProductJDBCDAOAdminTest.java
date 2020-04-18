package basic.product;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductJDBCDAOAdminTest {
    @Test
    public void shouldCheckIfProductAddedToDatabase() throws SQLException {
        //given
        ProductJDBCDAOAdmin prod = new ProductJDBCDAOAdmin();
        Product product = new Product("GreenToy", 2, "Toyland", (float) 12.22, 2);

        //when
        boolean result = prod.insertNewProduct(product);

        //then
        assertTrue(result);
    }

}
