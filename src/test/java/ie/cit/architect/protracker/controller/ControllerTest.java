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

    private String projectName = "House";
    private String projectClient = "John Murphy";
    private String projectAuthor = "Dermot Coveney";
    private String projectLocation = "Cork";
    private double projectFee = 50000.0;
    private String email = "tod@email.com";
    private String password = "passwd";

    private Project project;
    private IUser employee;




    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void createProject() throws Exception {

        project = Controller.getInstance().createProject(
                projectName, projectAuthor, projectLocation , projectClient, projectFee);

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