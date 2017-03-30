package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.controller.DBController;
import ie.cit.architect.protracker.controller.PersistenceMode;
import ie.cit.architect.protracker.helpers.Consts;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class HomeScene {

    private Mediator mediator;

    // Composition - passing a reference of Mediator to HomeScene's constructor. Now HomeScene 'has-a' Mediator
    public HomeScene(Mediator mediator) {
        this.mediator = mediator;
    }

    private Button buttonSignInArchitect, buttonMySQL, buttonMongoDB;
    private Button buttonSignInClient;
    private CustomArchitectDialog customArchitectDialog;


    public void start(Stage stage) throws Exception {

        // check the Operating System
        System.out.println(System.getProperty("os.name"));

        Scene scene = new Scene(
                createHomeMenu(), Consts.APP_WIDTH, Consts.APP_HEIGHT);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE);
        stage.show();
    }


    private GridPane createHomeMenu() {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 0, 20, 20));
        gridPane.setVgap(20);

        Label labelTitle = new Label(Consts.APPLICATION_TITLE.toUpperCase());
        labelTitle.getStyleClass().add("label_title");

        Label labelSubTitle = new Label("Welcome");
        labelSubTitle.getStyleClass().add("label_subtitle");

        buttonSignInClient = new Button("Sign in Client");
        buttonSignInArchitect = new Button("Sign in Architect");
        buttonSignInClient.setMinWidth(150);
        buttonSignInArchitect.setMinWidth(150);

        buttonMySQL = new Button("MySQL");
        buttonMySQL.setOnAction(event ->
                DBController.getInstance().setPersistenceMode(PersistenceMode.MYSQL));

        buttonMongoDB = new Button("MongoDB");

        Platform.runLater(() -> buttonMongoDB.setOnAction(event ->
                DBController.getInstance().setPersistenceMode(PersistenceMode.MONGODB)));

        buttonSignInClient.setOnAction(event -> mediator.changeToClientCustomDialog());
        buttonSignInArchitect.setOnAction(event -> mediator.changeToArchitectCustomDialog());


        gridPane.add(labelTitle, 0, 1);
        GridPane.setHalignment(labelTitle, HPos.CENTER);

        gridPane.add(labelSubTitle, 0, 2);
        GridPane.setHalignment(labelSubTitle, HPos.CENTER);

        gridPane.add(buttonSignInClient, 0, 6);
        GridPane.setHalignment(buttonSignInClient, HPos.CENTER);

        gridPane.add(buttonSignInArchitect, 0, 8);
        GridPane.setHalignment(buttonSignInArchitect, HPos.CENTER);


        //test
        gridPane.add(buttonMySQL, 0, 10);
        gridPane.add(buttonMongoDB, 1, 10);



        return gridPane;
    }


}
