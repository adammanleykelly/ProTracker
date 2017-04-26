package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.helpers.Consts;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;

/**
 * Created by Adam on 04/03/2017.
 */
public class ClientViewProjectTimeline
{
    private Mediator mainMediator;

    public ClientViewProjectTimeline(Mediator mainMediator)
    {
        this.mainMediator = mainMediator;
    }

    public void start(Stage stage)
    {
        BorderPane pane = new BorderPane();
        pane.setTop(homeButtonContainer());
        pane.setCenter(createBarChart());

        pane.setBottom(navButtonContainer());

        Scene scene = new Scene(pane, Consts.APP_WIDTH, Consts.APP_HEIGHT);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + "Project Timeline");
        stage.show();
    }

    public Group createBarChart()
    {

        // define the X Axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setTickLabelRotation(90);
        xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList("Design", "Tender", "Construction")));


        // define the Y Axis
        NumberAxis yAxis = new NumberAxis(0, 60, 10);
        yAxis.setLabel("Percent");


        // create the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Fee Breakdown");

        //Prepare XYChart.Series
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series1.setName("Design");
        series2.setName("Tender");
        series3.setName("Construction");

        // Timeline Animation of the project progress, which is displayed as a percentage.
        // The animation is run only once - when the user enters the scene.
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        // set the XYChart.Series objects data
                        series1.getData().add(new XYChart.Data<>("Design", 55));

                        series2.getData().add(new XYChart.Data<>("Tender", 22.5));

                        series3.getData().add(new XYChart.Data<>("Construction", 22.5));

                    }
                }));

        tl.play();

        barChart.getData().addAll(series1, series2, series3);

        //Creating a Group object
        Group groupBarChart = new Group(barChart);

        return groupBarChart;
    }

    private Pane createProjectTimeline()
    {
        Label pTimeline = new Label("View Project Timeline");

        VBox vb = new VBox(pTimeline);
        vb.setSpacing(15);
        vb.setPadding(new Insets(1));
        vb.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(vb);

        return pane;
    }

    public HBox homeButtonContainer()
    {
        Button buttonHome = new Button("Home");
        Image logo = new Image(this.getClass().getResource("/Protracker_big.png").toString());
        ImageView iview1 = new ImageView(logo);
        iview1.setFitWidth(236.25);
        iview1.setFitHeight(62.5);

        buttonHome.setOnAction(event -> {
            try {
                mainMediator.changeToClientMenuScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        HBox hb = new HBox(buttonHome);
        hb.setSpacing(100);
        hb.setPadding(new Insets(10));
        hb.setAlignment(Pos.TOP_LEFT);

        HBox hb1 = new HBox(iview1);
        hb1.setSpacing(100);
        hb1.setPadding(new Insets(10));
        hb1.setAlignment(Pos.TOP_RIGHT);

        HBox hb2 = new HBox(hb, hb1);
        hb2.setSpacing(450);
        hb2.setPadding(new Insets(10));
        hb2.setAlignment(Pos.TOP_CENTER);

        return hb2;
    }

    public VBox navButtonContainer()
    {
        Button buttonStage = new Button("View Current Stage");
        Button buttonCancel = new Button("Cancel");
        Button buttonContinue= new Button("Continue");

        buttonCancel.setOnAction(event -> {
            try {
                mainMediator.changeToClientMenuScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonContinue.setOnAction(event -> {
            try {
                mainMediator.changeToClientMenuScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonStage.setOnAction(event -> {
            try {
                mainMediator.changeToClientStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        HBox hb = new HBox(buttonCancel, buttonContinue);
        hb.setSpacing(10);
        hb.setPadding(new Insets(10));
        hb.setAlignment(Pos.TOP_RIGHT);

        HBox hb1 = new HBox(buttonStage);
        hb1.setSpacing(10);
        hb1.setPadding(new Insets(10));
        hb1.setAlignment(Pos.TOP_RIGHT);

        VBox vb1 = new VBox(hb1, hb);
        vb1.setSpacing(5);
        vb1.setPadding(new Insets(10));
        vb1.setAlignment(Pos.TOP_RIGHT);

        return vb1;
    }


}














































