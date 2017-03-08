package ie.cit.architect.protracker.controller;

import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.User;
import ie.cit.architect.protracker.persistors.IPersistor;

import java.util.ArrayList;

/**
 * Created by brian on 3/3/2017.
 */
public class DBController
{

    private static DBController instance;
    private IPersistor persistor;
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<Project> projectList = new ArrayList<>();

    private DBController() {}

    public static DBController getInstance() {
        if(instance == null) {
            instance = new DBController();
        }
        return instance;
    }


    // user
    public void addUser(User user) { this.userList.add(user); }

    public void saveUser() { this.persistor.writeUsers(this.userList); }

    // projects
    public void addProject(Project project) { this.projectList.add(project); }

    public void saveProject() { this.persistor.writeProjects(this.projectList); }


    // read
    public User readRecords() {
          User user = this.persistor.selectRecords();


          return user;
    }


    public void setPersistor(IPersistor persistor) { this.persistor = persistor; }

}
