package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.MainMediator;
import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.helpers.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brian on 27/02/17.
 */
public class ManageProjectScene {


    private ArrayList<TextField> TextFieldArrayList;
    CheckBox[] checkBoxes = new CheckBox[21];

    private MainMediator mainMediator;

    public ManageProjectScene(MainMediator mainMediator) {
        this.mainMediator = mainMediator;
    }


    public void start(Stage stage) {

        createCheckboxArray();

        Scene scene = new Scene(
                Utility.createContainer(createLeftPane(), createMiddlePane(), createRightPane()),
                Consts.APP_WIDTH, Consts.APP_HEIGHT);

        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " Create New");
        stage.show();
    }


    private VBox createRightPane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_left");
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        Button button1 = new Button("Open");
        Button button2 = new Button("View Stage");
        Button button3 = new Button("Rename");
        Button button4 = new Button("Delete");
        ObservableList<Button> buttonList =
                FXCollections.observableArrayList(button1, button2, button3, button4);

        for (Button button : buttonList) {
            button.setOnAction(event -> System.out.println(button.getText()));
            button.setFocusTraversable(false);
            button.setMinWidth(150);
            VBox.setMargin(button, new Insets(0, 37.5, 50, 37.5));

        }

        // Labels
        Label label1 = new Label("Operations:");


        VBox.setMargin(label1, new Insets(30,0,50,10));

        // add controls to VBox
        vBox.getChildren().addAll(label1, button1, button2, button3, button4);

        return vBox;
    }

    private ScrollPane createMiddlePane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_middle");
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        Label label = new Label("Select project:");

        vBox.getChildren().add(label);

        for(CheckBox checkBox :
                checkBoxes) {
            checkBox.setOnAction(event -> System.out.println(checkBox.selectedProperty().getValue()));
            checkBox.getStyleClass().add("checkbox_padding");
            vBox.getChildren().add(checkBox);
        }

        VBox.setMargin(label, new Insets(30,0,10,10));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(vBox);

        return scrollPane;
    }


    private void createCheckboxArray() {

        List<String> text = Arrays.asList(
                "Project Name","Project Name", "Project Name", "Project Name", "Project Name", "Project Name", "Project Name",
                "Project Name", "Project Name", "Project Name", "Project Name", "Project Name", "Project Name", "Project Name",
                "Project Name", "Project Name", "Project Name", "Project Name", "Project Name", "Project Name", "Project Name");

        for(int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new CheckBox((i+1) + " " + text.get(i));
        }
    }


    private VBox createLeftPane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_right");
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        Label label = new Label("Search Projects:");
        VBox.setMargin(label, new Insets(30,0,20,10));


        TextField textField = new TextField();
        VBox.setMargin(textField, new Insets(0, 37.5, 0, 37.5));


        vBox.getChildren().addAll(label, textField);

        return vBox;
    }




}
