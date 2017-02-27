package ie.cit.architect.protracker.App;

import ie.cit.architect.protracker.gui.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainMediator extends Application {

    private HomeScene homeMenuScene;
    private ArchitectMenuScene architectMenuScene;
    private ClientMenuScene clientMenuScene;
    private CreateNewProjScene createNewProjScene;
    private ViewMessagesScene viewMessagesScene;

    private Stage primaryStage;

    public static void main(String[] args){ launch(args); }


    //  Swapping scenes. Ref: http://stackoverflow.com/a/14168529/5942254
    public MainMediator(){
        homeMenuScene = new HomeScene(this);
        architectMenuScene = new ArchitectMenuScene(this);
        clientMenuScene = new ClientMenuScene(this);
        createNewProjScene = new CreateNewProjScene(this);
        viewMessagesScene = new ViewMessagesScene(this);

        // TODO:
        // - add new scenes
        // - add methods below for those scenes to be opened
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        homeMenuScene.start(primaryStage); // default
    }


    // methods to change scene
    public void changeToArchitectMenuScene(){
        architectMenuScene.start(primaryStage);
    }

    public void changeToClientMenuScene(){
        clientMenuScene.start(primaryStage);
    }

    public void changeToCreateProjScene() { createNewProjScene.start(primaryStage); }

    public void changeToViewMessagesScene() { viewMessagesScene.start(primaryStage); }

    public void changeToHomeScene() throws Exception { homeMenuScene.start(primaryStage); }

}