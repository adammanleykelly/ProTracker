package ie.cit.architect.protracker.controller;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import ie.cit.architect.protracker.model.Project;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import static com.mongodb.client.model.Filters.eq;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by brian on 13/04/17.
 */
public class DBControllerTest {

    private MongoCollection collectionEmployees, collectionClientsTest, collectionProjects;
    private MongoDatabase database;
    private static String DB_NAME = "protrackerdev";

    @Before
    public void setUp() throws Exception {
        try {

            // Local database connection
            // Tests run on my laptops local host.
            // Tests also run on my VPS which has a Jenkins Continious Integration setup.
            MongoClient mongoClientConn = new MongoClient("localhost", 27017);
            database = mongoClientConn.getDatabase("mongotest");
            database = mongoClientConn.getDatabase(DB_NAME);

            collectionEmployees = database.getCollection("employees");
            collectionClientsTest = database.getCollection("clients");
            collectionProjects = database.getCollection("projects");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateProjectName() throws Exception {

        // Given
        String currProjectName = "Eastside";

        Project project = new Project(currProjectName);

        Document document = new Document();
        document.put("name", project.getName());

        collectionProjects.insertOne(document);

        String newProjectName = "Westside";
        try {

           project = DBController.getInstance().updateProjectName(currProjectName, newProjectName);

           assertThat(project.getName(), is("Westside"));

        }catch (Exception e) {
            e.getMessage();
        }


        String updatedProjectName = null;

        // When  (testing the implementation)
        collectionProjects.updateOne(eq("name", currProjectName),
                new Document("$set", new Document("name", newProjectName)));


        // find the documents updated value and pass is to the String newProjectName
        FindIterable<Document> databaseRecords = database.getCollection("projects").find();
        MongoCursor<Document> cursor = databaseRecords.iterator();

        try {
            updatedProjectName = cursor.next().getString("name");
            project.setName(updatedProjectName);
        } finally {
            cursor.close();
        }


        // Then
        assertThat(updatedProjectName, is("Westside"));
        assertThat(updatedProjectName, is(not("Eastside")));

        assertThat(collectionProjects.count(), is(1L));

        // cleanup
        collectionProjects.deleteOne(eq("name", updatedProjectName));

    }

}