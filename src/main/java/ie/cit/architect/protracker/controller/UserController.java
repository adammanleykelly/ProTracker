package ie.cit.architect.protracker.controller;

import ie.cit.architect.protracker.model.*;

/**
 * Created by brian on 24/03/17.
 */
public class UserController {

    private static UserController instance;
    private IProject project;



    private UserController() {}


//    // singleton
    public static UserController getInstance() {
        if(instance == null) {
            instance = new UserController();
        }
        return instance;
    }


    public Project createProject(String name, String author, String location, String client) {

        return new Project(name, author, location, client);

    }


    public IUser createClientUser(String emailAddress, String password) {
        return new ClientUser(emailAddress, password);
    }


    public IUser createEmployeeUser(String emailAddress, String password) {
        return new EmployeeUser(emailAddress, password);
    }


    public String getProject() {
        return project.getLocation();
    }



}
