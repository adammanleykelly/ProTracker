package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.MainMediator;
import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.helpers.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brian on 10/02/17.
 */
public class CreateNewProjScene {


    private ArrayList<TextField> TextFieldArrayList;
    CheckBox[] checkBoxes = new CheckBox[10];

    private MainMediator mainMediator;

    public CreateNewProjScene(MainMediator mainMediator) {
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




    private VBox createLeftPane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_left");
        vBox.setMinWidth(Consts.PANEL_WIDTH);


        // TextFields
        TextField tfProjName = new TextField();
        TextField tfProjAuthor = new TextField();
        TextField tfProjClient = new TextField();
        TextField tfProjLocation = new TextField();
        ObservableList<TextField> textFieldList =
                FXCollections.observableArrayList(tfProjName, tfProjAuthor, tfProjClient, tfProjLocation);
        List<String> text = Arrays.asList("Project Name", "Name of Author", "Name of Client", "Project Location");

        for(int i = 0; i < textFieldList.size(); i++ ) {
            textFieldList.get(i).setPromptText(text.get(i));
        }

        for (TextField textField : textFieldList) {
            textField.setOnAction(event -> System.out.println(textField.getText()));
            textField.setFocusTraversable(false);
            VBox.setMargin(textField, new Insets(0, 37.5, 0, 37.5));
        }

        // Labels
        Label lbProjName = new Label("Name of project");
        Label lbProjAuthor = new Label("Name of author");
        Label lbProjClient = new Label("Name of client");
        Label lbProjLocation = new Label("Location");

        List<Label> labelList = Arrays.asList(lbProjName, lbProjAuthor, lbProjClient, lbProjLocation);

        for(Label label : labelList) {
            label.getStyleClass().add("lable_padding");
        }

        VBox.setMargin(lbProjName, new Insets(10,0,0,0));

        // add controls to VBox
        vBox.getChildren().addAll(lbProjName, tfProjName, lbProjAuthor, tfProjAuthor, lbProjClient, tfProjClient, lbProjLocation, tfProjLocation);

        return vBox;
    }

    private VBox createMiddlePane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_middle");
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        Label label = new Label("Select folders to create:");

        vBox.getChildren().add(label);


        for(CheckBox checkBox :
                checkBoxes) {
            checkBox.setOnAction(event -> System.out.println(checkBox.selectedProperty().getValue()));
            checkBox.getStyleClass().add("checkbox_padding");
            vBox.getChildren().add(checkBox);
        }

        VBox.setMargin(label, new Insets(30,0,10,0));

        return vBox;
    }


    // Ref: http://stackoverflow.com/a/23512831/5942254
    private void createCheckboxArray() {

        List<String> text = Arrays.asList(
                "Site Maps", "Proposed Drawings", "Structural Drawings", "Supplier Details",
                "Fire Drawings", "Images", "Exports", "Imports", "Documents", "Emails");

        for(int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new CheckBox((i+1) + " " + text.get(i));
        }
    }


    private VBox createRightPane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_right");
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        Label label = new Label("Description:");

        TextArea textArea = new TextArea();
        textArea.setPrefWidth(200);

        VBox.setMargin(label, new Insets(30,0,0,0));

        vBox.getChildren().addAll(label, textArea);

        return vBox;
    }


}