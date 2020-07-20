package sample.controllers.modelSection;

public class ClientTableModel {
    String pesel, name, second_name;

    public ClientTableModel(String pesel, String name, String second_name) {
        this.pesel = pesel;
        this.name = name;
        this.second_name = second_name;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }
}
