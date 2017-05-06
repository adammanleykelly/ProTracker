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

    private MongoCollection collectionUsers, collectionProjects;
    private MongoDatabase database;

    private static String DB_MONGO_PASS = "topdog12";
    private static String DB_MONGO_USER = "developmentuser";
    private static String DB_MONGO_IP = "ec2-54-202-69-181.us-west-2.compute.amazonaws.com";
    private static String DB_NAME = "protrackerdev";

    @Before
    public void setUp() throws Exception {
        try {

            // local database
            MongoClient mongoClientConn = new MongoClient("localhost", 27017);
            database = mongoClientConn.getDatabase("mongotest");

            // remote database
//            String mongoURI = "mongodb://" + DB_MONGO_USER + ":" + DB_MONGO_PASS + "@" +
//                    DB_MONGO_IP + "/" + DB_NAME;
//            MongoClient mongoClientConn = new MongoClient(new MongoClientURI(mongoURI));

            database = mongoClientConn.getDatabase(DB_NAME);
            collectionUsers = database.getCollection("users");
            collectionProjects = database.getCollection("projects");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateProjectName() throws Exception {

        // Given
        String currProjectName = "House";

        Project project = new Project(currProjectName);

        Document document = new Document();
        document.put("name", project.getName());

        collectionProjects.insertOne(document);

        String newProjectName = "Apartment";
        try {

           project = DBController.getInstance().updateProjectName(currProjectName, newProjectName);

           assertThat(project.getName(), is("Apartment"));

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
        assertThat(updatedProjectName, is("Apartment"));
        assertThat(updatedProjectName, is(not("House")));

        assertThat(collectionProjects.count(), is(1L));

        // cleanup
        collectionProjects.deleteOne(eq("name", updatedProjectName));

    }

}