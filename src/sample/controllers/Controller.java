package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.dbConnection.schemas.LoginSchema;

import java.io.IOException;
import java.sql.SQLException;

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

        System.out.println("User email: " + email);
        System.out.println("User pass: " + password);

        LoginSchema loginSchema = new LoginSchema();
        boolean flag = loginSchema.validate(email, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/mainLibraryPage.fxml"));
            signinPage.getChildren().setAll(pane);
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @FXML
    void handleCancel(ActionEvent event) {
        System.exit(0);
    }
}
