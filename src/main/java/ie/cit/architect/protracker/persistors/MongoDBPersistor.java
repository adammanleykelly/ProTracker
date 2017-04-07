package ie.cit.architect.protracker.persistors;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import ie.cit.architect.protracker.helpers.Credentials;
import ie.cit.architect.protracker.model.IUser;
import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.ProjectList;
import ie.cit.architect.protracker.model.UserList;
import org.bson.Document;

import java.util.*;


/**
 * Created by brian on 13/03/17.
 */
public class MongoDBPersistor implements IPersistor {

    private MongoClient mongoClientConn;
    private MongoCollection collectionUsers, collectionProjects;
//    private DB database;
    private MongoDatabase database;

    private static int count = 0;



    public MongoDBPersistor() {

        try {

            //local database
            mongoClientConn = new MongoClient("localhost", 27017);

            // remote database
//            String mongoURI = "mongodb://" + Credentials.DB_MONGO_USER + ":" + Credentials.DB_MONGO_PASS + "@" +
//                    Credentials.DB_MONGO_IP +"/" + Credentials.DB_NAME;
//
//            mongoClientConn = new MongoClient( new MongoClientURI(mongoURI));


            if(mongoClientConn != null) {
                System.out.println("Connected to MongoDB!");
            } else {
                System.out.println("Connection to MongoDB Failed!");
            }


            //Get Database
            // if database doesn't exist, mongoDB will create it for you
            database = mongoClientConn.getDatabase(Credentials.DB_NAME);


            //Get Collection / Table from 'protracker'
            //if collection doesn't exist, mongoDB will create it for you
            collectionUsers = database.getCollection("users");

            // create another table
            collectionProjects = database.getCollection("projects");


        } catch (MongoException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void writeUsers(UserList users) {

        try {
            for (IUser currUser : users.getUsers()) {
                Document document = new Document();
                //key value pair
                document.put("email", currUser.getEmailAddress());
                document.put("password", currUser.getPassword());
                collectionUsers.insertOne(document);

            }
        } catch (MongoException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeProjects(ProjectList projects) {


        try {
            Document document = new Document();

            for (Project currProject : projects.getProjects()) {

                document.put("project_id", currProject.getProjectId());
                document.put("name", currProject.getName());
                document.put("author", currProject.getAuthor());
                document.put("location", currProject.getLocation());
                document.put("client_name", currProject.getClientName());
                document.put("create_date", currProject.getDate());


                //A unique index ensures that the indexed fields do not store duplicate values
                // https://docs.mongodb.com/v3.2/core/index-unique/
                Document indexOption = new Document();
                indexOption.put("unique", true);
            }
            collectionProjects.insertOne(document);

        }catch (MongoException e) {
            e.printStackTrace();
        }

    }




    @Override
    public ArrayList<Project> getProjectNameList() {

        HashSet<Project> projectNameHashSet = new HashSet<>();
        ArrayList<Project> orderedList = new ArrayList();

        try {

            FindIterable<Document> databaseRecords = database.getCollection("projects").find();
            MongoCursor<Document> iterator = databaseRecords.iterator();

            while (iterator.hasNext()) {


                Document document = iterator.next();

                int projectId = document.getInteger("project_id");
                String projectName = document.getString("name");
                Date date = document.getDate("create_date");

                // create project with values set from DB
                Project project = new Project();
                project.setProjectId(projectId);
                project.setName(projectName);
                project.setDate(date);

                projectNameHashSet.add(project);
            }


            for(Project project : projectNameHashSet) {
                orderedList.add(project);
            }


            sortProjectsByDate(orderedList);


        } catch (MongoException e) {
            e.printStackTrace();
        }

        return orderedList;
    }


    private void sortProjectsByDate(ArrayList<Project> list) {
        Collections.sort(list, new Comparator<Project>() {
            @Override
            public int compare(Project p1, Project p2) {

                if(p1.getDate().before(p2.getDate())) {
                    return 1;
                }
                else if(p1.getDate().after(p2.getDate())) {
                    return -1;
                }

                return 0;
            }
        });


//    private void sortProjectsById(ArrayList<Project> list) {
//        Collections.sort(list, new Comparator<Project>() {
//            @Override
//            public int compare(Project p1, Project p2) {
//
//                if(p1.getProjectId() > p2.getProjectId()) {
//                    return 1;
//                }
//                else if(p1.getProjectId() < p2.getProjectId()) {
//                    return -1;
//                }
//
//                return 0;
//            }
//        });



    }

}



















