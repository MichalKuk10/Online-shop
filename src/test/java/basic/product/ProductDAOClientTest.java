package basic.product;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOClientTest {

    @Test
    public void shouldCheckIfReturnProductById() throws SQLException {
        //given
        ProductDAOClient prod = new ProductDAOClient();
        Product product = new Product(20, "Party hat", 10, "LG-Imports", 2,5);

        //when
        Product result  = prod.getProductById(20);

        //then

        assertEquals(product, result);
    }

}