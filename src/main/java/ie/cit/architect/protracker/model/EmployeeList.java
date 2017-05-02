package ie.cit.architect.protracker.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by brian on 13/03/17.
 */
public class EmployeeList implements Serializable{

    private ArrayList<EmployeeUser> employeeUserUsers;

    public EmployeeList() {
        this.employeeUserUsers = new ArrayList<>();
    }

    public ArrayList<EmployeeUser> getEmployeeUserUsers() {
        return employeeUserUsers;
    }

    public void setEmployeeUserUsers(ArrayList<EmployeeUser> employeeUserUsers) {
        this.employeeUserUsers = employeeUserUsers;
    }

    public void add(EmployeeUser user) { this.employeeUserUsers.add(user); }
}
