package ie.cit.architect.protracker.persistors;

import com.mongodb.DBObject;
import ie.cit.architect.protracker.helpers.Credentials;
import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.ProjectList;
import ie.cit.architect.protracker.model.User;
import ie.cit.architect.protracker.model.UserList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 3/3/2017.
 */
public class MySQLPersistor implements IPersistor{

    private Connection dbConnection;
    private ArrayList<AutoCloseable> dbObjects;

    private ArrayList<String> projectNameList;


    public MySQLPersistor(){

        dbObjects = new ArrayList<>();
        try {

            String db_Driver = "com.mysql.cj.jdbc.Driver";

            //TODO: uncomment after testing on localhost database is complete
            String db_REMOTE_URL = "jdbc:mysql://"+ Credentials.DB_SQL_IP +":"+ Credentials.DB_SQL_PORT +"/"+
                    Credentials.DB_NAME + "?user="+ Credentials.DB_SQL_USER + "&password=" + Credentials.DB_SQL_PASS;

//            String db_LOCAL_URL = "jdbc:mysql://"+ DB_LOCAL_HOST +":"+ DB_SQL_PORT +"/"+ DB_NAME +
//                    "?user="+ "root" + "&password=" + DB_LOCALHOST_PASS;

            Class.forName(db_Driver);

            this.dbConnection = DriverManager.getConnection(db_REMOTE_URL);

            if(this.dbConnection != null) {
                System.out.println("Connected to MySQL!");
            } else {
                System.out.println("Connection Failed!");
            }




        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void writeUsers(UserList users) {
        try {
            for (User currUser : users.getUsers()) {
                PreparedStatement preparedStatement =
                        dbConnection.prepareStatement(
                                "INSERT INTO users " +
                                        "(email, password)" +
                                        "VALUES(?, ?)");

                preparedStatement.setString(1, currUser.getEmailAddress());
                preparedStatement.setString(2, currUser.getPassword());

                preparedStatement.executeUpdate();
                dbObjects.add(preparedStatement);
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeProjects(ProjectList projects) {

        try {

            for(Project currProject : projects.getProjects())
            {
                PreparedStatement preparedStatement =
                        dbConnection.prepareStatement(
                          "INSERT INTO projects" +
                                  "(name, date, author, location, client_name)" +
                                  "VALUES(?, ?, ?, ?, ?)");

                preparedStatement.setString(1, currProject.getName());
                preparedStatement.setString(2, String.valueOf(currProject.getDate()));
                preparedStatement.setString(3, currProject.getAuthor());
                preparedStatement.setString(4, currProject.getLocation());
                preparedStatement.setString(5, currProject.getClientName());

                preparedStatement.executeUpdate();
                dbObjects.add(preparedStatement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    //TODO - remove params, only used for testing mongo
    @Override
    public ArrayList<String> selectRecords(ProjectList projectList) {

        try {

            ArrayList<Project> projectNameList = new ArrayList<>();

            String query = "SELECT email FROM users WHERE (email LIKE ? OR email LIKE ?)";
            String managerEmail = "coveneyarch@eircom.net";
            String employeeEmail = "coveneygeorgia@hotmail.com";

                PreparedStatement preparedStatement =
                        dbConnection.prepareStatement(query);

                preparedStatement.setString(1, managerEmail);
                preparedStatement.setString(2, employeeEmail);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String userEmail = rs.getString("email");

                    System.out.printf("Found employee email: %s", userEmail );
                }

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return projectNameList;
    }

    @Override
    public void displayCreatedProjects() {

    }


    public void close() {
        try{
            for(AutoCloseable curr : dbObjects) {
                curr.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // for MySQLDBPersistorTest
    public User getDbUser(User user) {
        return user;
    }


    // methods not called. Belong to MongoDB


    @Override
    public void displayCurrentProject(ProjectList projects) { }

    @Override
    public List<DBObject> getResults(int limit) {
        return null;
    }

    @Override
    public ArrayList<Project> selectProjectName(ProjectList projectList) {
        return null;
    }


}
