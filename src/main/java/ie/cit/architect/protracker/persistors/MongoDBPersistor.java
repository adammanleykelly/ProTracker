package ie.cit.architect.protracker.persistors;

import com.mongodb.*;
import ie.cit.architect.protracker.helpers.Credentials;
import ie.cit.architect.protracker.model.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by brian on 13/03/17.
 */
public class MongoDBPersistor implements IPersistor {

    private MongoClient mongoClientConn;
    private DBCollection tableUsers, tableProjects;
    private DB db;

    private ArrayList<String> projectNameList;


    public MongoDBPersistor() {

        try {

            //local database
//            mongoClientConn = new MongoClient("localhost", 27017);

            // remote database - there is ~2sec delay
           mongoClientConn = new MongoClient( new MongoClientURI(
                   "mongodb://" + Credentials.DB_MONGO_USER + ":" +
                           Credentials.DB_MONGO_PASS + "@" + Credentials.DB_MONGO_IP +"/" + Credentials.DB_NAME));


            if(mongoClientConn != null) {
                System.out.println("Connected to MongoDB!");
            } else {
                System.out.println("Connection to MongoDB Failed!");
            }


            //Get Database
            // if database doesn't exist, mongoDB will create it for you
            db = mongoClientConn.getDB(Credentials.DB_NAME);


            //Get Collection / Table from 'protracker'
            //if collection doesn't exist, mongoDB will create it for you
            tableUsers = db.getCollection("users");

            // create another table
            tableProjects = db.getCollection("projects");


        } catch (MongoException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void writeUsers(UserList users) {

        new Thread(() -> {
            try {
                for (IUser currUser : users.getUsers()) {
                    BasicDBObject document = new BasicDBObject();
                    //key value pair
                    document.put("email", currUser.getEmailAddress());
                    document.put("password", currUser.getPassword());
                    tableUsers.insert(document);

                }
            } catch (MongoException e) {
                e.printStackTrace();
            }

        }).start();
    }

    @Override
    public void writeProjects(ProjectList projects) {

        try {
            for (Project currProject : projects.getProjects()) {
                BasicDBObject document = new BasicDBObject();
                document.put("name", currProject.getName());
                document.put("author", currProject.getAuthor());
                document.put("location", currProject.getLocation());
                document.put("client_name", currProject.getClientName());

                tableProjects.insert(document);
            }
        }catch (MongoException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void displayCurrentProject(ProjectList projects) {
        try {
            for (IProject currProject : projects.getProjects()) {
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("name", currProject.getName());
                searchQuery.put("author", currProject.getAuthor());
                searchQuery.put("location", currProject.getLocation());
                searchQuery.put("client_name", currProject.getClientName());

                DBCursor cursor = tableProjects.find(searchQuery);

                System.out.println("Project:");
                while (cursor.hasNext()){
                    System.out.println(cursor.next());
                }
            }
        }catch (MongoException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void displayCreatedProjects() {

        try {
            DBCursor cursor = tableProjects.find();

            System.out.println("Project:");
            while (cursor.hasNext()) {

                String x = String.valueOf(cursor.next());
                System.out.println(x);

            }

        } catch (MongoException e) {
            e.printStackTrace();
        }
    }


    // https://stackoverflow.com/questions/19580501/create-a-list-from-dbcursor-in-mongodb-and-java
    public List<DBObject> getResults(int limit) {
        List<DBObject> myList = new ArrayList<>();
        DBCursor myCursor= tableProjects.find().sort(new BasicDBObject("name",-1)).limit(limit);
        try {
            while(myCursor.hasNext()) {
                myList.add(myCursor.next());
            }

            System.out.println(myList);

        }
        finally {
            myCursor.close();
        }
        return myList;
    }


    // print all entries from a single field in the collection
    @Override
    public ArrayList<String> selectRecords(ProjectList project) {

        try {
            BasicDBObject query = new BasicDBObject();
            BasicDBObject field = new BasicDBObject();
            field.put("name", 1);
            DBCursor cursor = tableProjects.find(query, field);

            projectNameList = new ArrayList<>();

            while (cursor.hasNext()) {

                String pName = (String) cursor.next().get("name");
                projectNameList.add(pName);
            }

            System.out.println(projectNameList.toString());

        } catch (MongoException e) {
            e.printStackTrace();
        }

        return projectNameList;
    }


    @Override
    public ArrayList<Project> selectProjectName(ProjectList projectList) {
        try {

            List<Project> projectNameList = new ArrayList<>();

            for(Project currProject : projectList.getProjects()) {
                BasicDBObject query = new BasicDBObject();
                BasicDBObject field = new BasicDBObject();
                field.put("name", 1);
                DBCursor cursor = tableProjects.find(query, field);


                while (cursor.hasNext()) {
                    String pName = (String) cursor.next().get("name");
                    currProject.setName(pName);

                }

                projectNameList.add(currProject);
                System.out.println(projectNameList);
            }

        } catch (MongoException e) {
            e.printStackTrace();
        }

        return projectList.getProjects();
    }

}



















