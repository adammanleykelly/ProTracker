package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.helpers.Consts;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Created by brian on 27/02/17.
 */
public class ViewMessagesScene {


    private Mediator mediator;


    public ViewMessagesScene(Mediator mediator) {
        this.mediator = mediator;
    }

    public void start(Stage stage) {

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(createLeftPane());
        borderPane.setCenter(createMiddlePane());
        borderPane.setRight(createRightPane());
        borderPane.setBottom(createBottomPane());

        Scene scene = new Scene(borderPane,
                Consts.APP_WIDTH, Consts.APP_HEIGHT);

        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " View Messages");
        stage.show();
    }



    public VBox createLeftPane() {
        VBox vBoxLeftPane = new VBox();
        vBoxLeftPane.setMinWidth(Consts.PANE_WIDTH);

        Label label = new Label("Employee: Georgia");

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(200);
        textArea.setPrefHeight(200);
        textArea.setWrapText(true);
        textArea.setText("@Joe - Hello Joe, how can I help");
        textArea.appendText("you today?");

        VBox.setMargin(label, new Insets(30,0,0,40));
        VBox.setMargin(textArea, new Insets(0,0,0,40));

        vBoxLeftPane.getChildren().addAll(label, textArea);

        return vBoxLeftPane;
    }


    private VBox createMiddlePane() {
        VBox vBox = new VBox();
        vBox.setMinWidth(Consts.PANE_WIDTH);

        Label label = new Label("Client: Joe");

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(200);
        textArea.setPrefHeight(200);
        textArea.setWrapText(true);
        textArea.setText("@Georgia - Hi Georgia, I'm looking");
        textArea.appendText("to make an appointm to discuss ");
        textArea.appendText("the next stage of the project.");


        VBox.setMargin(label, new Insets(30,0,0,40));
        VBox.setMargin(textArea, new Insets(0,0,0,40));


        vBox.getChildren().addAll(label, textArea);

        return vBox;
    }


    private VBox createRightPane() {
        VBox vBox = new VBox();
        vBox.setMinWidth(Consts.PANE_WIDTH);

        Label label = new Label("Compose Message:");

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(200);
        textArea.setPrefHeight(200);
        textArea.setWrapText(true);
        textArea.setText("@Joe - Let me have a quick look to see");
        textArea.appendText("what days we have available.");

        VBox.setMargin(label, new Insets(30,0,0,40));
        VBox.setMargin(textArea, new Insets(0,0,0,40));

        vBox.getChildren().addAll(label, textArea);

        return vBox;
    }



    private AnchorPane createBottomPane() {

        Button buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> {
            System.out.println("hello");
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
