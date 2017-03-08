package ie.cit.architect.protracker.persistors;

import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.User;

import java.util.ArrayList;

/**
 * Created by brian on 3/3/2017.
 */
public interface IPersistor {


    void writeUsers(ArrayList<User> users);

    void writeProjects(ArrayList<Project> projects);

    User selectRecords();

}
