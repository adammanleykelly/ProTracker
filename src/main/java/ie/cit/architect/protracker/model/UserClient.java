package ie.cit.architect.protracker.model;

/**
 * Created by brian on 06/02/17.
 */
public class UserClient extends User {

    private String name;
    private String password;
    private String emailAddress;

    private UserClient(String emailAddress, String password) {
        super(password, emailAddress);
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public static UserClient createClient(String emailAddress, String password) {
        return new UserClient(emailAddress, password);
    }


    @Override
    public String speak() {
        return "I'm an Client User";
    }

    @Override
    public String toString() {
        return "UserClient{" +
                ", emailAddress='" + this.emailAddress + '\'' +
                ", password='" + this.password + '\'' +
                '}';
    }
}
