package basic.user;

public interface UserDAOInterface {
    boolean insertUser(User user);

    void updateUser(User user);

    User getUserByEmail(String email);

    boolean checkIfPasswordMatchesEmail(String email, String password);
    
}
