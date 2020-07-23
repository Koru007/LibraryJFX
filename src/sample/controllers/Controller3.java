package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.controllers.modelSection.BookTableModel;
import sample.controllers.modelSection.ClientTableModel;
import sample.controllers.modelSection.OrderTableModel;
import sample.dbConnection.schemas.AddDataSchema;
import sample.dbConnection.schemas.ChangingDataSchema;
import sample.dbConnection.schemas.GetDataSchema;
import sample.enumType.BookType;
import sample.enumType.ShippingStatus;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller3 implements Initializable {
    @FXML
    private AnchorPane libraryPage;
    @FXML
    private GridPane pnClients;
    @FXML
    private GridPane pnBooks;
    @FXML
    private GridPane pnOrders;
    @FXML
    private Pane pnlStatus;
    @FXML
    private Button btnClients;
    @FXML
    private Button btnBooks;
    @FXML
    private Button btnOrders;
    @FXML
    private Label lblStatusMin;
    @FXML
    private Label lblStatus;
    @FXML
    private TextField title_hold;
    @FXML
    private TextField author_hold;
    @FXML
    private TextField publisher_hold;
    @FXML
    private TextField isbn_hold;
    @FXML
    private TextField publicationD_hold;
    @FXML
    private TextField price_hold;
    @FXML
    private ComboBox<BookType> bookType;
    //  Order__Section
    @FXML
    private ComboBox<ShippingStatus> orderStat;
    @FXML
    private TextField orderPesel;
    @FXML
    private TextField orderBookID;
    @FXML
    private TextField orderID;

    //   Delete section
    @FXML
    private TextField peselToDelete_field;
    @FXML
    private TextField bookID_field;
    //    Book Table
    @FXML
    private TableView<BookTableModel> bookTable;
    @FXML
    private TableColumn<BookTableModel, String> col_id;
    @FXML
    private TableColumn<BookTableModel, String> col_title;
    @FXML
    private TableColumn<BookTableModel, String> col_price;
    //    Client Table
    @FXML
    private TableView<ClientTableModel> clientTable;
    @FXML
    private TableColumn<?, ?> col_cli_pesel;
    @FXML
    private TableColumn<?, ?> col_cli_name;
    @FXML
    private TableColumn<?, ?> col_cli_secNam;
    // Order Table
    @FXML
    private TableView<OrderTableModel> orderTable;
    @FXML
    private TableColumn<?, ?> col_ord_id;
    @FXML
    private TableColumn<?, ?> col_ord_pesel;
    @FXML
    private TableColumn<?, ?> col_ord_book;
    @FXML
    private TableColumn<?, ?> col_ord_stat;


    @FXML
    void changeStatus(ActionEvent event) {
        int order_id = Integer.parseInt(orderID.getText());
        int order_status = orderStat.getValue().getShippingStatValue();

        ChangingDataSchema changingDataSchema = new ChangingDataSchema();
        changingDataSchema.updateOrderStatus(order_id, order_status);

        orderID.clear();

        GetDataSchema getData = new GetDataSchema();
        orderTable.setItems(getData.seeAllOrders());
    }

    @FXML
    void addNewOrder(ActionEvent event) {
        String pesel = orderPesel.getText();
        int book_id = Integer.parseInt(orderBookID.getText());
        int order_status = orderStat.getValue().getShippingStatValue();


        AddDataSchema addDataSchema = new AddDataSchema();
        addDataSchema.addNewOrderToDB(pesel, book_id, order_status);

        orderPesel.clear();
        orderBookID.clear();

        GetDataSchema getData = new GetDataSchema();
        orderTable.setItems(getData.seeAllOrders());
    }


    @FXML
    void addNewBook(ActionEvent event) {
        long isbn = Long.parseLong(isbn_hold.getText());
        String title = title_hold.getText();
        String author = author_hold.getText();
        int bookT = bookType.getValue().getBookValue();
        String publisher = publisher_hold.getText();
        int pubYear = Integer.parseInt(publicationD_hold.getText());
        double price = Double.parseDouble(price_hold.getText());

        System.out.println(title + "\n" + author + "\n" + pubYear + "\nbookType: " + bookT + "\n" + isbn + "\n" + price + "\n" + publisher + "\n");

        try {
            AddDataSchema addDataSchema = new AddDataSchema();
            addDataSchema.addDataOfNewBook(isbn, title, author, bookT, publisher, pubYear, price);
            isbn_hold.clear();
            title_hold.clear();
            author_hold.clear();
            bookType.getSelectionModel().clearSelection();
            publisher_hold.clear();
            publicationD_hold.clear();
            price_hold.clear();
        } catch (Exception e) {
            throw e;
        }
        GetDataSchema getData = new GetDataSchema();
        bookTable.setItems(getData.seeAllBooks());
    }

    @FXML
    void changePrice(ActionEvent event) {
        GetDataSchema getData = new GetDataSchema();

        int bookId = Integer.parseInt(bookID_field.getText());
        double pirice = Double.parseDouble(price_hold.getText());

        ChangingDataSchema changingDataSchema = new ChangingDataSchema();
        changingDataSchema.updateBookPriceByID(bookId, pirice);

        bookID_field.clear();
        price_hold.clear();

        bookTable.setItems(getData.seeAllBooks());
    }

    @FXML
    void deleteBook(ActionEvent event) {
        if (!bookID_field.getText().equals("")) {
            int id = Integer.parseInt(bookID_field.getText());
            ChangingDataSchema changingDataSchema = new ChangingDataSchema();
            changingDataSchema.deleteBookByID(id);

            bookID_field.clear();

            GetDataSchema getData = new GetDataSchema();
            bookTable.setItems(getData.seeAllBooks());
        }else {
            infoBox("Pleas enter Book ID", null, "Empty field");
        }
    }

    @FXML
    void deleteClient(ActionEvent event) {
        String pesel = peselToDelete_field.getText();
        if (!pesel.equals("")) {
            ChangingDataSchema changingDataSchema = new ChangingDataSchema();
            changingDataSchema.deleteClientByPesel(pesel);

            peselToDelete_field.clear();

            GetDataSchema getData = new GetDataSchema();
            clientTable.setItems(getData.seeAllClients());
        } else {
            infoBox("Pleas enter pesel", null, "Empty field");
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnClients) {
            lblStatusMin.setText("/home/clients");
            lblStatus.setText("Clients");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(0, 33, 99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnClients.toFront();
        } else if (event.getSource() == btnBooks) {

            lblStatusMin.setText("/home/books");
            lblStatus.setText("Books");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(21, 79, 141), CornerRadii.EMPTY, Insets.EMPTY)));
            pnBooks.toFront();


        } else if (event.getSource() == btnOrders) {
            lblStatusMin.setText("/home/orders");
            lblStatus.setText("Orders");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 43, 99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnOrders.toFront();
        }
    }

    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/logInPage.fxml"));
        libraryPage.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GetDataSchema getData = new GetDataSchema();
//SET ENUM TO COMBOBOX
        orderStat.getItems().addAll(ShippingStatus.values());
        orderStat.getSelectionModel().selectFirst();
        bookType.getItems().addAll(BookType.values());

//ORDER LIST ->>>>
        col_ord_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ord_book.setCellValueFactory(new PropertyValueFactory<>("book"));
        col_ord_pesel.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        col_ord_stat.setCellValueFactory(new PropertyValueFactory<>("status"));

        orderTable.setItems(getData.seeAllOrders());
//BOOK LIST ->>>>
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        bookTable.setItems(getData.seeAllBooks());
//CLIENT LIST ->>>>
        col_cli_pesel.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        col_cli_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_cli_secNam.setCellValueFactory(new PropertyValueFactory<>("second_name"));

        clientTable.setItems(getData.seeAllClients());
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}

