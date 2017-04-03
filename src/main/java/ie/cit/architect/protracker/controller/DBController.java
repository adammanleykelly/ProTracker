package ie.cit.architect.protracker.controller;

import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.ProjectList;
import ie.cit.architect.protracker.model.User;
import ie.cit.architect.protracker.model.UserList;
import ie.cit.architect.protracker.persistors.IPersistor;
import ie.cit.architect.protracker.persistors.MongoDBPersistor;
import ie.cit.architect.protracker.persistors.MySQLPersistor;

import java.util.ArrayList;

import static ie.cit.architect.protracker.controller.PersistenceMode.MONGODB;
import static ie.cit.architect.protracker.controller.PersistenceMode.MYSQL;

/**
 * Created by brian on 3/3/2017.
 */
public class DBController {

    private static DBController instance;
    private PersistenceMode persistenceMode;
    private IPersistor persistor;
    private UserList userList;
    private ProjectList projectList;


    private DBController() {
        this.userList = new UserList();
        this.projectList = new ProjectList();
    }


    public static DBController getInstance() {
        if (instance == null) {
            instance = new DBController();
        }
        return instance;
    }

    public void setPersistenceMode(PersistenceMode mode) {
        if(mode.equals(MYSQL)) {
            this.persistor = new MySQLPersistor();
        }
        else if(mode.equals(MONGODB)) {
            this.persistor = new MongoDBPersistor();
        }
    }

    public void setPersistor(IPersistor persistor) {
        this.persistor = persistor;
    }

    // user
    public User addUser(User user) {
        this.userList.add(user);

        return user;
    }

    public void saveUser() {
        this.persistor.writeUsers(this.userList);
    }

    // projects
    public void addProject(Project project) {
        this.projectList.add(project);
    }

    public void saveProject() {
        this.persistor.writeProjects(this.projectList);
    }


    public ArrayList<String> selectRecords() {
        return (ArrayList<String>) this.persistor.getProjectNameList();

    }


    public void getProjects() {
        this.persistor.displayCreatedProjects();
    }



}























