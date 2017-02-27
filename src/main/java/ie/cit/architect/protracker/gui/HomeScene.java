package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.MainMediator;
import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.model.User;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * Created by brian on 10/02/17.
 */


public class HomeScene {

    private MainMediator mainMediator;

    // Composition - passing a reference of MainMediator to HomeScene's constructor. Now HomeScene 'has-a' MainMediator
    public HomeScene(MainMediator mediator) {
        this.mainMediator = mediator;
    }


    private Button buttonSignInArchitect;
    private Button buttonSignInClient;
    private User user;


    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(
                createHomeMenu(), Consts.APP_WIDTH, Consts.APP_HEIGHT);
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE);
        stage.show();
    }


    private GridPane createHomeMenu() {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 0, 20, 20));
        gridPane.setVgap(20);

        Label labelTitle = new Label(Consts.APPLICATION_TITLE.toUpperCase());
        labelTitle.getStyleClass().add("label_title");

        Label labelSubTitle = new Label("Welcome");
        labelSubTitle.getStyleClass().add("label_subtitle");

        buttonSignInClient = new Button("Sign in Client");
        buttonSignInArchitect = new Button("Sign in Architect");
        buttonSignInClient.setMinWidth(150);
        buttonSignInArchitect.setMinWidth(150);


        List<Button> buttonList = Arrays.asList(buttonSignInClient, buttonSignInArchitect);

        for (Button button : buttonList) {
            button.setOnAction(event -> signInDialog());
        }

        gridPane.add(labelTitle, 0, 1);
        GridPane.setHalignment(labelTitle, HPos.CENTER);

        gridPane.add(labelSubTitle, 0, 2);
        GridPane.setHalignment(labelSubTitle, HPos.CENTER);

        gridPane.add(buttonSignInClient, 0, 6);
        GridPane.setHalignment(buttonSignInClient, HPos.CENTER);

        gridPane.add(buttonSignInArchitect, 0, 8);
        GridPane.setHalignment(buttonSignInArchitect, HPos.CENTER);

        return gridPane;
    }



    // Custom Dialog ref: http://code.makery.ch/blog/javafx-dialogs-official/
    private Dialog<Pair<String, String>> signInDialog() {

        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Client");
        dialog.setHeaderText("Please Sign In");

        // Set the icon (must be included in the project).
        if (buttonSignInClient.isArmed()) {
            dialog.setGraphic(new ImageView(this.getClass().getResource("/login_icon_client.png").toString()));
        } else if (buttonSignInArchitect.isArmed()) {
            dialog.setGraphic(new ImageView(this.getClass().getResource("/login_icon_architect.png").toString()));
        }


        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);


        // Create the username and password labels and fields.
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField textFieldEmail = new TextField();
        textFieldEmail.setPromptText("Email");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Label labelCheckEmail = new Label();

        gridPane.add(new Label("Email:"), 0, 0);
        gridPane.add(labelCheckEmail, 3, 0);
        gridPane.add(textFieldEmail, 1, 0);
        gridPane.add(new Label("Password:"), 0, 1);
        gridPane.add(password, 1, 1);

        // Label width
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);

        // TextField width
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);
        gridPane.getColumnConstraints().addAll(col1, col2);


        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);

        //TODO : change to true and uncomment code after testing
        loginButton.setDisable(false);
        // disable the Login button and set prompt, if user enters incorrect email address
        //Off for testing
//        textFieldEmail.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            if (!(newValue.trim().matches(Consts.VALID_EMAIL_REGEX))) {
//                loginButton.setDisable(true);
//                labelCheckEmail.setText("enter a valid email"); // display error message
//                labelCheckEmail.setTextFill(Color.RED);
//            } else {
//                loginButton.setDisable(false);
//                labelCheckEmail.setText("");
//            }
//        });


        dialog.getDialogPane().setContent(gridPane);

        // colour the dialogs header
        GridPane grid = (GridPane)dialog.getDialogPane().lookup(".header-panel");
        grid.setStyle("-fx-background-color: white;");

        // Request focus on the username field by default.
        Platform.runLater(() -> textFieldEmail.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(textFieldEmail.getText(), password.getText());
            }
            return null;
        });


        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(emailPass -> {

            String userEmail = emailPass.getKey();
            String userPass = emailPass.getValue();

            user = User.getInstance(userEmail, userPass);

            // // open new Client / Architect menu scene
            if (buttonSignInClient.isArmed()) {
                mainMediator.changeToClientMenuScene();
            } else if (buttonSignInArchitect.isArmed()) {
                mainMediator.changeToArchitectMenuScene();
            }

            if (user != null)
                System.out.println(user.toString());

        });

        return dialog;

    }

}
