package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.MainMediator;
import ie.cit.architect.protracker.helpers.Consts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brian on 10/02/17.
 */
public class CreateNewProjScene {


    private final int PANEL_WIDTH = 275;
    private ArrayList<TextField> TextFieldArrayList;
    CheckBox[] checkBoxes = new CheckBox[10];

    private MainMediator mainMediator;

    public CreateNewProjScene(MainMediator mainMediator) {
        this.mainMediator = mainMediator;
    }


    public void start(Stage stage) {

        createButtonArray();
        Scene scene = new Scene(
                createContainer(), Consts.APP_WIDTH, Consts.APP_HEIGHT);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " Create New");
        stage.show();
    }


    private BorderPane createContainer() {
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(createLeftPane());
        borderPane.setCenter(createMiddlePane());
        borderPane.setRight(createRightPane());
        borderPane.setBottom(createNavButtons());

        return borderPane;
    }

    private VBox createLeftPane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_left");
        vBox.setMinWidth(PANEL_WIDTH);


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

        // add controls to VBox
        vBox.getChildren().addAll(lbProjName, tfProjName, lbProjAuthor, tfProjAuthor, lbProjClient, tfProjClient, lbProjLocation, tfProjLocation);

        return vBox;
    }

    private VBox createMiddlePane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_middle");
        vBox.setMinWidth(PANEL_WIDTH);


        for(CheckBox checkBox : checkBoxes) {
            vBox.getChildren().add(checkBox);
        }


        return vBox;
    }


    // Ref: http://stackoverflow.com/a/23512831/5942254
    private void createButtonArray() {
        for(int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new CheckBox("Checkbox-"+i);
        }
    }


    private VBox createRightPane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_right");
        vBox.setMinWidth(PANEL_WIDTH);
        return vBox;
    }


    public AnchorPane createNavButtons() {

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("anchorpane_color");

        Button buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(event -> System.exit(0));
        Button buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> {
            try {
                mainMediator.changeToArchitectMenuScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        AnchorPane.setBottomAnchor(buttonCancel, 10.0);
        AnchorPane.setRightAnchor(buttonCancel, 150.0);
        AnchorPane.setBottomAnchor(buttonContinue, 10.0);
        AnchorPane.setRightAnchor(buttonContinue, 10.0);
        anchorPane.getChildren().addAll(buttonCancel, buttonContinue);

        return anchorPane;
    }

}