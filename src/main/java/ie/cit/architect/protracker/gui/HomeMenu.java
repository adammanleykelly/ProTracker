package ie.cit.architect.protracker.gui;

import ie.cit.architect.protracker.helpers.Consts;
import ie.cit.architect.protracker.model.User;
import ie.cit.architect.protracker.model.UserEmployee;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Optional;

/**
 * Created by brian on 10/02/17.
 */
public class HomeMenu {

   private MainAppMediator mainAppMediator;

    public HomeMenu(MainAppMediator mainAppMediator) {
        this.mainAppMediator = mainAppMediator;
    }


    private Button buttonSignInArchitect;
    private Button buttonSignInClient;
    private Button buttonBuilderScene;



    public void start(Stage stage) throws Exception {


        Scene scene = new Scene(
                createHomeMenu(), 800, 500);
        signInClient();
        scene.getStylesheets().add("/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle(Consts.APPLICATION_TITLE);
        stage.show();
    }



    private GridPane createHomeMenu() {

        GridPane gridPaneHomeMenu = new GridPane();
        gridPaneHomeMenu.setAlignment(Pos.CENTER);
        gridPaneHomeMenu.setPadding(new Insets(20, 0, 20, 20));
        gridPaneHomeMenu.setVgap(20);

        Label labelTitle = new Label(Consts.APPLICATION_TITLE.toUpperCase());
        labelTitle.getStyleClass().add("label_title");

        Label labelSubTitle = new Label("Welcome");
        labelSubTitle.getStyleClass().add("label_subtitle");

        buttonSignInClient = new Button("Sign in Client");
        buttonSignInClient.setOnAction(event -> signInClientDialog());

        buttonBuilderScene = new Button("Builder Menu");

        buttonSignInArchitect = new Button("Sign in Architect");


        gridPaneHomeMenu.add(labelTitle, 0, 1);
        GridPane.setHalignment(labelTitle, HPos.CENTER);

        gridPaneHomeMenu.add(labelSubTitle, 0, 2);
        GridPane.setHalignment(labelSubTitle, HPos.CENTER);

        gridPaneHomeMenu.add(buttonSignInClient, 0, 6);
        GridPane.setHalignment(buttonSignInClient, HPos.CENTER);

        gridPaneHomeMenu.add(buttonSignInArchitect, 0, 8);
        GridPane.setHalignment(buttonSignInArchitect, HPos.CENTER);

        gridPaneHomeMenu.add(buttonBuilderScene, 0, 10);
        GridPane.setHalignment(buttonBuilderScene, HPos.CENTER);

        return gridPaneHomeMenu;
    }


    public void signInClient() {

        // Components are individual classes:
        // https://stackoverflow.com/questions/32464698/java-how-do-i-start-a-standalone-application-from-the-current-one-when-both-are
        buttonBuilderScene.setOnAction(event -> {
            Parent view = new BuilderMenuScene().getView();
            Scene scene = new Scene(view);
            Stage stage = new Stage();
            stage.initOwner(buttonBuilderScene.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        });

    }

    // Custom Dialog ref: http://code.makery.ch/blog/javafx-dialogs-official/
    public Dialog<Pair<String, String>> signInClientDialog() {

        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Client");
        dialog.setHeaderText("Please Sign In");

        // Set the icon (must be included in the project).
        dialog.setGraphic(new ImageView(this.getClass().getResource("/login_icon_architect.png").toString()));

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField email = new TextField();
        email.setPromptText("Email");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");


        grid.add(new Label("Email:"), 0, 0);
        grid.add(email, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1 );

        // Label width
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);

        // TextField width
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);
        grid.getColumnConstraints().addAll(col1,col2);


        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> email.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(email.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(emailPassword -> {

            String userEmail = emailPassword.getKey();
            String userPass = emailPassword.getValue();

            User user = UserEmployee.getInstance(userEmail, userPass);

            System.out.println(user.toString());

        });


        return dialog;

    }

}
