package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.MainMediator;
import ie.cit.architect.protracker.helpers.Consts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by brian on 10/02/17.
 */
public class CreateNewProjScene {


    private MainMediator mainMediator;

    public CreateNewProjScene(MainMediator mainMediator) {
        this.mainMediator = mainMediator;
    }


    public void start(Stage stage) {
        Scene scene = new Scene(
                createLayout(), 800, 500);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE);
        stage.show();
    }


    private GridPane createLayout() {

        GridPane gridPane = new GridPane();
        gridPane.setPrefHeight(400);
        gridPane.setPrefWidth(300);

        Button button = new Button("Menu");
        button.setOnAction(event -> mainMediator.changeToArchitectMenuScene());

        gridPane.add(button, 1, 1);


        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(50);
        ProgressBar progressBar = new ProgressBar(0.6);
        ProgressIndicator progressIndicator = new ProgressIndicator(0.6);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                progressBar.setProgress(newValue.doubleValue() / 50);
                progressIndicator.setProgress(newValue.doubleValue() / 50);
            }
        });

        gridPane.add(slider, 1, 2);
        gridPane.add(progressBar, 2, 2);
        gridPane.add(progressIndicator, 3, 2);


        return gridPane;
    }
}