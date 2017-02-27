package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.MainMediator;
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

    private BorderPane view;
    private MainMediator mainMediator;

    public ClientMenuScene(MainMediator mainMediator) {
        this.mainMediator = mainMediator;
    }


    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        pane.setTop(homeButtonContainer());
        pane.setCenter(createClientMenu());

        Scene scene = new Scene(pane, Consts.APP_WIDTH, Consts.APP_HEIGHT);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE);
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
                mainMediator.changeToHomeScene();
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
