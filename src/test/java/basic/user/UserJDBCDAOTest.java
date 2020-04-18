package basic.user;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserJDBCDAOTest {

    private UserDAO userDAO = new UserJDBCDAO();

    @Test
    public void should_evaluate_email_and_password_correctness() throws SQLException {
        // given:
        String email = "test@test.pl";
        String password = "password";
        User user = new User(email, password);
        // when:
        userDAO.insertUser(user);
        // then:
        assertTrue(userDAO.checkIfEmailInDatabase(email));
        assertFalse(userDAO.checkIfEmailInDatabase("wrongEmail"));
        assertTrue(userDAO.checkIfPasswordMatchesEmail(email, password));
        assertFalse(userDAO.checkIfPasswordMatchesEmail(email, "wrongPassword"));
    }
}