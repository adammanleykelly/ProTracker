package ie.cit.architect.protracker.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotSame;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by brian on 05/04/17.
 */
public class ProjectTest {

    private ArrayList<Project> projects;
    private Project project1, project2, project3;

    @Before
    public void setUp() throws Exception {

        project1 = new Project(
          "house","27/01/2017","Der","Cork","Ben"
        );
        project2 = new Project(
                "apart","15/01/2017","Jer","Cork","Clare"
        );
        project3 = new Project(
                "church","22/01/2017","Der","Cork","Ben"
        );

    }

    @Test
    public void getProjectId() throws Exception {

        assertThat(1, is(project1.getProjectId()));
        assertThat(2, is(project2.getProjectId()));
        assertThat(3, is(project3.getProjectId()));

        assertNotSame(1, project3.getProjectId());


    }

}