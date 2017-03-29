package ie.cit.architect.protracker.model;

import ie.cit.architect.protracker.helpers.Consts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by brian on 06/02/17.
 */
public class User implements IUser{

    private String name;
    private String password;
    private String emailAddress;


    private IProject project;

    public User() {  }

    public User(String emailAddress) { this.emailAddress = emailAddress; }

    public User(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;

    }

    @Override
    public void setProject(IProject project) {
        this.project = project;
    }

    @Override
    public IProject getProject() {
        return project;
    }

    @Override
    public String getProjectName() {
        return project.getName();
    }

    @Override
    public String getProjectDate() {
        return project.getDate();
    }

    @Override
    public String getProjectAuthor() {
        return project.getAuthor();
    }

    @Override
    public String getProjectLocation() {
        return project.getLocation();
    }

    @Override
    public String getProjectClient() {
        return project.getClientName();
    }

    @Override
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

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    boolean isEmailValid(String eMail) {

        if(eMail != null) {
            Pattern pattern = Pattern.compile(Consts.VALID_EMAIL_REGEX);
            Matcher matcher = pattern.matcher(eMail);
            return matcher.matches();
        }
        return false;
    }


    boolean isNameValid() {
        if (getName().matches(Consts.VALID_NAME)) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
