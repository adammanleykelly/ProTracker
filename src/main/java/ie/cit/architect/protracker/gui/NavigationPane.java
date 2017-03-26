package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Created by brian on 27/02/17.
 */
public class NavigationPane {

    private Mediator mediator;

    public NavigationPane(Mediator mediator) {
        this.mediator = mediator;
    }

    public NavigationPane() {  }


    public AnchorPane createNavButtons() {

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("anchorpane_color");

        Button buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(event -> System.exit(0));
        Button buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> {
            try {
                mediator.changeToHomeScene();
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
