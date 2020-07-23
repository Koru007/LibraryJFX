package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.dbConnection.schemas.AddDataSchema;

import java.io.IOException;

public class Controller2 {
    @FXML
    private AnchorPane signupPage;
    @FXML
    private TextField pesel_field;
    @FXML
    private TextField name_field;
    @FXML
    private TextField sureName_field;
    @FXML
    private TextField address_field;
    @FXML
    private TextField mail_field;
    @FXML
    private TextField phone_field;


    @FXML
    void back(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/logInPage.fxml"));
        signupPage.getChildren().setAll(pane);
    }

    @FXML
    void saveNewClient(ActionEvent event) throws IOException {

        String pesel = pesel_field.getText();
        String name = name_field.getText();
        String sureName = sureName_field.getText();
        String mail = mail_field.getText();
        String address = address_field.getText();
        String phone = phone_field.getText();

        if (!(pesel.equals("") || name.equals("") || sureName.equals("") || mail.equals("") || address.equals("") || phone.equals(""))) {
            AddDataSchema addDataSchema = new AddDataSchema();
            addDataSchema.addNewClientToDB(pesel, name, sureName, address, mail, phone);

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/logInPage.fxml"));
            signupPage.getChildren().setAll(pane);
            System.out.println("Pesel: " + pesel + "\nName: " + name + "\nSurename: " + sureName + "\nMail: " + mail + "\nBirthday: " + address + "\nPhone: " + phone);

        } else {
            infoBox("Please complete all text fields", null, "SignUp failed");
        }
//      SHOWING DATA


    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @FXML
    void handleCancle(ActionEvent event) {
        System.exit(0);
    }
}
