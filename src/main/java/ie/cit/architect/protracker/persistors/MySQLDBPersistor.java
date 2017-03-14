package ie.cit.architect.protracker.persistors;

import ie.cit.architect.protracker.model.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by brian on 3/3/2017.
 */
public class MySQLDBPersistor implements IPersistor{

    private Connection dbConnection;
    private ArrayList<AutoCloseable> dbObjects;


    private static final String DB_REMOTE_HOST = "82.118.226.76";
    private static final String DB_LOCAL_HOST = "localhost";
    private static final String DB_NAME = "protracker";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "remoteUser";
    private static final String DB_LOCALHOST_PASS = "bossdog12";


    public MySQLDBPersistor(){

        dbObjects = new ArrayList<>();
        try {

            String db_Driver = "com.mysql.cj.jdbc.Driver";

            //TODO: uncomment after testing on localhost database is complete
//            String db_REMOTE_URL = "jdbc:mysql://"+ DB_REMOTE_HOST +":"+ DB_PORT +"/"+ DB_NAME +
//                    "?user="+ DB_USER + "&password=" + MySQLCredentials.DB_PASS;

            String db_LOCAL_URL = "jdbc:mysql://"+ DB_LOCAL_HOST +":"+ DB_PORT +"/"+ DB_NAME +
                    "?user="+ "root" + "&password=" + DB_LOCALHOST_PASS;

            Class.forName(db_Driver);

            this.dbConnection = DriverManager.getConnection(db_LOCAL_URL);

            if(this.dbConnection != null) {
                System.out.println("Connected!");
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

    @Override
    public User selectRecords() {

        User user = new User();

        try {
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

                    user = UserArchitect.getInstance(userEmail);

                    System.out.println("User email: " + userEmail);
                }

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
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


    // not called
    @Override
    public void displayProjects(ProjectList projects) { }


}
