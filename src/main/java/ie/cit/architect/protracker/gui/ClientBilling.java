package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.helpers.PdfInvoice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.io.IOException;

import java.net.URL;

/**
 * Created by Adam on 05/03/2017.
 */
public class ClientBilling
{
    private Mediator mainMediator;

    public ClientBilling (Mediator mainMediator)
    {
        this.mainMediator = mainMediator;
    }

    public void start(Stage stage)
    {
        BorderPane pane = new BorderPane();
        pane.setTop(homeButtonContainer());
        pane.setCenter(createClientBilling());
        pane.setBottom(createBottomPane());

        Scene scene = new Scene(pane, Consts.APP_WIDTH, Consts.APP_HEIGHT);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + "Billing");
        stage.show();
    }


    private Pane createClientBilling()
    {
        //Account Details
        Label ainfo = new Label("Account Details");
        ainfo.setFont(new Font("Arial", 30));
        Label accNum = new Label ("Account Number: +{account number}");
        Label cName = new Label ("Company Name: +{company name}");
        Label cAddress = new Label("Company Address: +{company address}");
        Label clName = new Label("ClientUser Name: + {client name}");
        Label clAddress = new Label("ClientUser Address: +{client address}");

        VBox vb = new VBox(ainfo, accNum, cName, cAddress, clName, clAddress);
        vb.setSpacing(5);
        vb.setPadding(new Insets(10));
        vb.setAlignment(Pos.TOP_LEFT);
        // Create our browser which contains the Google Map

        ClientBilling.Browser browser = new ClientBilling.Browser();

        //we need a StackPane to customise our browser's size
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(browser);
        stackPane.setPrefHeight(300);
        stackPane.setPrefWidth(400);

        VBox vbPdf = new VBox(stackPane);
        vbPdf.setSpacing(15);
        vbPdf.setPadding(new Insets(1));
        vbPdf.setAlignment(Pos.TOP_RIGHT);


        HBox hb1 = new HBox(vb, vbPdf);
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
        Button buttonChat = new Button("Chat");
        Button buttonSaveInvoice = new Button("Save PdfInvoice");


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
        buttonChat.setOnAction(event -> {
            try {
                mainMediator.changeToClientMessages();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

       /* buttonSaveInvoice.setOnAction(event -> {
            try {
                try {
                    PdfInvoice.getInstance().createPdfDocument();
                    MessageBox.show("PdfInvoice Saved to Desktop", "Saved");
                }catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
        // layout
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("anchorpane_color");
        AnchorPane.setTopAnchor(buttonCancel, 10.0);
        AnchorPane.setBottomAnchor(buttonCancel, 10.0);
        AnchorPane.setRightAnchor(buttonCancel, 150.0);

        AnchorPane.setBottomAnchor(buttonContinue, 10.0);
        AnchorPane.setRightAnchor(buttonContinue, 10.0);

        AnchorPane.setBottomAnchor(buttonChat, 10.0);
        AnchorPane.setRightAnchor(buttonChat, 280.0);

        AnchorPane.setBottomAnchor(buttonSaveInvoice, 10.0);
        AnchorPane.setRightAnchor(buttonSaveInvoice, 410.0);

        anchorPane.getChildren().addAll(buttonCancel, buttonContinue, buttonChat, buttonSaveInvoice);

        return anchorPane;
    }
    //Pdf Viewer via pdf.js
    public class Browser extends StackPane {

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        public Browser() {

            getStyleClass().add("browser");

            final URL urlPdf = getClass().getResource("/pdf.html");
            webEngine.load(urlPdf.toExternalForm());
            getChildren().add(browser);
        }
    }
}
