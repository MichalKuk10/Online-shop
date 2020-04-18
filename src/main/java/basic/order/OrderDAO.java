package basic.order;

import java.sql.SQLException;

public interface OrderDAO {

    void insertOrder(Order order, int userId) throws SQLException;

}
