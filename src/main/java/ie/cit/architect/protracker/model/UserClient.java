package ie.cit.architect.protracker.model;

/**
 * Created by brian on 06/02/17.
 */
public class UserClient extends User {

    private String name;
    private String password;
    private String emailAddress;

    public UserClient(String password, String emailAddress) {
        super(password, emailAddress);
    }

    public static UserClient createClient(String emailAddress, String password) {
        return new UserClient(emailAddress, password);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
