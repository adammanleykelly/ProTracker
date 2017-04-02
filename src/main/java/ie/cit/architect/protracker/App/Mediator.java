package ie.cit.architect.protracker.App;

import ie.cit.architect.protracker.controller.DBController;
import ie.cit.architect.protracker.gui.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Mediator extends Application {

    private HomeScene homeMenuScene;
    private ArchitectMenuScene architectMenuScene;
    private ClientMenuScene clientMenuScene;
    private CreateNewProjectScene createNewProjectScene;
    private ViewMessagesScene viewMessagesScene;
    private ManageProjectScene manageProjectScene;
    private CustomArchitectDialog customArchitectDialog;
    private CustomClientDialog customClientDialog;

    private Stage primaryStage;

    public static void main(String[] args){ launch(args); }



     /**
     * Swapping scenes.
     * @link { http://stackoverflow.com/a/14168529/5942254 }
     */

    public Mediator(){
        homeMenuScene = new HomeScene(this);
        architectMenuScene = new ArchitectMenuScene(this);
        clientMenuScene = new ClientMenuScene(this);
        createNewProjectScene = new CreateNewProjectScene(this);
        viewMessagesScene = new ViewMessagesScene(this);
        manageProjectScene = new ManageProjectScene(this);
        customArchitectDialog = new CustomArchitectDialog(this);
        customClientDialog = new CustomClientDialog(this);

        // TODO:
        // - add new scenes
        // - add methods below for those scenes to be opened
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        homeMenuScene.start(primaryStage); // default


        // Database type (MongoDB / MySQL) selected in the controller
        Platform.runLater(() -> DBController.getInstance());
    }


    public void changeToArchitectMenuScene(){
        architectMenuScene.start(primaryStage);
    }

    public void changeToClientMenuScene(){
        clientMenuScene.start(primaryStage);
    }

    public void changeToCreateProjectScene() { createNewProjectScene.start(primaryStage); }

    public void changeToViewMessagesScene() { viewMessagesScene.start(primaryStage); }

    public void changeToManageProjectScene() { manageProjectScene.start(primaryStage); }

    public void changeToArchitectCustomDialog() { customArchitectDialog.signInArchitectDialog(); }

    public void changeToClientCustomDialog() { customClientDialog.signInClientDialog(); }

    public void changeToHomeScene() throws Exception { homeMenuScene.start(primaryStage); }

}