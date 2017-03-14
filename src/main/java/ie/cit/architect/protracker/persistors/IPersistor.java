package ie.cit.architect.protracker.persistors;

import ie.cit.architect.protracker.model.ProjectList;
import ie.cit.architect.protracker.model.User;
import ie.cit.architect.protracker.model.UserList;

/**
 * Created by brian on 3/3/2017.
 */
public interface IPersistor {

    void writeUsers(UserList users);

    void writeProjects(ProjectList projects);

    User selectRecords();

    void displayProjects(ProjectList projects);

}
