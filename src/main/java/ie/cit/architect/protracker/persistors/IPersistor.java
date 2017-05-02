package ie.cit.architect.protracker.persistors;

import ie.cit.architect.protracker.model.*;

import java.util.ArrayList;

/**
 * Created by brian on 3/3/2017.
 */
public interface IPersistor {

    void writeEmployeeUsers(EmployeeList users);

    void writeClientUsers(ClientList users);

    void writeProjects(ProjectList projects);

    ArrayList<Project> getProjectNameList();

    void deleteProject(Project projectName);

    Project updateProject(String currentProjectName, String upDatedProjectName);

    void writeMessages(ChatMessage chatMessages);

    ChatMessage readMessage();


}
