package ie.cit.architect.protracker.persistors;

import ie.cit.architect.protracker.model.ProjectList;
import ie.cit.architect.protracker.model.UserList;

import java.util.List;

/**
 * Created by brian on 3/3/2017.
 */
public interface IPersistor {

    void writeUsers(UserList users);

    void writeProjects(ProjectList projects);

    void displayCreatedProjects();

    void displayCurrentProject(ProjectList projects);


    List<String> getProjectNameList();



}
