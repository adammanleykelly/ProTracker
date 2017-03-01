package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.MainMediator;
import ie.cit.architect.protracker.helpers.Consts;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by brian on 27/02/17.
 */
public class NavigationPane {

    private MainMediator mainMediator;

    public NavigationPane(MainMediator mainMediator) {
        this.mainMediator = mainMediator;
    }

    public NavigationPane() {  }


    public void start(Stage stage) {
        Scene scene = new Scene(createNavButtons(),
                Consts.APP_WIDTH, Consts.APP_HEIGHT);

        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE + " Navigation");
        stage.show();
    }


    public AnchorPane createNavButtons() {

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("anchorpane_color");

        Button buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(event -> System.exit(0));
        Button buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> {
            try {
                mainMediator.changeToHomeScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        AnchorPane.setTopAnchor(buttonCancel, 10.0);
        AnchorPane.setBottomAnchor(buttonCancel, 10.0);
        AnchorPane.setRightAnchor(buttonCancel, 150.0);
        AnchorPane.setBottomAnchor(buttonContinue, 10.0);
        AnchorPane.setRightAnchor(buttonContinue, 10.0);
        anchorPane.getChildren().addAll(buttonCancel, buttonContinue);

        return anchorPane;
    }

}
