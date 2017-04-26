package ie.cit.architect.protracker.gui;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.geometry.*;
/**
 * Created by Adam on 26/04/2017.
 */



public class MessageBox
{
    public static void show(String message, String title)
    {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(300);

        Label lbl = new Label();
        lbl.setText(message);
        lbl.setFont(new Font("Arial",20));
        Button btnOK = new Button();
        btnOK.setText("OK");
        btnOK.setOnAction(e -> stage.close());

        VBox pane = new VBox(20);
        pane.getChildren().addAll(lbl, btnOK);
        pane.setAlignment(Pos.CENTER);
        pane.setId("vb");

        Scene scene1 = new Scene(pane, 250 ,80);
        scene1.getStylesheets().addAll(("stylesheet.css"));
        stage.setScene(scene1);
        stage.showAndWait();
    }
}
