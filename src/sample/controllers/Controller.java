package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.dbConnection.schemas.LoginSchema;

import java.io.IOException;
import java.sql.SQLException;

import static sample.dbConnection.schemas.ChangingDataSchema.infoBox;

public class Controller {
    @FXML
    private AnchorPane signinPage;
    @FXML
    private TextField email_field;
    @FXML
    private PasswordField password_field;

    @FXML
    void goToSignupPage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/signUpForm.fxml"));
        signinPage.getChildren().setAll(pane);
    }

    @FXML
    void handleSignin(ActionEvent event) throws IOException, SQLException {
        String email = email_field.getText();
        String password = password_field.getText();

        LoginSchema loginSchema = new LoginSchema();
        boolean flag = loginSchema.validate(email, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/mainLibraryPage.fxml"));
            signinPage.getChildren().setAll(pane);
        }
    }

    public static void infobox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(headerText);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("resources/icons/libraryLogo.png"));
        alert.showAndWait();
    }

    @FXML
    void handleCancel(ActionEvent event) {
        System.exit(0);
    }
}
