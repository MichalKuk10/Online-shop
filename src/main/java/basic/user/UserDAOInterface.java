package basic.user;

public interface UserDAOInterface {
    boolean insertUser(User user);

    boolean updateUser(User user);

    User getUserByEmail(String email);

    boolean checkIfPasswordMatchesEmail(String email, String password);

}
