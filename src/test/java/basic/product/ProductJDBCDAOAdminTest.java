package basic.product;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductJDBCDAOAdminTest {
    @Test
    public void shouldCheckIfProductAddedToDatabase() throws SQLException {
        //given
        ProductJDBCDAOAdmin prod = new ProductJDBCDAOAdmin();

        //when
        boolean result  = prod.insertNewProduct(2, "GreenToy", "Toyland", 8, 2);

        //then
        assertTrue(result);
    }

}
