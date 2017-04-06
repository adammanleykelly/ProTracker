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
public class ArchitectMenuScene {


    private Mediator mediator;

    /**
     * Each GUI class has a constructor that passes a Mediator object.
     * Within this (and other GUI classes), this mediator reference will pass the selected stage
     * back to the Mediator, which will in turn 'start' that stage.
     * @see #architectMenu() -> ...mediator.changeToManageProjectScene();
     * Reference:
     * @link { http://stackoverflow.com/a/14168529/5942254 }
     */
    public ArchitectMenuScene(Mediator mediator) {
        this.mediator = mediator;
    }


    public void start(Stage stage) {

        BorderPane pane = new BorderPane();
        pane.setTop(homeButtonContainer());
        pane.setCenter(architectMenu());

        Scene scene = new Scene(pane, Consts.APP_WIDTH, Consts.APP_HEIGHT);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " Architects Menu");
        stage.show();
    }


    private GridPane architectMenu() {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 0, 20, 20));
        gridPane.setVgap(20);

        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Button btn4 = new Button();

        List<String> buttonText = Arrays.asList("Create New", "Manage Existing", "Billing / Invoice", "View Messages");
        List<Button> buttonList = Arrays.asList(btn1, btn2, btn3, btn4);

        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).getStyleClass().add("client_menu_buttons");
            buttonList.get(i).setText(buttonText.get(i));
        }

        for (Button button : buttonList) {
            button.setOnAction(event -> {
                if (event.getSource().equals(btn1)) {
                    mediator.changeToCreateProjectScene();
                } else if (event.getSource().equals(btn2)) {
                    mediator.changeToManageProjectScene();
                } else if (event.getSource().equals(btn3)) {
                    System.out.println("Billing");
                } else if (event.getSource().equals(btn4)) {
                    mediator.changeToViewMessagesScene();
                }
            });
        }

        gridPane.add(btn1, 0, 1);
        gridPane.add(btn2, 0, 2);
        gridPane.add(btn3, 0, 3);
        gridPane.add(btn4, 0, 4);

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
