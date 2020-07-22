package sample.controllers.modelSection;

public class OrderTableModel {
    String id, pesel, book, status;

    public OrderTableModel(String book) {
        this.book = book;
    }

    public OrderTableModel(String id, String pesel, String book, String status) {
        this.id = id;
        this.pesel = pesel;
        this.book = book;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
