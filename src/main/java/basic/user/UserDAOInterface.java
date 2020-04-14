package basic.user;

public interface UserDAOInterface {
    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean checkIfEmailInDatabase(String email);

    User getUserByEmailAndPassword(String email, String password);

    boolean checkIfPasswordMatchesEmail(String email, String password);

}
