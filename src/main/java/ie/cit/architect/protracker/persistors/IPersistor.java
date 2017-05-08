package ie.cit.architect.protracker.persistors;

import com.mongodb.client.MongoCollection;
import ie.cit.architect.protracker.model.*;

import java.util.ArrayList;

/**
 * Created by brian on 3/3/2017.
 */
public interface IPersistor {

    void writeEmployeeUsers(EmployeeList users);

    void writeClientUsers(ClientList users);

    void writeProjects(ProjectList projects);

    ArrayList<Project> createProjectList();

    Project readProject(String projectName);

    void deleteProject(Project projectName);

    Project updateProjectName(String currentProjectName, String upDatedProjectName);

    Project updateProjectFee(double currentFee, double updatedFee);


    void writeMessages(ChatMessage chatMessages);

    ChatMessage readMessage();

    Project readProject(MongoCollection collection);

    void deleteUser(IUser user);


}
