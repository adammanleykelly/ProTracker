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


    public IClient createClientUser(String emailAddress, String password) {
        return new Client(emailAddress, password);
    }


    public IEmployee createEmployeeUser(String emailAddress, String password) {
        return new Employee(emailAddress, password);
    }


    public String getProject() {
        return project.getLocation();
    }



}
