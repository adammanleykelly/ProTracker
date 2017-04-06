package ie.cit.architect.protracker.persistors;

import com.mongodb.*;
import ie.cit.architect.protracker.helpers.Credentials;
import ie.cit.architect.protracker.model.*;

import java.util.*;


/**
 * Created by brian on 13/03/17.
 */
public class MongoDBPersistor implements IPersistor {

    private MongoClient mongoClientConn;
    private DBCollection tableUsers, tableProjects;
    private DB db;



    public MongoDBPersistor() {

        try {

            //local database
//            mongoClientConn = new MongoClient("localhost", 27017);

            String mongoURI = "mongodb://" + Credentials.DB_MONGO_USER + ":" + Credentials.DB_MONGO_PASS + "@" +
                    Credentials.DB_MONGO_IP +"/" + Credentials.DB_NAME;

            mongoClientConn = new MongoClient( new MongoClientURI(mongoURI));


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

    }

    @Override
    public void writeProjects(ProjectList projects) {

        try {
            BasicDBObject document = new BasicDBObject();

            for (Project currProject : projects.getProjects()) {

                document.put("project_id", currProject.getProjectId());
                document.put("name", currProject.getName());
                document.put("author", currProject.getAuthor());
                document.put("location", currProject.getLocation());
                document.put("client_name", currProject.getClientName());
                document.put("create_date", currProject.getDate());


                //A unique index ensures that the indexed fields do not store duplicate values
                // https://docs.mongodb.com/v3.2/core/index-unique/
                DBObject indexOption = new BasicDBObject();
                indexOption.put("unique", true);
            }
            tableProjects.insert(document);

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



    @Override
    public ArrayList<Project> getProjectNameList() {

        HashSet<Project> projectNameList = new HashSet<>();
        ArrayList<Project> orderedList = new ArrayList();


        try {
            BasicDBObject query = new BasicDBObject();
            BasicDBObject field = new BasicDBObject();
//            BasicDBObject field = new BasicDBObject("name", true).append("_id", false);


            field.put("project_id", 1);
            field.put("name", 1);
            field.put("create_date", 1);
            field.append("_id", 0);

            DBCursor cursor = tableProjects.find(query, field);


            while (cursor.hasNext()) {

                BasicDBObject object = (BasicDBObject) cursor.next();

                int projectId = (int) object.get("project_id");
                String projectName = String.valueOf(object.get("name"));
                Date date = (Date) object.get("create_date");

                Project project = new Project();
                project.setProjectId(projectId);
                project.setName(projectName);
                project.setDate(date);

                projectNameList.add(project);
            }


            for(Project project : projectNameList) {
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



















