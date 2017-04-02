package ie.cit.architect.protracker.persistors;

import com.mongodb.DBObject;
import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.ProjectList;
import ie.cit.architect.protracker.model.UserList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 3/3/2017.
 */
public interface IPersistor {

    void writeUsers(UserList users);

    void writeProjects(ProjectList projects);

    ArrayList<String> selectRecords(ProjectList project);

    void displayCreatedProjects();

    void displayCurrentProject(ProjectList projects);

    List<DBObject> getResults(int limit);

    ArrayList<Project> selectProjectName(ProjectList projectList);



}
