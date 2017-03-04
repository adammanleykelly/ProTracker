package ie.cit.architect.protracker.persistors;

import ie.cit.architect.protracker.model.User;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by brian on 3/3/2017.
 */
public class DBPersistor  implements IPersistor{

    private Connection dbConnection;
    private ArrayList<AutoCloseable> dbObjects;


    private static final String DB_PASS = "<enter_pass_here>";


    public DBPersistor(){

        dbObjects = new ArrayList<>();
        try {

            String db_Driver = "com.mysql.cj.jdbc.Driver";
            String db_URL = "jdbc:mysql://82.118.226.76:3306/protracker?user=root&password=" + DB_PASS;

            Class.forName(db_Driver);

            this.dbConnection = DriverManager.getConnection(db_URL);

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
    public void writeUsers(ObservableList<User> users) {
        try {
            for (User currUser : users) {
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


    public void close() {
        try{
            for(AutoCloseable curr : dbObjects) {
                curr.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }






















}
