package ie.cit.architect.protracker.model;

/**
 * Created by brian on 06/02/17.
 */
public class UserEmployee extends User {

    private String password;
    private String emailAddress;

    public UserEmployee(String password, String emailAddress) {
        super(password, emailAddress);
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public static UserEmployee createUserEmployee(String emailAddress, String password) {
        return new UserEmployee(emailAddress, password);
    }

    @Override
    public String speak() {
        return "I'm a employee user";
    }


    @Override
    public String toString() {
        return "UserEmployee{" +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
