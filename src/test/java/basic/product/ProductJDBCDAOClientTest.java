package basic.product;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductJDBCDAOClientTest {

    @Test
    public void shouldCheckIfReturnProductById() throws SQLException {
        //given
        ProductJDBCDAOClient prod = new ProductJDBCDAOClient();
        Product product = new Product(20, "Party hat", 10, "LG-Imports", (float) 2,5);

        //when
        Product result  = prod.getProductById(20);

        //then

        assertEquals(product, result);
    }

}