package ie.cit.architect.protracker.controller;

import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.model.IUser;
import ie.cit.architect.protracker.model.Project;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by brian on 05/05/17.
 */
public class ControllerTest {


    private String email = "tod@email.com";
    private String password = "passwd";

    private Project project;
    private IUser employee;
    private IUser client;


    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void isEmployeeUserEmailValid() throws Exception {
        boolean isValid = Controller.getInstance().isEmployeeUserEmailValid(Consts.MANAGER_EMAIL_1);
        boolean isFalse = Controller.getInstance().isEmployeeUserEmailValid(email);
        assertEquals(isValid, true);
        assertEquals(isFalse, false);
    }

    @Test
    public void isClientUserEmailValid() throws Exception {
        boolean isValid = Controller.getInstance().isClientUserEmailValid(Consts.CLIENT_EMAIL_1);
        boolean isFalse = Controller.getInstance().isEmployeeUserEmailValid(email);
        assertEquals(isValid, true);
        assertEquals(isFalse, false);
    }

    @Test
    public void isUserPasswordValid() throws Exception {
        boolean isValid = Controller.getInstance().isUserPasswordValid(Consts.USER_PASS);
        boolean isFalse = Controller.getInstance().isEmployeeUserEmailValid(password);
        assertEquals(isValid, true);
        assertEquals(isFalse, false);
    }


    @Test
    public void createClientUser() throws Exception {
        client = Controller.getInstance().createClientUser(email, password);
        assertTrue(client != null);
        assertEquals(client.getEmailAddress(), email);
        assertEquals(client.getPassword(), password);
    }


    @Test
    public void createProject() throws Exception {

        String projectName = "School";
        String projectClient = "John Murphy";
        String projectAuthor = "Dermot Coveney";
        String projectLocation = "Cork";
        double projectFee = 50000.0;


        project = Controller.getInstance().createProject(
                projectName, projectAuthor, projectLocation, projectClient, projectFee);

        assertTrue(project != null);
        assertEquals(project.getName(), projectName);
        assertEquals(project.getAuthor(), projectAuthor);
        assertEquals(project.getLocation(), projectLocation);
        assertEquals(project.getClientName(), projectClient);

    }


    @Test
    public void createEmployeeUser() {
        employee = Controller.getInstance().createEmployeeUser(Consts.MANAGER_EMAIL_1, Consts.USER_PASS);
        assertTrue(employee != null);
        assertThat(employee.getEmailAddress(), is(Consts.MANAGER_EMAIL_1));
        assertThat(employee.getPassword(), is(Consts.USER_PASS));
        assertThat(employee.getEmailAddress(), is(not(Consts.MANAGER_EMAIL_2)));

    }
}