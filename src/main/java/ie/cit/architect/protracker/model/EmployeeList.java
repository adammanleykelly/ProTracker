package ie.cit.architect.protracker.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by brian on 13/03/17.
 */
public class EmployeeList implements Serializable{

    private ArrayList<IUser> employeeUserUsers;

    public EmployeeList() {
        this.employeeUserUsers = new ArrayList<>();
    }

    public ArrayList<IUser> getEmployeeUserUsers() {
        return employeeUserUsers;
    }

    public void setEmployeeUserUsers(ArrayList<IUser> employeeUserUsers) {
        this.employeeUserUsers = employeeUserUsers;
    }

    public void add(IUser user) { this.employeeUserUsers.add(user); }
}
