package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.helpers.Consts;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * Created by brian on 10/02/17.
 */
public class ClientMenuScene {

    private Mediator mediator;

    /**
     * Each GUI class has a constructor that passes a Mediator object.
     * Within this (and other GUI classes), this mediator reference will pass the selected stage
     * back to the Mediator, which will in turn 'start' that stage.
     * @see #createClientMenu() () -> ...mediator.changeToViewMessagesScene();
     * Reference:
     * @link { http://stackoverflow.com/a/14168529/5942254 }
     */
    public ClientMenuScene(Mediator mediator) {
        this.mediator = mediator;
    }


    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        pane.setTop(homeButtonContainer());
        pane.setCenter(createClientMenu());

        Scene scene = new Scene(pane, Consts.APP_WIDTH, Consts.APP_HEIGHT);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " Client Menu");
        stage.show();
    }


    private GridPane createClientMenu() {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 0, 20, 20));
        gridPane.setVgap(20);

        Button btnTimeline = new Button("View Project Timeline");
        Button btnProgress = new Button("View Project Progress");
        Button btnBilling = new Button("View Billing");
        Button btnAppointments = new Button("View Appointments");


        btnAppointments.setOnAction(event -> mediator.changeToViewMessagesScene()); // to be changed


        List<Button> buttonList = Arrays.asList(btnTimeline, btnProgress, btnBilling, btnAppointments);
        for(Button butons : buttonList) {
            butons.getStyleClass().add("client_menu_buttons");
        }


        gridPane.add(btnTimeline, 0 ,1);
        gridPane.add(btnProgress, 0, 2);
        gridPane.add(btnBilling, 0, 3);
        gridPane.add(btnAppointments, 0, 4);


        return gridPane;
    }


    public AnchorPane homeButtonContainer() {

        AnchorPane anchorPane = new AnchorPane();

        Button buttonHome = new Button("Home");
        buttonHome.setOnAction(event -> {
            try {
                mediator.changeToHomeScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        AnchorPane.setTopAnchor(buttonHome, 10.0);
        AnchorPane.setLeftAnchor(buttonHome, 10.0);
        anchorPane.getChildren().add(buttonHome);

        return anchorPane;
    }



}
