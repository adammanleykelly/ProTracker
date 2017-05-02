package ie.cit.architect.protracker.persistors;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import ie.cit.architect.protracker.model.*;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;


/**
 * Created by brian on 11/04/17.
 */
public class MongoDBPersistorTest {

    private MongoCollection collectionEmployees, collectionClientsTest, collectionProjects;
    private MongoDatabase database;

    private static String DB_MONGO_PASS = "topdog12";
    private static String DB_MONGO_USER = "developmentuser";
    private static String DB_MONGO_IP = "ec2-54-202-69-181.us-west-2.compute.amazonaws.com";
    private static String DB_NAME = "protrackerdev";

    private MongoDBPersistor mongoDBPersistor;
    private User mUser;


    @Before
    public void setUp() throws Exception {

        mongoDBPersistor = new MongoDBPersistor();

        // local database
//        MongoClient mongoClientConn = new MongoClient("localhost", 27017);
//        database = mongoClientConn.getDatabase("mongotest");

        try {
            // remote database
            String mongoURI = "mongodb://" + DB_MONGO_USER + ":" + DB_MONGO_PASS + "@" +
                    DB_MONGO_IP + "/" + DB_NAME;

            MongoClient mongoClientConn = new MongoClient(new MongoClientURI(mongoURI));

            database = mongoClientConn.getDatabase(DB_NAME);

            collectionEmployees = database.getCollection("employees");

            collectionClientsTest = database.getCollection("clients");

            collectionProjects = database.getCollection("projects");

        } catch (MongoException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void writeOneUser() throws Exception {

        // Given
        IEmployee employee = new Employee("pete@email.com", "passwd");
        Document document = new Document();
        document.put("email", employee.getEmailAddress());
        document.put("password", employee.getPassword());

        // When
        collectionEmployees.insertOne(document);

        // Then
        assertThat(collectionEmployees.count(), is(1L));

        collectionEmployees.deleteOne(eq("email", "pete@email.com"));


    }


    @Test
    public void writeManyUsers() throws Exception {

        // Given
        Employee user1 = new Employee("frank@email.com", "passwd");
        Document document1 = new Document();
        document1.put("_id", 11);
        document1.put("email", user1.getEmailAddress());
        document1.put("password", user1.getPassword());

        Employee user2 = new Employee("grace@email.com", "mpass");
        Document document2 = new Document();
        document2.put("_id", 22);
        document2.put("email", user2.getEmailAddress());
        document2.put("password", user2.getPassword());

        List<Document> docList = new ArrayList<>();
        docList.add(document1);
        docList.add(document2);

        // When
        collectionEmployees.insertMany(docList);

        // Then
        assertThat(collectionEmployees.count(), is(2L));


        // Given
        EmployeeList employeeList = new EmployeeList();
        employeeList.add(user1);
        employeeList.add(user2);

        // When
        mongoDBPersistor.writeEmployeeUsers(employeeList);

        mUser = mongoDBPersistor.getDbUser(user1);

        // Then
        assertTrue("Expected not null ", mUser != null);
        assertEquals("frank@email.com", mUser.getEmailAddress());
        assertEquals("passwd", mUser.getPassword());


        // clean up
        collectionEmployees.deleteOne(eq("email", "frank@email.com"));
        collectionEmployees.deleteOne(eq("email", "grace@email.com"));

    }


    @Test
    public void writeClientUser(){

        Client client1 = new Client("ben@email.com", "passwd");
        ClientList clientList = new ClientList();
        clientList.add(client1);


        // This is the same as MongoDBPersistor#writeClientUsers(ClientList clientUsers)
        // If we called the method from MongoDBPersistor, then we would be writing to the production database.
        // Here we are writing to our test database.
        Document document = new Document();
        try {
            for (IClient currClientUser : clientList.getClients()) {
                document.put("email", currClientUser.getEmailAddress());
                document.put("password", currClientUser.getPassword());
            }

            collectionClientsTest.insertOne(document);

        } catch (MongoException e) {
            e.printStackTrace();
        }

        // verifies count of Client Collection is 1
        assertThat(collectionClientsTest.count(), is(1L));

        // clean up
        collectionClientsTest.deleteOne(eq("email", "ben@email.com"));

    }


    @Test
    public void writeEmployeeUser(){

        Employee employee = new Employee("mary@email.com", "passwd");
        EmployeeList employeeList = new EmployeeList();
        employeeList.add(employee);


        // This is the same as MongoDBPersistor#writeClientUsers(ClientList clientUsers)
        // If we called the method from MongoDBPersistor, then we would be writing to the production database.
        // Here we are writing to our test database.
        Document document = new Document();
        try {
            for (IEmployee currEmployeeUser : employeeList.getEmployeeUsers()) {
                document.put("email", currEmployeeUser.getEmailAddress());
                document.put("password", currEmployeeUser.getPassword());
            }

            collectionEmployees.insertOne(document);

        } catch (MongoException e) {
            e.printStackTrace();
        }

        // verifies count of Client Collection is 1
        assertThat(collectionEmployees.count(), is(1L));

        // clean up
        collectionEmployees.deleteOne(eq("email", "mary@email.com"));

    }


    @Test
    public void updateProject() throws Exception {

        // Given
        String currProjectName = "House";
        String newProjectName = "Apartment";
        String updatedProjectName = null;

        /** the project's object name is first set to "House" **/
        Project project = new Project(currProjectName);

        Document document = new Document();
        document.put("name", project.getName());

        collectionProjects.insertOne(document);


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



        //**********************************************************************

        Project project1;

        // When  (testing the actual method)

        /** the project's object name is then updated to "Apartment" **/
        project1 = mongoDBPersistor.updateProject(currProjectName, newProjectName);

        // Then
        assertThat(project1.getName(), is("Apartment"));


        // cleanup
        collectionProjects.deleteOne(eq("name", updatedProjectName));

    }


}




























