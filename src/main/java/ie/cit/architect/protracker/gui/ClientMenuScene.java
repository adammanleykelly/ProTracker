package ie.cit.architect.protracker.gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Created by brian on 10/02/17.
 */
public class ClientMenuScene {

    private BorderPane view;
    private MainAppMediator mainAppMediator;

    public ClientMenuScene(MainAppMediator mainAppMediator) {
        this.mainAppMediator = mainAppMediator;
    }


    public void start(Stage stage) {
        Circle circle = new Circle(40, 40, 30);
        Group root = new Group(circle);
        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
    }
}
