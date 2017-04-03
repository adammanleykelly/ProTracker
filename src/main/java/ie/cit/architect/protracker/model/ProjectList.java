package ie.cit.architect.protracker.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by brian on 14/03/17.
 */
public class ProjectList implements Serializable {

    private ArrayList<Project> projects;
    private ArrayList<Project> projectNames;

    public ProjectList() {
        this.projects = new ArrayList<>();
        this.projectNames = new ArrayList<>();
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void add(Project project) {
        this.projects.add(project);
    }


//  project names
    public ArrayList<Project> getProjectNames() { return projectNames; }

    public void setProjectNames(ArrayList<Project> projectNames) { this.projectNames = projectNames; }

    public void addProjectNames(Project projectNames) {
        this.projectNames.add(projectNames);
    }

}
