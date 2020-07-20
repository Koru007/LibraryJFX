package sample.dbConnection.schemas;

import sample.dbConnection.DBHandler;

import java.sql.*;

public class ChangingDataSchema {
    DBHandler dbHandler = new DBHandler();
    Connection connection = dbHandler.getDbConnection();
    private Statement ste_1;

    public void deleteClientByPesel(String peselToDelete) {
        try {
            ste_1 = connection.createStatement();
            ste_1.executeUpdate("DELETE FROM contact WHERE pesel = '" + peselToDelete + "'");
            ste_1.executeUpdate("DELETE FROM client WHERE pesel = '" + peselToDelete + "'");

            System.out.println("Client with pesel: " + peselToDelete + " DELETED SUCCESSFULLY");
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
}




