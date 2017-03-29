package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.controller.DBController;
import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.helpers.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brian on 27/02/17.
 */
public class ManageProjectScene {


    private CheckBox[] checkBoxes = new CheckBox[21];
    private static final String CURR_DIR = "src/main/resources";
    private static final String TXT_FILE_DESC = "txt files (*.txt)";
    private static final String TXT_FILE_EXT = "*.txt";

    private Mediator mediator;

    public ManageProjectScene(Mediator mediator) {
        this.mediator = mediator;
    }


    public void start(Stage stage) {

        createCheckboxArray();

        Scene scene = new Scene(
                Utility.createContainer(createLeftPane(), createMiddlePane(), createRightPane()),
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
        Button button2 = new Button("View Stage");
        Button button3 = new Button("Rename");

        buttonOpen.setOnAction(event -> DBController.getInstance().showProjects());

        Button button4 = new Button("Delete");
        ObservableList<Button> buttonList =
                FXCollections.observableArrayList(buttonOpen, button2, button3, button4);

        for (Button button : buttonList) {
            button.setFocusTraversable(false);
            button.setMinWidth(150);
            VBox.setMargin(button, new Insets(0, 37.5, 50, 37.5));

        }

        // Labels
        Label label1 = new Label("Operations:");


        VBox.setMargin(label1, new Insets(30, 0, 50, 10));

        // add controls to VBox
        vBox.getChildren().addAll(label1, buttonOpen, button2, button3, button4);

        return vBox;
    }

    private ScrollPane createMiddlePane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_middle");
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        Label label = new Label("Select project:");

        vBox.getChildren().add(label);

        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnAction(event -> System.out.println(checkBox.selectedProperty().getValue()));
            checkBox.getStyleClass().add("checkbox_padding");
            vBox.getChildren().add(checkBox);
        }

        VBox.setMargin(label, new Insets(30, 0, 10, 10));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(vBox);

        return scrollPane;
    }


    private void createCheckboxArray() {

        List<String> text = Arrays.asList(
                "Project Name", "Project Name", "Project Name", "Project Name", "Project Name", "Project Name", "Project Name",
                "Project Name", "Project Name", "Project Name", "Project Name", "Project Name", "Project Name", "Project Name",
                "Project Name", "Project Name", "Project Name", "Project Name", "Project Name", "Project Name", "Project Name");

        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new CheckBox((i + 1) + " " + text.get(i));
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


    public void openDocument() {

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
