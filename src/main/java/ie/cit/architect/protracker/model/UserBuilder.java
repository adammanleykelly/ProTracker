package ie.cit.architect.protracker.model;

/**
 * Created by brian on 06/02/17.
 */
public class UserBuilder extends User {

    private String name;
    private String password;
    private String emailAddress;


    private UserBuilder(String emailAddress, String userName) {
        super(emailAddress, userName);
        this.emailAddress = emailAddress;
        this.password = userName;
    }

    public static UserBuilder createUserBuilder(String emailAddress, String password) {
        return new UserBuilder(emailAddress, password);
    }


    @Override
    public String speak() {
        return "I'm an Builder User";
    }

    @Override
    public String toString() {
        return "UserBuilder{" +
                ", emailAddress='" + this.emailAddress + '\'' +
                ", password='" + this.password + '\'' +
                '}';
    }
}
