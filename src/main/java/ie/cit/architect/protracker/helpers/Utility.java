package ie.cit.architect.protracker.helpers;

import ie.cit.architect.protracker.gui.NavigationPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Created by brian on 27/02/17.
 */
public final class Utility {

    private Utility() {  }


    public static BorderPane createContainer(VBox vb1, VBox vb2, VBox vb3) {
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vb1);
        borderPane.setCenter(vb2);
        borderPane.setRight(vb3);

        NavigationPane navigationPane = new NavigationPane();
        AnchorPane nav = navigationPane.createNavButtons();

        borderPane.setBottom(nav);

        return borderPane;
    }


    public static BorderPane createContainer(VBox vb1, VBox vb2, VBox vb3, AnchorPane ap) {
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vb1);
        borderPane.setCenter(vb2);
        borderPane.setRight(vb3);
        borderPane.setBottom(ap);

        NavigationPane navigationPane = new NavigationPane();
        AnchorPane nav = navigationPane.createNavButtons();

        borderPane.setBottom(nav);

        return borderPane;
    }

    public static BorderPane createContainer(VBox vb1, ScrollPane scrollPane, VBox vb3) {
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vb1);
        borderPane.setCenter(scrollPane);
        borderPane.setRight(vb3);

        NavigationPane navigationPane = new NavigationPane();
        AnchorPane nav = navigationPane.createNavButtons();

        borderPane.setBottom(nav);

        return borderPane;
    }

}
