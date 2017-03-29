package ie.cit.architect.protracker.controller;

import ie.cit.architect.protracker.model.IProject;
import ie.cit.architect.protracker.model.IUser;
import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.User;

/**
 * Created by brian on 24/03/17.
 */
public class Controller {

    private static Controller instance;
    private IProject project;
    private IUser user;


    private Controller() {}


    // singleton
    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }


    public Project createProject(String name, String date, String author, String location, String client) {

        return new Project(name, date, author, location, client);

    }


    public IUser createUser(String emailAddress, String password) {

        return new User(emailAddress, password);
    }


    public String getProject() {
        return project.getLocation();
    }



}
