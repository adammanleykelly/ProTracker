package ie.cit.architect.protracker.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

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

        Date date = new Date();

        project1 = new Project(
          "house","Der","Cork","Ben"
        );
        project2 = new Project(
                "apart","Jer","Cork","Clare"
        );
        project3 = new Project(
                "church","Der","Cork","Ben"
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