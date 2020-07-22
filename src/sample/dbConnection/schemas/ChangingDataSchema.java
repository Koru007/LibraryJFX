package sample.dbConnection.schemas;

import javafx.scene.control.Alert;
import sample.dbConnection.DBHandler;

import java.sql.*;

public class ChangingDataSchema {
    DBHandler dbHandler = new DBHandler();
    Connection connection = dbHandler.getDbConnection();
    private Statement ste_1;

    public void deleteClientByPesel(String peselToDelete) {
        try {
            ste_1 = connection.createStatement();
            ResultSet res = ste_1.executeQuery("SELECT COUNT(*) FROM book_order WHERE pesel = '" + peselToDelete + "'");
            res.next();
            int col = res.getInt(1);
            if (col == 0) {
                ste_1.executeUpdate("DELETE FROM contact WHERE pesel = '" + peselToDelete + "'");
                ste_1.executeUpdate("DELETE FROM client WHERE pesel = '" + peselToDelete + "'");

                System.out.println("Client with pesel: " + peselToDelete + " DELETED SUCCESSFULLY");
            } else {
                infoBox("Client with pesel: " + peselToDelete + " already borrowed a book", null, "Mission failed");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteBookByID(int book_id) {
        try {
            ste_1 = connection.createStatement();
            ste_1.executeUpdate("DELETE FROM book WHERE id = '" + book_id + "'");

            System.out.println("Book with id: " + book_id + " DELETED SUCCESSFULLY");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateBookPriceByID(int book_id, double book_price) {
        try {
            ste_1 = connection.createStatement();
            ste_1.executeUpdate("UPDATE book SET price ='" + book_price + "' WHERE id ='" + book_id + "'");

            System.out.println("Book PRICE: " + book_price + " changed in book with ID: " + book_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}




