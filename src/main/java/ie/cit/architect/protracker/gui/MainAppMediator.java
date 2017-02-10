package ie.cit.architect.protracker.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainAppMediator extends Application {

    private ArchitectMenuScene architectMenuScene;
    private ClientMenuScene clientMenuScene;
    private HomeMenu homeMenuScene;
    private Stage primaryStage;

//  Swapping scenes, ref:
//    http://stackoverflow.com/a/14168529/5942254

    public MainAppMediator(){
        architectMenuScene = new ArchitectMenuScene(this);
        clientMenuScene = new ClientMenuScene(this);
        homeMenuScene = new HomeMenu(this);

    }
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

//        changeToClientMenuScene();
//        changeToArchitectMenuScene();
        changeToHomeMenu();

    }

    public void changeToArchitectMenuScene(){
        architectMenuScene.start(primaryStage);
    }

    public void changeToClientMenuScene(){
        clientMenuScene.start(primaryStage);
    }

    public void changeToHomeMenu() throws Exception {
        homeMenuScene.start(primaryStage);
    }
}