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
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by Adam on 06/03/2017.
 */
public class ClientContact
{
    private Mediator mainMediator;

    public ClientContact(Mediator mainMediator)
    {
        this.mainMediator = mainMediator;
    }

    HBox hb, hb1,hb2;
    VBox vb;
    public void start(Stage stage)
    {
        BorderPane pane = new BorderPane();
        pane.setTop(homeButtonContainer());
        pane.setCenter(createContactPage());
        pane.setBottom(createBottomPane());
        Scene scene = new Scene(pane, Consts.APP_WIDTH, Consts.APP_HEIGHT);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " Contact Page");
        stage.show();
    }


    private Pane createContactPage()
    {
        //Contact Info
        Label heading = new Label("Contact Info");
        heading.setFont(new Font("Arial", 30));
        Label email = new Label ("www.coveneyarchitects.com");
        Label phoneNumber = new Label ("Contact Number:(021) 4667788");
        Label address = new Label("Address: Monkstown, Cork,");


        VBox vb = new VBox(heading, address, phoneNumber, email);
        vb.setSpacing(5);
        vb.setPadding(new Insets(1));
        vb.setAlignment(Pos.CENTER_LEFT);
        vb.setId("whitebackground");

        //Opening Hours Info
        Label openHours = new Label("Opening Hours");
        openHours.setFont(new Font("Arial", 30));

        Label mon = new Label("Mon 09:00 to 18:00");
        Label tue = new Label("Tue 09:00 to 18:00");
        Label wed = new Label("Wed 09:00 to 18:00");
        Label thur = new Label("Thur 09:00 to 18:00");
        Label fri = new Label("Fri 09:00 to 18:00");

        VBox vbO = new VBox(openHours, mon,tue,wed,thur,fri);
        vbO.setSpacing(5);
        vbO.setPadding(new Insets(1));
        vbO.setAlignment(Pos.CENTER_LEFT);
        vbO.setId("whitebackground");

        //Map info

        // Create our browser which contains the Google Map
        Browser browser = new Browser();

        //we need a StackPane to customise our browser's size
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(browser);
        stackPane.setPrefHeight(300);
        stackPane.setPrefWidth(400);

        VBox vbMap = new VBox(stackPane);
        vbMap.setSpacing(15);
        vbMap.setPadding(new Insets(1));
        vbMap.setAlignment(Pos.TOP_RIGHT);


        VBox vb2 = new VBox(vb,vbO);
        vb2.setSpacing(15);
        vb2.setPadding(new Insets(10));
        vb2.setAlignment(Pos.TOP_LEFT);

        HBox hb1 = new HBox(vb2, vbMap);
        hb1.setSpacing(150);
        hb1.setPadding(new Insets(1));
        hb1.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(hb1);

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

        hb = new HBox(buttonHome);
        hb.setSpacing(100);
        hb.setPadding(new Insets(10));
        hb.setAlignment(Pos.TOP_LEFT);

        hb1 = new HBox(iview1);
        hb1.setSpacing(100);
        hb1.setPadding(new Insets(10));
        hb1.setAlignment(Pos.TOP_RIGHT);

        hb2 = new HBox(hb, hb1);
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



    public class Browser extends StackPane
    {
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        public Browser()
        {
            getStyleClass().add("browser");
            final URL urlGoogleMaps = getClass().getResource("/GoogleMapsV3.html");
            webEngine.load(urlGoogleMaps.toExternalForm());
            webEngine.setJavaScriptEnabled(true);
            getChildren().add(browser);
        }
    }
}
