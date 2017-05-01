package ie.cit.architect.protracker.model;

/**
 * Created by brian on 01/05/17.
 */
public interface IClient {

    String getName();

    String getEmailAddress();

    String getPassword();

    boolean isClientEmail(String email);

    boolean isClientPassword(String password);

}
