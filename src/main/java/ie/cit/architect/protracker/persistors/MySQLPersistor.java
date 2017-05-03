//package ie.cit.architect.protracker.persistors;
//
//import ie.cit.architect.protracker.model.Project;
//import ie.cit.architect.protracker.model.ProjectList;
//import ie.cit.architect.protracker.model.User;
//import ie.cit.architect.protracker.model.EmployeeList;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//
//
///**
// * Created by brian on 3/3/2017.
// */
//public class MySQLPersistor implements IPersistor{
//
//    private Connection dbConnection;
//    private ArrayList<AutoCloseable> dbObjects;
//
//
//    public static final String DB_SQL_PASS = "bossdog12";
//    public static final String DB_SQL_NAME = "protracker";
//    public static final String DB_SQL_IP = "localhost";
//    public static final String DB_SQL_PORT = "3308";
//
//
//    public MySQLPersistor(){
//
//        dbObjects = new ArrayList<>();
//        try {
//
//            String db_Driver = "com.mysql.cj.jdbc.Driver";
//
//            //TODO: uncomment after testing on localhost database is complete
////            String db_REMOTE_URL = "jdbc:mysql://"+ Credentials.DB_SQL_IP +":"+ Credentials.DB_SQL_PORT +"/"+
////                    Credentials.DB_NAME + "?user="+ Credentials.DB_SQL_USER + "&password=" + Credentials.DB_SQL_PASS;
//
//            String db_LOCAL_URL = "jdbc:mysql://"+ DB_SQL_IP +":"+ DB_SQL_PORT +"/"+ DB_SQL_NAME +
//                    "?user="+ "root" + "&password=" + DB_SQL_PASS;
//
//            Class.forName(db_Driver);
//
//            this.dbConnection = DriverManager.getConnection(db_LOCAL_URL);
//
//            if(this.dbConnection != null) {
//                System.out.println("Connected to MySQL!");
//            } else {
//                System.out.println("Connection Failed!");
//            }
//
//
//
//
//        }catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Override
//    public void writeEmployeeUsers(EmployeeList users) {
//        try {
//            for (User currUser : users.getUsers()) {
//                PreparedStatement preparedStatement =
//                        dbConnection.prepareStatement(
//                                "INSERT INTO users " +
//                                        "(email, password)" +
//                                        "VALUES(?, ?)");
//
//                preparedStatement.setString(1, currUser.getEmailAddress());
//                preparedStatement.setString(2, currUser.getPassword());
//
//                preparedStatement.executeUpdate();
//                dbObjects.add(preparedStatement);
//            }
//            close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void writeProjects(ProjectList projects) {
//
//        try {
//
//            for(Project currProject : projects.getProjects())
//            {
//                PreparedStatement preparedStatement =
//                        dbConnection.prepareStatement(
//                          "INSERT INTO projects" +
//                                  "(name, date, author, location, client_name)" +
//                                  "VALUES(?, ?, ?, ?, ?)");
//
//                preparedStatement.setString(1, currProject.getName());
//                preparedStatement.setString(2, String.valueOf(currProject.getDate()));
//                preparedStatement.setString(3, currProject.getAuthor());
//                preparedStatement.setString(4, currProject.getLocation());
//                preparedStatement.setString(5, currProject.getClientName());
//
//                preparedStatement.executeUpdate();
//                dbObjects.add(preparedStatement);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//
//    @Override
//    public ArrayList<Project> readProjects() {
//
//        ArrayList<Project> pNameList = new ArrayList<>();
//
//        try {
//
//            String query = "SELECT email FROM users WHERE (email LIKE ? OR email LIKE ?)";
//            String managerEmail = "coveneyarch@eircom.net";
//            String employeeEmail = "coveneygeorgia@hotmail.com";
//
//            PreparedStatement preparedStatement =
//                    dbConnection.prepareStatement(query);
//
//            preparedStatement.setString(1, managerEmail);
//            preparedStatement.setString(2, employeeEmail);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                String userEmail = rs.getString("email");
//
//                System.out.printf("Found employee email: %s", userEmail );
//            }
//
//            close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        return pNameList;
//    }
//
//
//
//
//
//    public void close() {
//        try{
//            for(AutoCloseable curr : dbObjects) {
//                curr.close();
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    // for MySQLDBPersistorTest
//    public User getDbUser(User user) {
//        return user;
//    }
//
//
//
//
//    @Override
//    public void deleteProject(String projectName) {
//
//
//
//    }
//
//    @Override
//    public void updateProject(String currentProjectName, String upDatedProjectName) {
//
//    }
//
//
//}
