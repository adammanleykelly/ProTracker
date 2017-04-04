package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.controller.DBController;
import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.persistors.MongoDBPersistor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by brian on 27/02/17.
 */
public class ManageProjectScene {



    private static final String CURR_DIR = "src/main/resources";
    private static final String TXT_FILE_DESC = "txt files (*.txt)";
    private static final String TXT_FILE_EXT = "*.txt";
    private List<Object> arrayListProjectNames;
    private VBox vBoxMiddlePane;

    private Mediator mediator;

    public ManageProjectScene(Mediator mediator) {
        this.mediator = mediator;
    }


    public void start(Stage stage) {

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(createLeftPane());
        borderPane.setCenter(createMiddlePane());
        borderPane.setRight(createRightPane());
        borderPane.setBottom(createBottomPane());


        Scene scene = new Scene(
                borderPane,
                Consts.APP_WIDTH, Consts.APP_HEIGHT);

        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " Manage project");
        stage.show();
    }


    private VBox createRightPane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_left");
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        Button buttonOpen = new Button("Open");
        buttonOpen.setOnAction(event -> openDocument());
        Button buttonViewStage = new Button("View Stage");
        Button buttonRename = new Button("Rename");
        Button buttonDelete = new Button("Delete");


        buttonOpen.setOnAction(event -> DBController.getInstance().getProjects());


        ObservableList<Button> buttonList =
                FXCollections.observableArrayList(buttonOpen, buttonViewStage, buttonRename, buttonDelete);

        for (Button button : buttonList) {
            button.setFocusTraversable(false);
            button.setMinWidth(150);
            VBox.setMargin(button, new Insets(0, 37.5, 50, 37.5));

        }

        Label labelOperations = new Label("Operations:");

        VBox.setMargin(labelOperations, new Insets(30, 0, 50, 10));

        // add controls to VBox
        vBox.getChildren().addAll(labelOperations, buttonOpen, buttonViewStage, buttonRename, buttonDelete);

        return vBox;
    }


    private ScrollPane createMiddlePane() {
        vBoxMiddlePane = new VBox();
        vBoxMiddlePane.getStyleClass().add("hbox_middle");
        vBoxMiddlePane.setMinWidth(Consts.PANEL_WIDTH);

        Label label = new Label("Select project:");

        vBoxMiddlePane.getChildren().add(label);


        createCheckboxArray();

        VBox.setMargin(label, new Insets(30, 0, 10, 10));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(vBoxMiddlePane);

        return scrollPane;
    }


    /**
     * CheckBoxes populated with the project 'name' field from MongoDB
     * @see DBController#selectRecords()
     * @see MongoDBPersistor#getProjectNameList()
     */
    private void createCheckboxArray() {

        ArrayList<CheckBox> checkBoxList = new ArrayList<>();


        // set ArrayList equal to the return value from a MongoDB search query
        arrayListProjectNames = DBController.getInstance().selectRecords();

        // Prevents duplication of projects in our Checkboxes, i.e. when user leaves and then re-enters this scene
        HashSet uniqueProjectNames = new HashSet<>();
        uniqueProjectNames.addAll(arrayListProjectNames);

        // HashSet will not guarantee any order of its elements.
        // We will use TreeSet, so that values will come back in a well defined order.
//        TreeSet sortedProjectNames = new TreeSet();
//        sortedProjectNames.addAll(uniqueProjectNames);


        ArrayList orderedList = new ArrayList();
        orderedList.addAll(uniqueProjectNames);


        Iterator iterator = orderedList.iterator();

        int j = 0; // index used to give the project a number
        while (iterator.hasNext()) {
            Object projectName = iterator.next();

//            Queue<Object> fifoProjectNames = new LinkedList();
//            fifoProjectNames.add(projectName);
            

            // create CheckBoxes and set their text equal to the MongoDB find()
            CheckBox checkBox = new CheckBox((j+1) + " " + projectName);

            checkBoxList.add(checkBox);

            j++;
        }


//        int i = 0;
//        for (Object projectName : uniqueProjectNames) {
//
//            // create CheckBoxes and set their text equal to the MongoDB find()
//            CheckBox checkBox = new CheckBox((j+1) + " " + projectName);
//            checkBoxList.add(checkBox);
//            i++;
//        }


        for (CheckBox checkBox : checkBoxList) {
            checkBox.getStyleClass().add("checkbox_padding");
            vBoxMiddlePane.getChildren().add(checkBox);
        }
    }


    private VBox createLeftPane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_right");
        vBox.setMinWidth(225);

        Label label = new Label("Search Projects:");
        VBox.setMargin(label, new Insets(30, 0, 20, 10));


        TextField textField = new TextField();

        VBox.setMargin(textField, new Insets(0, 37.5, 0, 37.5));


        vBox.getChildren().addAll(label, textField);

        return vBox;
    }


    private AnchorPane createBottomPane() {

        Button buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> {

        });

        Button buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(event -> mediator.changeToArchitectMenuScene());

        // layout
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("anchorpane_color");
        AnchorPane.setTopAnchor(buttonCancel, 10.0);
        AnchorPane.setBottomAnchor(buttonCancel, 10.0);
        AnchorPane.setRightAnchor(buttonCancel, 150.0);
        AnchorPane.setBottomAnchor(buttonContinue, 10.0);
        AnchorPane.setRightAnchor(buttonContinue, 10.0);

        anchorPane.getChildren().addAll(buttonCancel, buttonContinue);

        return anchorPane;
    }


    private void openDocument() {

        File myFile = null;

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFiltertxt =
                new FileChooser.ExtensionFilter(TXT_FILE_DESC, TXT_FILE_EXT);
        fileChooser.getExtensionFilters().addAll(extFiltertxt);

        // open my resource directory, which contains the text files.
        fileChooser.setInitialDirectory(
                new java.io.File(CURR_DIR));

        myFile = fileChooser.showOpenDialog(null);

        if (myFile != null) {
            openFile(myFile);
        }
    }


    // solves JavaFX Freezing on Desktop.open(file) Ref: http://stackoverflow.com/a/34429067/5942254
    private void openFile(File file) {

        if (Desktop.isDesktopSupported()) {

            new Thread(() -> {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();

        }

    }
}
