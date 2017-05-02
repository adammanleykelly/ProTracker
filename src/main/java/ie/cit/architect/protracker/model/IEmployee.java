package ie.cit.architect.protracker.model;

/**
 * Created by brian on 01/05/17.
 */
public interface IEmployee {

    String getName();

    String getEmailAddress();

    String getPassword();

    boolean isEmployeeEmail(String email);

    boolean isEmployeePassword(String password);
}
