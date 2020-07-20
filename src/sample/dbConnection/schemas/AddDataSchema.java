package sample.dbConnection.schemas;

import sample.dbConnection.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDataSchema {
    DBHandler dbHandler = new DBHandler();
    Connection connection = dbHandler.getDbConnection();
    private PreparedStatement p_1;
    private PreparedStatement p_2;

    /* ADD NEW BOOK TO DB */
    public void addDataOfNewBook(long book_isbn, String book_title, String book_author, int book_type, String book_publisher, int book_pubYear, double book_price) {
        try {
            p_1 = connection.prepareStatement("INSERT INTO book(isbn, title, author, book_type, publisher, publication_year, price) VALUES (?,?,?,?,?,?,?)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            p_1.setLong(1, book_isbn);
            p_1.setString(2, book_title);
            p_1.setString(3, book_author);
            p_1.setInt(4, book_type);
            p_1.setString(5, book_publisher);
            p_1.setInt(6, book_pubYear);
            p_1.setDouble(7, book_price);
            p_1.executeUpdate();

            System.out.println("New Book added successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /* ADD NEW CLIENT TO DB */
    public void addNewClientToDB(String client_pesel, String client_name, String client_sureName, String client_address, String client_mail, String client_phone) {

        try {
            p_1 = connection.prepareStatement("INSERT INTO client(pesel, name, second_name)VALUES (?,?,?)");
            p_2 = connection.prepareStatement("INSERT INTO contact(pesel, address, mail, telephone)VALUES (?,?,?,?)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            p_1.setString(1, client_pesel);
            p_1.setString(2, client_name);
            p_1.setString(3, client_sureName);

            p_2.setString(1, client_pesel);
            p_2.setString(2, client_address);
            p_2.setString(3, client_mail);
            p_2.setString(4, client_phone);

            p_1.executeUpdate();
            p_2.executeUpdate();

            System.out.println("New client sign up successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}