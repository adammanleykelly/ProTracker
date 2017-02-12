package ie.cit.architect.protracker.gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by brian on 10/02/17.
 */
public class ArchitectMenuScene {

    private GridPane view;
    private MainMediator mainMediator;

    public ArchitectMenuScene(MainMediator mainMediator) {
        this.mainMediator = mainMediator;
    }


    public void start(Stage stage) {

        view = new GridPane();
        view.setPrefHeight(400);
        view.setPrefWidth(300);
        Label label2 = new Label("Architect Menu");
        view.add(label2, 1,1);

        Scene scene = new Scene(view, 400, 300);

        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
    }


}
