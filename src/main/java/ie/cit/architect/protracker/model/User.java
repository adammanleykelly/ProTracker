package ie.cit.architect.protracker.model;

import ie.cit.architect.protracker.helpers.Consts;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by brian on 06/02/17.
 */
public abstract class User implements IUser{

    private String name;
    private String password;
    private String emailAddress;
    private ArrayList<User> users;



    public User() {
        this.users = new ArrayList<User>();
    }


    public User(String password, String emailAddress) {
        this.password = password;
        this.emailAddress = emailAddress;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isEmailValid(String eMail) {

        if(eMail != null) {
            Pattern pattern = Pattern.compile(Consts.VALID_EMAIL_REGEX);
            Matcher matcher = pattern.matcher(eMail);
            return matcher.matches();
        }
        return false;
    }




    public boolean isNameValid(String fName) {
        if (getName().matches(Consts.VALID_NAME)) {
            return true;
        }
        return false;
    }


}
