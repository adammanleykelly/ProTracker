package ie.cit.architect.protracker.gui;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Created by brian on 10/02/17.
 */
public class BuilderMenuScene {

    private GridPane view;

    public BuilderMenuScene() {

        view = new GridPane();
        view.setPrefHeight(400);
        view.setPrefWidth(300);
        Label label = new Label("Builder Menu");
        view.add(label, 1, 1);
    }

    public Parent getView() {
        return view;
    }

}