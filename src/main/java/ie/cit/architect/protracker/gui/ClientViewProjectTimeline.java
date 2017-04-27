package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.helpers.Consts;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
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
        pane.setCenter(createProjectTimeline());

        pane.setBottom(navButtonContainer());

        Scene scene = new Scene(pane, Consts.APP_WIDTH, Consts.APP_HEIGHT);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + "Project Timeline");
        stage.show();
    }


    private Pane createProjectTimeline()
    {
        Label pTimeline = new Label("View Project Timeline");

        Group rootGroup = new Group();
        VBox vb = new VBox(rootGroup);
        applyAnimation(rootGroup);
        vb.setSpacing(15);
        vb.setPadding(new Insets(1));
        vb.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(vb);

        return pane;
    }

    //Adjustable path for shape per point on timeline
    private Path generateStraightPath()
    {
        final Path path = new Path();
        path.getElements().add(new MoveTo(20,200));
        path.getElements().add(new LineTo(600,200));
        path.setOpacity(1.0);
        return path;
    }

    //Underlying Straight Path
    private Path generateStraightPath2()
    {
        final Path path = new Path();
        path.getElements().add(new MoveTo(20,200));
        path.getElements().add(new LineTo(700,200));
        path.setOpacity(1.0);
        return path;
    }

    private PathTransition generatePathTransition(final Shape shape, final Path path)
    {
        final PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(4.0));
        pathTransition.setDelay(Duration.seconds(0.5));
        pathTransition.setPath(path);
        pathTransition.setNode(shape);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(true);
        return pathTransition;
    }

    private double determinePathOpacity()
    {
        double pathOpacity = 1.0;
        return pathOpacity;
    }

    private void applyAnimation(final Group group)
    {
        final Circle circle = new Circle(20, 200, 15);
        circle.setFill(Color.BLUE);
        final Path path = generateStraightPath();
        final Path path2 = generateStraightPath2();
        group.getChildren().add(path);
        group.getChildren().add(path2);
        group.getChildren().add(circle);
        //a
        group.getChildren().add(new Circle(20, 200, 5));
        //b
        group.getChildren().add(new Circle(200, 200, 5));
        //c
        group.getChildren().add(new Circle(400, 200, 5));
        //d
        group.getChildren().add(new Circle(500, 200, 5));
        //e
        group.getChildren().add(new Circle(600, 200, 5));
        //f
        group.getChildren().add(new Circle(700, 200, 5));
        final PathTransition transition = generatePathTransition(circle, path);
        transition.play();
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














































