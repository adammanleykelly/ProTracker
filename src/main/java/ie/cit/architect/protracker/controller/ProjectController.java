package ie.cit.architect.protracker.controller;

import ie.cit.architect.protracker.gui.CreateNewProjectScene;
import ie.cit.architect.protracker.model.Project;

/**
 * Created by brian on 24/03/17.
 */
public class ProjectController {

    private static ProjectController instance;
    private CreateNewProjectScene view;
    private Project project;


    public ProjectController() {
        this.view = new CreateNewProjectScene();
        this.project = new Project();
    }



    public static ProjectController getInstance() {
        if(instance == null) {
            instance = new ProjectController();
        }
        return instance;
    }




    public Project createProject(String name, String date, String author, String location, String client) {
        project = new Project(name, date, author, location, client);
        return project;
    }


    public String getProject() {
        return project.getLocation();
    }





}
