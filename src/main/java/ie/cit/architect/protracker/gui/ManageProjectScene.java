package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.controller.Controller;
import ie.cit.architect.protracker.controller.DBController;
import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.persistors.MongoDBPersistor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by brian on 27/02/17.
 */
public class ManageProjectScene {



    private String projectName, selectedProjectName, selectedProjectClient;
    private double selectedProjectFee, doubleDialogInput;
    private static final String FILE_SEP = File.separator;
    private static final String DOUBLE_FILE_SEP = FILE_SEP + FILE_SEP;
    private static final String PATH_TO_DESKTOP = System.getProperty("user.home") + FILE_SEP + "Desktop" + FILE_SEP;
    private static final String CURR_DIR = "src/main/resources";
    private static final String TXT_FILE_DESC = "txt files (*.txt)";
    private static final String PDF_FILE_DESC = "PdfInvoice files (*.PdfInvoice)";
    private static final String TXT_FILE_EXT = "*.txt";
    private static final String PDF_FILE_EXTENSION = "*.PdfInvoice";
    private VBox vBoxMiddlePane;
    private ObservableList<CheckBox> checkBoxList;
    private ObservableList<Label> labelList;
    private Button buttonDelete, buttonRename;
    private Label labelDate;
    private String editDialogInput;
    private HBox hBoxProject;
    private Stage window;


    private Mediator mediator;

    public ManageProjectScene(Mediator mediator) {
        this.mediator = mediator;
        checkBoxList = FXCollections.observableArrayList();
        labelList = FXCollections.observableArrayList();
    }


    public void start(Stage stage) {

        window = stage;

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(createLeftPane());
        borderPane.setCenter(createMiddlePane());
        borderPane.setRight(createRightPane());
        borderPane.setBottom(createBottomPane());

        int sceneWidth = 1000;
        Scene scene = new Scene(
                borderPane,
                sceneWidth, Consts.APP_HEIGHT);

        scene.getStylesheets().add("/stylesheet.css");
        window.setScene(scene);
        window.setTitle(Consts.APPLICATION_TITLE + " Manage project");
        window.show();
    }


    private VBox createRightPane() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("hbox_left");
        vBox.setMinWidth(Consts.PANE_WIDTH);

        Button changeFee = new Button("Change Fee");
        changeFee.setOnAction(event -> {
            selectedProject();

            editBilling();

        });;


        Button buttonViewStage = new Button("View Stage");
        buttonViewStage.setOnAction(event -> createTimelineScene());


        buttonRename = new Button("Rename");
        buttonRename.setOnAction(event -> updateNameDialog());
        buttonRename.setDisable(true);

        buttonDelete = new Button("Delete");
        buttonDelete.setOnAction(event -> {
            deleteProject();
            removeControlsFromScrollPane();
        });

        ObservableList<Button> buttonList =
                FXCollections.observableArrayList(changeFee, buttonViewStage, buttonRename, buttonDelete);

        for (Button button : buttonList) {
            button.setFocusTraversable(false);
            button.setMinWidth(150);
            VBox.setMargin(button, new Insets(0, 37.5, 50, 37.5));
        }

        Label labelOperations = new Label("Operations:");

        VBox.setMargin(labelOperations, new Insets(30, 0, 50, 10));

        // add controls to VBox
        vBox.getChildren().addAll(labelOperations, changeFee, buttonViewStage, buttonRename, buttonDelete);

        return vBox;
    }



    private ScrollPane createMiddlePane() {

        vBoxMiddlePane = new VBox();
        vBoxMiddlePane.getStyleClass().add("hbox_middle");
        int paneWidth = 300;
        vBoxMiddlePane.setMinWidth(paneWidth);

        Label labelName = new Label("Name");
        Label labelDateModified = new Label("Date Modified");
        HBox.setMargin(labelName, new Insets(10, 0, 0, 35));
        HBox.setMargin(labelDateModified, new Insets(10, 0, 0, 135));
        HBox hBox = new HBox();
        hBox.getChildren().addAll(labelName, labelDateModified);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox, vBoxMiddlePane);

        addProjectsToMiddlePane();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(vBox);

        return scrollPane;
    }




    /**
     * CheckBoxes populated with the project 'name' field from MongoDB
     * @see DBController#selectRecords()
     * @see MongoDBPersistor#createProjectList()
     */
    private void addProjectsToMiddlePane() {

        ArrayList<Project> projectArrayList = DBController.getInstance().selectRecords();

        for(Project project : projectArrayList) {

            CheckBox checkBox = new CheckBox(project.getName());
            checkBoxList.add(checkBox);

            labelDate = new Label(project.getFormattedDate());
            labelList.add(labelDate);

            hBoxProject = new HBox();
            labelDate.getStyleClass().add("label_padding");

            checkBox.getStyleClass().add("checkbox_padding");
            hBoxProject.getChildren().addAll(checkBox, labelDate);

            vBoxMiddlePane.getChildren().add(hBoxProject);


        }

        getProjectByName();

    }

    private String getProjectName() {

        for(CheckBox checkBox : checkBoxList) {
            checkBox.setOnAction(event -> {
                projectName =  checkBox.getText();
            });
        }

        return projectName;

    }



    private Project selectedProject() {

        String name = getProjectName();

        // read the checkbox selected project from the db
        Project project = DBController.getInstance().readProjectDetails(name);

        selectedProjectName = project.getName();
        selectedProjectClient = project.getClientName();
        selectedProjectFee = project.getFee();

        return project;
    }



    private void editBilling() {

        double newFee =  updateFeeDialog();

        Controller.getInstance().createInvoice(selectedProjectName, selectedProjectClient, selectedProjectFee);

        Controller.getInstance().editBilling(selectedProjectName, selectedProjectClient, newFee);

        DBController.getInstance().updateProjectFee(selectedProjectFee, newFee);

    }


    public Double updateFeeDialog() {
        javafx.scene.control.Dialog dialog = new TextInputDialog();
        dialog.setTitle("Project Fee");
        dialog.setHeaderText("Enter the new project fee");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            doubleDialogInput = Double.valueOf(result.get());
        }
        return doubleDialogInput;
    }



    private Project getProjectByName() {
        for(CheckBox checkBox : checkBoxList) {
            checkBox.setOnAction(event -> {
                projectName =  checkBox.getText();
                buttonRename.setDisable(false);

            });
        }
        Project project = new Project();
        project.setName(projectName);

        return project;
    }


    private void removeControlsFromScrollPane() {

        for (int i = 0; i < checkBoxList.size(); i++) {
            if(checkBoxList.get(i).isSelected()) {
                checkBoxList.get(i).setVisible(false);
                checkBoxList.get(i).setManaged(false);
                labelList.get(i).setVisible(false);
                labelList.get(i).setManaged(false);
            }
        }
    }


    private void updateNameDialog() {
        Dialog dialog = new TextInputDialog();
        dialog.setTitle("Edit Project Name");
        dialog.setHeaderText("Enter the new project name");

        Optional<String> result = dialog.showAndWait();

        if(result.isPresent()) {
            editDialogInput = result.get();
        }


        editProjectName();


        vBoxMiddlePane.getChildren().clear();

        addProjectsToMiddlePane();
    }


    // Update
    private void editProjectName() { DBController.getInstance().updateProjectName(projectName, editDialogInput); }




    // Delete
    // 'deleteButton' listener which calls the Controller to remove the selected project from the database
    private void deleteProject() {
        DBController.getInstance().deleteProject(getProjectByName());
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
            mediator.changeToArchitectMenuScene();
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

        File file;

        FileChooser fileChooser = new FileChooser();


        // open create project directory
        fileChooser.setInitialDirectory(
                new java.io.File(PATH_TO_DESKTOP + projectName + DOUBLE_FILE_SEP));


        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            openFile(file);
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




    //*** New Scene ***//

    private void createTimelineScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(createBottomPaneTimLine());
        borderPane.setCenter(createBarChart());
        borderPane.setLeft(createLeftBillingPane());
        borderPane.getStyleClass().add("border_pane");


        Scene scene = new Scene(borderPane, 1000, 500);
        scene.getStylesheets().add("/stylesheet.css");
        window.setScene(scene);
        window.setTitle(Consts.APPLICATION_TITLE + " View Stage");
        window.show();
    }

    private VBox createLeftBillingPane() {
        VBox vBox = new VBox();
        Button buttonDesign = new Button("Send Design Invoice");
        Button buttonPlanning = new Button("Send Planning Invoice");
        Button buttonTender = new Button("Send Tender Invoice");
        Button buttonConstruction = new Button("Send Construction Invoice");
        VBox.setMargin(buttonDesign, new Insets(100, 37.5, 0, 37.5));
        VBox.setMargin(buttonPlanning, new Insets(30, 37.5, 0, 37.5));
        VBox.setMargin(buttonTender, new Insets(30, 37.5, 0, 37.5));
        VBox.setMargin(buttonConstruction, new Insets(30, 37.5, 0, 37.5));
        vBox.getChildren().addAll(buttonDesign, buttonPlanning, buttonTender, buttonConstruction);
        return vBox;
    }


    private Group createBarChart() {

        // define the X Axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setTickLabelRotation(90);
        xAxis.setCategories(FXCollections.observableArrayList(
                Arrays.asList(Consts.DESIGN, Consts.PLANNING, Consts.TENDER, Consts.CONSTRUCTION)));


        // define the Y Axis
        NumberAxis yAxis = new NumberAxis(0, 50000, 10000);
        yAxis.setLabel("Fee");


        String name = getProjectName();

        //** get
        Project project = DBController.getInstance().readProjectDetails(name);
        String projectName = project.getName();
        double fee = project.getFee();

        // create the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Total fee of €" + fee + " for " +  projectName);

        //Prepare XYChart.Series
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        XYChart.Series<String, Number> series4 = new XYChart.Series<>();

        series1.setName(Consts.DESIGN);
        series2.setName(Consts.PLANNING);
        series3.setName(Consts.TENDER);
        series4.setName(Consts.CONSTRUCTION);


        double yValueDesign = fee * 0.5;
        double yValuePlanning = fee * 0.2;
        double yValueTender = fee * 0.1;
        double yValueConstruction = fee * 0.4;



        // Timeline Animation of the project progress, which is displayed as a percentage on the Y-Axis.
        // The animation is run only once - when the user enters the scene.
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        // set the XYChart.Series objects data
                        series1.getData().add(new XYChart.Data<>(Consts.DESIGN, yValueDesign));

                        series2.getData().add(new XYChart.Data<>(Consts.PLANNING, yValuePlanning));

                        series3.getData().add(new XYChart.Data<>(Consts.TENDER, yValueTender));

                        series4.getData().add(new XYChart.Data<>(Consts.CONSTRUCTION, yValueConstruction));
                    }
                }));

        tl.play();

        barChart.getData().addAll(series1, series2, series3, series4);

        //Creating a Group object
        Group groupBarChart = new Group(barChart);

        return groupBarChart;
    }


    private AnchorPane createBottomPaneTimLine() {

        Button buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> {
            mediator.changeToArchitectMenuScene();
        });

        Button buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(event -> mediator.changeToManageProjectScene());

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





}