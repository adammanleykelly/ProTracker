package ie.cit.architect.protracker.model;

/**
 * Created by brian on 06/02/17.
 */
public class UserBuilder extends User {

    private String name;
    private String password;
    private String emailAddress;


    public UserBuilder(String name, String userName, String emailAddress) {
        super(name, userName, emailAddress);
        this.name = name;
        this.password = userName;
        this.emailAddress = emailAddress;
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
        return "I'm an Builder User";
    }

    @Override
    public String toString() {
        return "UserClient{" +
                "name='" + this.name + '\'' +
                ", password='" + this.password + '\'' +
                ", emailAddress='" + this.emailAddress + '\'' +
                '}';
    }
}
