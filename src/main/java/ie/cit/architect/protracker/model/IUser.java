package ie.cit.architect.protracker.model;

/**
 * Created by brian on 07/02/17.
 */
public interface IUser {

    // project

    void setProject(IProject project);

    IProject getProject();

    String getProjectName();

    String getProjectDate();

    String getProjectAuthor();

    String getProjectLocation();

    String getProjectClient();



    // user

    String getName();

    String getEmailAddress();

    String getPassword();
}
