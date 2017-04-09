package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.App.Mediator;
import ie.cit.architect.protracker.controller.Controller;
import ie.cit.architect.protracker.controller.DBController;
import ie.cit.architect.protracker.model.IUser;
import ie.cit.architect.protracker.model.User;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

/**
 * Created by brian on 3/2/2017.
 */
public class CustomArchitectDialog
{

    private Mediator mediator;
    private Dialog<Pair<String, String>> dialog;
    private String userEmail;
    private String userPass;


    public CustomArchitectDialog(Mediator mediator) {
        this.mediator = mediator;
    }


    // Custom Dialog ref: http://code.makery.ch/blog/javafx-dialogs-official/
    public Dialog<Pair<String, String>> signInArchitectDialog() {


        // Create the custom dialog.
        dialog = new Dialog<>();
        dialog.setTitle("Login Architect");
        dialog.setHeaderText("Please Sign In");

        // Set the icon (must be included in the project).
        dialog.setGraphic(new ImageView(this.getClass().getResource("/login_icon_architect.png").toString()));


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


        Button button = (Button) dialog.getDialogPane().lookupButton(loginButtonType);
        button.setOnAction(event -> mediator.changeToArchitectMenuScene());

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

                mediator.changeToArchitectMenuScene();

                return new Pair<>(textFieldEmail.getText(), password.getText());
            }

            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(emailPassword -> {
            userEmail = emailPassword.getKey();
            userPass = emailPassword.getValue();
        });

        Platform.runLater(() -> addUserToDB());

        return dialog;

    }

    public void addUserToDB() {

        IUser user = Controller.getInstance().createUser(userEmail, userPass);

        if (user != null) {
            DBController.getInstance().addUser((User) user);
        }

        DBController.getInstance().saveUser();

    }
}




