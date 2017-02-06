package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.helpers.Const;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;


public class MainApp extends Application
{

    private Scene homePageScene;
    private GridPane gridPaneHomeMenu;
    private Label labelTitle, labelSubTitle;
    private Button buttonClientSignIn, buttonArchitectSignIn;
    private TextInputDialog dialogInput;


    public static void main( String[] args )
    {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        homePageScene = new Scene(createHomeMenu(),800, 500);
        homePageScene.getStylesheets().add("/stylesheet.css");
        primaryStage.setScene(homePageScene);
        primaryStage.setTitle(Const.APPLICATION_TITLE);
        primaryStage.show();

    }


    private GridPane createHomeMenu() {

        gridPaneHomeMenu = new GridPane();
        gridPaneHomeMenu.setAlignment(Pos.CENTER);
        gridPaneHomeMenu.setPadding(new Insets(20, 0, 20, 20));
        gridPaneHomeMenu.setVgap(20);

        labelTitle = new Label(Const.APPLICATION_TITLE.toUpperCase());
        labelTitle.getStyleClass().add("label_title");

        labelSubTitle = new Label("Welcome");
        labelSubTitle.getStyleClass().add("label_subtitle");

        buttonClientSignIn = new Button("Sign in Client");
        buttonClientSignIn.setOnAction(event -> signInClientDialog());

        buttonArchitectSignIn = new Button("Sign in Architect");

        gridPaneHomeMenu.add(labelTitle, 0 ,1);
        GridPane.setHalignment(labelTitle, HPos.CENTER);

        gridPaneHomeMenu.add(labelSubTitle, 0, 2);
        GridPane.setHalignment(labelSubTitle, HPos.CENTER);

        gridPaneHomeMenu.add(buttonClientSignIn, 0, 6);
        GridPane.setHalignment(buttonClientSignIn, HPos.CENTER);

        gridPaneHomeMenu.add(buttonArchitectSignIn, 0, 8);
        GridPane.setHalignment(buttonArchitectSignIn, HPos.CENTER);

        return gridPaneHomeMenu;
    }


    public TextInputDialog signInClientDialog() {

        dialogInput = new TextInputDialog();
        dialogInput.setTitle("Sign In Client");
        dialogInput.setHeaderText("Please Sign In");
        dialogInput.setContentText("Your email address:");

        Optional<String> result = dialogInput.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
        }

        return dialogInput;

    }


    public int dummyTest() {
        int x = 2 + 2;

        return x;
    }

}
