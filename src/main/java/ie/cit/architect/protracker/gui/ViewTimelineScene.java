package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.helpers.Consts;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;

/**
 * Created by brian on 13/04/17.
 */
public class ViewTimelineScene {


    private Mediator mediator;

    public ViewTimelineScene(Mediator mediator) {
        this.mediator = mediator;
    }


    public void start(Stage stage) {

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(createBottomPane());
        borderPane.setCenter(createBarChart());

        Scene scene = new Scene(borderPane, 1000, 500);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " View Stage");
        stage.show();

    }


    private Group createBarChart() {

        // define the X Axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>
                observableArrayList(Arrays.asList("Planning", "Tender", "Construction")));
        xAxis.setLabel("");

        // define the Y Axis
        NumberAxis yAxis = new NumberAxis(0,100,25);
        yAxis.setLabel("Percentage Complete");

        // create the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Project Stage");

        //Prepare XYChart.Series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Apartments Fermoy");


        // Timeline Animation of the project progress, which is displayed as a percentage.
        // The animation is run only once - when the user enters the scene.
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                            // set the XYChart.Series objects data
                            series.getData().add(new XYChart.Data<>("Planning", 100));
                            series.getData().add(new XYChart.Data<>("Tender", 75));
                            series.getData().add(new XYChart.Data<>("Construction", 25));
                        }
                }));

        tl.play();

        barChart.getData().add(series);

        //Creating a Group object
        Group groupBarChart = new Group(barChart);

        return groupBarChart;
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


}
