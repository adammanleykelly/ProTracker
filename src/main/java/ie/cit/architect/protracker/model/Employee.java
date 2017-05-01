package ie.cit.architect.protracker.model;

import ie.cit.architect.protracker.helpers.Consts;

/**
 * Created by brian on 01/05/17.
 */
public class Employee extends User implements IEmployee{

    private String name;
    private String password;
    private String emailAddress;

    public Employee() {
    }

    public Employee(String emailAddress) {
        super(emailAddress);
    }

    public Employee(String emailAddress, String password) {
        super();
        this.emailAddress = emailAddress;
        this.password = password;
    }

    @Override
    public boolean isEmployeeEmail(String email) {
        if(emailAddress.equals(Consts.MANAGER_EMAIL) || emailAddress.equals(Consts.EMPLOYEE_EMAIL)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmployeePassword(String password) {
        if (password.equals(Consts.EMPLOYEE_PASS)) {
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
