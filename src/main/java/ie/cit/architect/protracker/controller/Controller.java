package ie.cit.architect.protracker.controller;

import ie.cit.architect.protracker.model.*;

/**
 * Created by brian on 24/03/17.
 */
public class Controller {

    private static Controller instance;
    private IProject project;



    private Controller() {}


    // singleton
    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
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
