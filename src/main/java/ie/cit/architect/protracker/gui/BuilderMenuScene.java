package ie.cit.architect.protracker.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

/**
 * Created by brian on 10/02/17.
 */
public class BuilderMenuScene {

    private GridPane view;

    public BuilderMenuScene() {

        view = new GridPane();
        view.setPrefHeight(400);
        view.setPrefWidth(300);
        Label label = new Label("Builder Menu");
        view.add(label, 1, 1);


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

        view.add(slider, 1, 2);
        view.add(progressBar, 2,2);
        view.add(progressIndicator, 3, 2);
    }

    public Parent getView() {
        return view;
    }

}