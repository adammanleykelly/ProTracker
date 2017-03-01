package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.MainMediator;
import ie.cit.architect.protracker.helpers.Consts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brian on 10/02/17.
 */
public class CreateNewProjScene {


    private ArrayList<String> directoryList = new ArrayList<>();
    private CheckBox[] checkBoxes = new CheckBox[10];
    private String checkboxText;
    private String numberRemoved;
    private TextField tfProjectName = new TextField();
    private TextField tfProjectAuthor = new TextField();
    private TextField tfProjectClient = new TextField();
    private TextField tfProjectLocation = new TextField();

    private MainMediator mainMediator;
    public CreateNewProjScene(MainMediator mainMediator) {
        this.mainMediator = mainMediator;
    }


    public void start(Stage stage) {

        createCheckboxArray();

        Scene scene= new Scene(
                createContainer(createLeftPane(), createMiddlePane(), createRightPane(), createNavigationButtons()),
                Consts.APP_WIDTH, Consts.APP_HEIGHT);

        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " Create New");
        stage.show();
    }



    public static BorderPane createContainer(VBox vb1, VBox vb2, VBox vb3, AnchorPane ap) {
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vb1);
        borderPane.setCenter(vb2);
        borderPane.setRight(vb3);
        borderPane.setBottom(ap);
        return borderPane;
    }



    private VBox createLeftPane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_left");
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        ObservableList<TextField> textFieldList =
                FXCollections.observableArrayList(tfProjectName, tfProjectAuthor, tfProjectClient, tfProjectLocation);
        List<String> text = Arrays.asList("Project Name", "Name of Author", "Name of Client", "Project Location");

        for(int i = 0; i < textFieldList.size(); i++ ) {
            textFieldList.get(i).setPromptText(text.get(i));
        }

        for (TextField textField : textFieldList) {
            textField.setFocusTraversable(false);
            VBox.setMargin(textField, new Insets(0, 37.5, 0, 37.5));
        }

        // Labels
        Label lbProjectName = new Label("Name of project");
        Label lbProjectAuthor = new Label("Name of author");
        Label lbProjectClient = new Label("Name of client");
        Label lbProjectLocation = new Label("Location");

        List<Label> labelList = Arrays.asList(lbProjectName, lbProjectAuthor, lbProjectClient, lbProjectLocation);

        for(Label label : labelList) {
            label.getStyleClass().add("lable_padding");
        }

        VBox.setMargin(lbProjectName, new Insets(10,0,0,0));

        // add controls to VBox
        vBox.getChildren().addAll(lbProjectName, tfProjectName, lbProjectAuthor, tfProjectAuthor, lbProjectClient, tfProjectClient, lbProjectLocation, tfProjectLocation);

        return vBox;
    }



    private VBox createMiddlePane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_middle");
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        Label label = new Label("Select folders to create:");

        vBox.getChildren().add(label);


        for(CheckBox checkBox : checkBoxes) {
            checkBox.setOnAction(event -> removeDigits(event));
            checkBox.getStyleClass().add("checkbox_padding");
            vBox.getChildren().add(checkBox);

        }

        VBox.setMargin(label, new Insets(30,0,10,0));

        return vBox;
    }



    private void createDirectories() {
        String projectName = tfProjectName.getText();
        try {
            Path path1 = Paths.get("/home/brian/Desktop/" + projectName + "//");
            Files.createDirectories(path1);

            for(int i = 0; i < directoryList.size(); i++) {
                Path path2 = Paths.get("/home/brian/Desktop/" + projectName + "//" + (i+1) + directoryList.get(i));
                Files.createDirectories(path2);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    private String removeDigits(ActionEvent event) {
        for (CheckBox checkBox : checkBoxes) {
            if (event.getSource().equals(checkBox) && checkBox.isSelected()) {
                checkboxText = checkBox.getText();
                numberRemoved = checkboxText.replaceAll("\\d", "");

                //add to ArrayList
                directoryList.add(numberRemoved);
            }
        }
        return numberRemoved;
    }



    // Ref: http://stackoverflow.com/a/23512831/5942254
    private void createCheckboxArray() {

        List<String> text = Arrays.asList(
                "_SiteMaps", "_ProposedDrawings", "_StructuralDrawings", "_SupplierDetails",
                "_FireDrawings", "_Images", "_Exports", "_Imports", "_Documents", "_Emails");

        for(int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new CheckBox((i+1) + text.get(i));
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



    private AnchorPane createNavigationButtons() {

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("anchorpane_color");

        Button buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(event -> System.exit(0));
        Button buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> {
            try {
                createDirectories();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        AnchorPane.setTopAnchor(buttonCancel, 10.0);
        AnchorPane.setBottomAnchor(buttonCancel, 10.0);
        AnchorPane.setRightAnchor(buttonCancel, 150.0);
        AnchorPane.setBottomAnchor(buttonContinue, 10.0);
        AnchorPane.setRightAnchor(buttonContinue, 10.0);
        anchorPane.getChildren().addAll(buttonCancel, buttonContinue);

        return anchorPane;
    }


}


























