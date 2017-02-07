package ie.cit.architect.protracker.model;

/**
 * Created by brian on 06/02/17.
 */
public class User {

    private String name;
    private String userName;
    private String emailAddress;


    public User(String name, String emailAddress, String userName) {
        this.name = name;
        this.userName = userName;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
