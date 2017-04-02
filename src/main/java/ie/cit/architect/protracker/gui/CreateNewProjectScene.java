package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.controller.DBController;
import ie.cit.architect.protracker.controller.Controller;
import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.model.IProject;
import ie.cit.architect.protracker.model.Project;
import javafx.application.Platform;
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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brian on 10/02/17.
 */
public class CreateNewProjectScene {

    private static final String FILE_SEP = File.separator;
    private static final String DOUBLE_FILE_SEP = FILE_SEP + FILE_SEP;
    private static final String PATH_TO_DESKTOP = System.getProperty("user.home") + FILE_SEP + "Desktop" + FILE_SEP;

    private ArrayList<String> directoryArrayList = new ArrayList<>();
    private CheckBox[] checkboxList = new CheckBox[10];
    private TextField tfProjectName = new TextField();
    private TextField tfProjectAuthor = new TextField();
    private TextField tfProjectClient = new TextField();
    private TextField tfProjectLocation;
    private String projectName, projectDate, projectAuthor, projectLocation, projectClient;
    private IProject project;
    private Mediator mediator;


    public CreateNewProjectScene(Mediator mediator) {
        this.mediator = mediator;
    }


    public void start(Stage stage) {

        createCheckboxArray();

        Scene scene = new Scene(
                createContainer(createLeftPane(), createMiddlePane(), createRightPane(), createBottomPane()),
                Consts.APP_WIDTH, Consts.APP_HEIGHT);

        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " Create New");
        stage.show();
    }

    private void getUserInput() {
        projectName = tfProjectName.getText();
        projectDate = String.valueOf(LocalDate.now());
        projectAuthor = tfProjectAuthor.getText();
        projectLocation = tfProjectLocation.getText();
        projectClient = tfProjectClient.getText();
    }


    private static BorderPane createContainer(VBox vb1, VBox vb2, VBox vb3, AnchorPane ap) {
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

        tfProjectLocation = new TextField();

        ObservableList<TextField> textFieldList =
                FXCollections.observableArrayList(tfProjectName, tfProjectAuthor, tfProjectClient, tfProjectLocation);
        List<String> text = Arrays.asList("Project Name", "Name of Author", "Name of Client", "Project Location");

        for (int i = 0; i < textFieldList.size(); i++) {
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

        for (Label label : labelList) {
            label.getStyleClass().add("lable_padding");
        }

        VBox.setMargin(lbProjectName, new Insets(10, 0, 0, 0));

        // add controls to VBox
        vBox.getChildren().addAll(lbProjectName, tfProjectName, lbProjectAuthor, tfProjectAuthor,
                lbProjectClient, tfProjectClient, lbProjectLocation, tfProjectLocation);

        return vBox;
    }


    private VBox createMiddlePane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_middle");
        vBox.setMinWidth(Consts.PANEL_WIDTH);
        Label label = new Label("Select folders to create:");
        vBox.getChildren().add(label);

        for (CheckBox checkBox : checkboxList) {
            checkBox.setOnAction(event -> removeDigits(event));
            checkBox.getStyleClass().add("checkbox_padding");
            vBox.getChildren().add(checkBox);
        }
        VBox.setMargin(label, new Insets(30, 0, 10, 0));

        return vBox;
    }


    private void createDirectories() {
        try {
            String projName = tfProjectName.getText();
            Path path1 = Paths.get(PATH_TO_DESKTOP + projName + DOUBLE_FILE_SEP);
            Files.createDirectories(path1);

            if (!projName.isEmpty()) {
                for (int i = 0; i < directoryArrayList.size(); i++) {
                    String subDirectory = directoryArrayList.get(i);
                    Path path2 = Paths.get(PATH_TO_DESKTOP + projName + DOUBLE_FILE_SEP + subDirectory);
                    Files.createDirectories(path2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void removeDigits(ActionEvent event) {
        for (CheckBox checkBox : checkboxList) {
            if (event.getSource().equals(checkBox) && checkBox.isSelected()) {
                String checkboxText = checkBox.getText();
                String numberRemoved = checkboxText.replaceAll("\\d", "");

                directoryArrayList.add(numberRemoved);
            }
        }
    }


    // @link { Ref: http://stackoverflow.com/a/23512831/5942254 }
    private void createCheckboxArray() {

        List<String> text = Arrays.asList(
                "SiteMaps", "ProposedDrawings", "StructuralDrawings", "SupplierDetails",
                "FireDrawings", "Images", "Exports", "Imports", "Documents", "Emails");


        for (int i = 0; i < checkboxList.length; i++) {
            checkboxList[i] = new CheckBox((i + 1) + text.get(i));
        }
    }


    private VBox createRightPane() {


        Label label = new Label("Description:");

        TextArea textArea = new TextArea();
        textArea.setPrefWidth(200);

        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_right");
        vBox.setMinWidth(Consts.PANEL_WIDTH);
        VBox.setMargin(label, new Insets(30, 0, 0, 0));
        vBox.getChildren().addAll(label, textArea);

        return vBox;
    }


    private AnchorPane createBottomPane() {

        Button buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> {
            createDirectories();
            createProject();
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


    /**
     * Button 'Continue' method listener
     * that uses the Controller class create and populate the Project object
     * @see CreateNewProjectScene#createBottomPane
     */
    private void createProject() {

        // values from TextFields stored as strings
        getUserInput();


        project = Controller.getInstance().createProject(
                projectName, projectDate, projectAuthor, projectLocation, projectClient);


        addProjectToDB();

        mediator.changeToArchitectMenuScene();

    }


    private void addProjectToDB() {

        try {
            Platform.runLater(() -> {

                if (project != null) {
                    DBController.getInstance().addProject((Project) project);
                }

                DBController.getInstance().saveProject();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}