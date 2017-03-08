package ie.cit.architect.protracker.model;

/**
 * Created by brian on 06/02/17.
 */
public class UserArchitect extends User {

    private String password;
    private String emailAddress;


    public UserArchitect(String emailAddress, String password) {
        super(emailAddress, password);
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public UserArchitect(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public static UserArchitect getInstance(String emailAddress, String password) {
        return new UserArchitect(emailAddress, password);
    }

    public static UserArchitect getInstance(String emailAddress) {
        return new UserArchitect(emailAddress);
    }

    @Override
    public String speak() {
        return "I'm an Architect User";
    }


    @Override
    public String toString() {
        return "UserArchitect{" +
                ", emailAddress='" + this.emailAddress + '\'' +
                ", password='" + this.password + '\'' +
                '}';

    }
}
