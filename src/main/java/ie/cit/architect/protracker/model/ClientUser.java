package ie.cit.architect.protracker.model;

import ie.cit.architect.protracker.helpers.Consts;

/**
 * Created by brian on 01/05/17.
 */
public class ClientUser implements IUser{

    private String name;
    private String password;
    private String emailAddress;


    public ClientUser() {
    }


    public ClientUser(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    public ClientUser(String emailAddress, String password) {
        super();
        this.emailAddress = emailAddress;
        this.password = password;
    }


    @Override
    public boolean validateEmailCredentials(String email) {
        if(emailAddress.equals(Consts.CLIENT_EMAIL_1)
                || emailAddress.equals(Consts.CLIENT_EMAIL_2)
                || emailAddress.equals(Consts.CLIENT_EMAIL_3)) {
            return true;
        }
        return false;
    }


    @Override
    public boolean validatePasswordCredentials(String password) {
        if (password.equals(Consts.USER_PASS)) {
            return true;
        }
        return false;
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


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
