package basic.user;

public class User {
    int userId;
    String email;
    String password;
    String firstName;
    String lastName;
    String phoneNumber;
    String address;
    String role;
    boolean agreedToNewsletter;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.agreedToNewsletter = false;
        this.role = "customer";
    }

    public User(int userId, String email, String password, String firstName, String lastName, String phoneNumber, String address, String role, boolean agreedToNewsletter) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.agreedToNewsletter = agreedToNewsletter;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAgreedToNewsletter() {
        return agreedToNewsletter;
    }

    public void setAgreedToNewsletter(boolean agreedToNewsletter) {
        this.agreedToNewsletter = agreedToNewsletter;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

