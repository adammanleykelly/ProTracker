package ie.cit.architect.protracker.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by brian on 14/03/17.
 */
public class ProjectList implements Serializable {

    private ArrayList<Project> projects;

    public ProjectList() {
        this.projects = new ArrayList<>();
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
}
