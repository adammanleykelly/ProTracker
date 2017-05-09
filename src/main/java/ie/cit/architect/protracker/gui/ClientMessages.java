package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.controller.DBController;
import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.model.ChatMessage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        pane.setBottom(createBottomPane());
        Scene scene = new Scene(pane, Consts.APP_WIDTH, Consts.APP_HEIGHT);

        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " View Messages");
        stage.show();
    }

    private Pane MessagesPane() {
        BorderPane pane = new BorderPane();
        pane.setRight(createRightPane());
        pane.setLeft(createLeftPane());
        return pane;
    }

    private VBox createLeftPane()
    {
        VBox vBox = new VBox();
        vBox.setMinWidth(Consts.PANE_WIDTH);

        Label label = new Label("Incoming Chat Message:");

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(200);
        textArea.setPrefHeight(200);
        textArea.setWrapText(true);

        // read chat message from the message saved in ArchitectsMenuScene
        // and have the ChatMessage object reference it
        ChatMessage chatMessage = DBController.getInstance().readMessage();

        // set the TextArea with this reference
        textArea.setText(chatMessage.getMessage());

        VBox.setMargin(label, new Insets(30, 0, 0, 40));
        VBox.setMargin(textArea, new Insets(0, 0, 0, 40));

        vBox.getChildren().addAll(label, textArea);

        return vBox;
    }

    private VBox createRightPane() {
        VBox vBox = new VBox();
        vBox.setMinWidth(Consts.PANE_WIDTH);

        Label label = new Label("Compose Chat Message:");

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(200);
        textArea.setPrefHeight(200);
        textArea.setWrapText(true);

        Button sendButton = new Button("Send");


        sendButton.setOnAction(event -> {

            String input = textArea.getText();

            // create ChatMessage object with TextArea input as its message
            ChatMessage message = new ChatMessage(input);

            // save the message in MongoDB
            DBController.getInstance().saveMessage(message);

        });



        VBox.setMargin(label, new Insets(30,0,0,40));
        VBox.setMargin(textArea, new Insets(0,0,0,40));
        VBox.setMargin(sendButton, new Insets(0,0,0,40));


        vBox.getChildren().addAll(label, textArea, sendButton);

        return vBox;
    }

    public HBox homeButtonContainer() {
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

    private AnchorPane createBottomPane() {

        Button buttonCancel = new Button("Cancel");
        Button buttonContinue = new Button("Continue");

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
