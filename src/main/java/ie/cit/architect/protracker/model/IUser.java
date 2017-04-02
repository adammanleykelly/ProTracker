package ie.cit.architect.protracker.model;

/**
 * Created by brian on 07/02/17.
 */
public interface IUser {

    // project

    void setProject(IProject project);

    IProject getProject();



    // user

    String getName();

    String getEmailAddress();

    String getPassword();
}
