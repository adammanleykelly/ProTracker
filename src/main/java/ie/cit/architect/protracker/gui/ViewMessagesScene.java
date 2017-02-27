package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.MainMediator;
import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.helpers.Utility;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by brian on 27/02/17.
 */
public class ViewMessagesScene {


    private MainMediator mainMediator;

    public ViewMessagesScene(MainMediator mainMediator) {
        this.mainMediator = mainMediator;
    }

    public void start(Stage stage) {

        Scene scene = new Scene(
                Utility.createContainer(createLeftPane(), createMiddlePane(), createRightPane()),
                Consts.APP_WIDTH, Consts.APP_HEIGHT);

        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " View Messages");
        stage.show();
    }



    private VBox createLeftPane() {
        VBox vBox = new VBox();
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        Label label = new Label("Employee: Georgia");

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(200);
        textArea.setPrefHeight(200);

        VBox.setMargin(label, new Insets(30,0,0,40));
        VBox.setMargin(textArea, new Insets(0,0,0,40));

        vBox.getChildren().addAll(label, textArea);

        return vBox;
    }


    private VBox createMiddlePane() {
        VBox vBox = new VBox();
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        Label label = new Label("Client: Joe");

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(200);
        textArea.setPrefHeight(200);

        VBox.setMargin(label, new Insets(30,0,0,40));
        VBox.setMargin(textArea, new Insets(0,0,0,40));


        vBox.getChildren().addAll(label, textArea);

        return vBox;
    }


    private VBox createRightPane() {
        VBox vBox = new VBox();
        vBox.setMinWidth(Consts.PANEL_WIDTH);

        Label label = new Label("Compose Message:");

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(200);
        textArea.setPrefHeight(200);

        VBox.setMargin(label, new Insets(30,0,0,40));
        VBox.setMargin(textArea, new Insets(0,0,0,40));

        vBox.getChildren().addAll(label, textArea);

        return vBox;
    }

}
