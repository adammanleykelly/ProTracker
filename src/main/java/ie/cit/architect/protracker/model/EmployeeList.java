package ie.cit.architect.protracker.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by brian on 13/03/17.
 */
public class EmployeeList implements Serializable{

    private ArrayList<Employee> employeeUsers;

    public EmployeeList() {
        this.employeeUsers = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployeeUsers() {
        return employeeUsers;
    }

    public void setEmployeeUsers(ArrayList<Employee> employeeUsers) {
        this.employeeUsers = employeeUsers;
    }

    public void add(Employee user) { this.employeeUsers.add(user); }
}
