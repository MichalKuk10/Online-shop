package basic.user;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserTest {

    @Test
    public void are_default_fields_set_properly() {
        // when:
        User user = new User("test@test.pl", "password");
        // then:
        assertFalse(user.isAgreedToNewsletter());
        assertEquals("customer", user.getRole());
    }
}