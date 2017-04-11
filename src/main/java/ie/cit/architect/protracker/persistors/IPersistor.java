package ie.cit.architect.protracker.persistors;

import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.ProjectList;
import ie.cit.architect.protracker.model.UserList;

import java.util.ArrayList;

/**
 * Created by brian on 3/3/2017.
 */
public interface IPersistor {

    void writeUsers(UserList users);

    void writeProjects(ProjectList projects);

    ArrayList<Project> getProjectNameList();

    void deleteProject(String projectName);

    void updateProject(String currentProjectName, String upDatedProjectName);


}
