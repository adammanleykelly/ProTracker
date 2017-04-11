package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.helpers.Consts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by brian on 27/02/17.
 */
public class ClientMessages {


    private Mediator mainMediator;

    public ClientMessages(Mediator mainMediator) {
        this.mainMediator = mainMediator;
    }

    public void start(Stage stage)
    {
        BorderPane pane = new BorderPane();
        pane.setTop(homeButtonContainer());
        pane.setCenter(MessagesPane());
        pane.setBottom(navButtonContainer());
        Scene scene = new Scene(pane, Consts.APP_WIDTH, Consts.APP_HEIGHT);

        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " View Messages");
        stage.show();
    }

    private Pane MessagesPane()
    {
        BorderPane pane = new BorderPane();
        pane.setLeft(createLeftPane());
        pane.setCenter(createMiddlePane());
        pane.setRight(createRightPane());

        return pane;
    }

    private VBox createLeftPane() {
        VBox vBox = new VBox();
        vBox.setMinWidth(Consts.PANE_WIDTH);

        Label label = new Label("Employee: Georgia");

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(200);
        textArea.setPrefHeight(200);
        textArea.setWrapText(true);
        textArea.setText("@Joe - Hello Joe, how can I help");
        textArea.appendText("you today?");

        VBox.setMargin(label, new Insets(30,0,0,40));
        VBox.setMargin(textArea, new Insets(0,0,0,40));

        vBox.getChildren().addAll(label, textArea);

        return vBox;
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

    public HBox navButtonContainer()
    {
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

        HBox hb = new HBox(buttonCancel, buttonContinue);
        hb.setSpacing(10);
        hb.setPadding(new Insets(10));
        hb.setAlignment(Pos.TOP_RIGHT);

        return hb;
    }

}
