package sample.dbConnection.schemas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.controllers.modelSection.BookTableModel;
import sample.controllers.modelSection.ClientTableModel;
import sample.controllers.modelSection.OrderTableModel;
import sample.dbConnection.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetDataSchema {
    ObservableList<BookTableModel> bookList = FXCollections.observableArrayList();
    ObservableList<ClientTableModel> clientList = FXCollections.observableArrayList();
    ObservableList<OrderTableModel> orderList = FXCollections.observableArrayList();

    DBHandler dbHandler = new DBHandler();
    Connection con = dbHandler.getDbConnection();

    //ORDER
    public ObservableList<OrderTableModel> seeAllOrders() {
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT o.id, o.pesel, b.title, os.name FROM book_order o JOIN book b ON o.book_id = b.id JOIN sl_order_status os ON os.id = o.status ORDER BY o.id;");


            while (rs.next()) {
                orderList.add(new OrderTableModel(
                        rs.getString("id"),
                        rs.getString("pesel"),
                        rs.getString("title"),
                        rs.getString("name")));
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    //BOOK
    public ObservableList<BookTableModel> seeAllBooks() {
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from book");
            while (rs.next()) {
                bookList.add(new BookTableModel(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("price")));
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookList;
    }

    //CLIENTS
    public ObservableList<ClientTableModel> seeAllClients() {
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from client");
            while (rs.next()) {
                clientList.add(new ClientTableModel(
                        rs.getString("pesel"),
                        rs.getString("name"),
                        rs.getString("second_name")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clientList;
    }

}
