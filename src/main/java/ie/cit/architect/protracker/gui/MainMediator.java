package ie.cit.architect.protracker.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainMediator extends Application {

    private ArchitectMenuScene architectMenuScene;
    private ClientMenuScene clientMenuScene;
    private HomeScene homeMenuScene;
    private Stage primaryStage;

    public static void main(String[] args){ launch(args); }


    //  Swapping scenes. Ref: http://stackoverflow.com/a/14168529/5942254
    public MainMediator(){
        architectMenuScene = new ArchitectMenuScene(this);
        clientMenuScene = new ClientMenuScene(this);
        homeMenuScene = new HomeScene(this);

        // TODO:
        // - add new scenes
        // - add methods below for those scenes to be opened
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        homeMenuScene.start(primaryStage); // default
    }


    public void changeToArchitectMenuScene(){
        architectMenuScene.start(primaryStage);
    }

    public void changeToClientMenuScene(){
        clientMenuScene.start(primaryStage);
    }


}