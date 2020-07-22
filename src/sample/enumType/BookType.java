package sample.enumType;

import java.util.HashMap;
import java.util.Map;

public enum BookType {
    SENSACJA(1),
    KRYMINAŁ(2),
    FANTASTYKA(3),
    THRILLER(4),
    HORROR(5),
    OBYCZAJOWA(6),
    PORADNIK(7),
    BIOGRAFIA(8),
    HISTORYCZNA(9),
    PODRÓŻE(10),
    ROMANS(11),
    POPULARNONAUKOWA(12),
    MŁODZIEŻOWA(13),
    DZIECIĘCA(14),
    REPORTAŻ(15),
    PODRĘCZNIK(16);

    private final int value;
    private BookType(int value) {
        this.value = value;
    }


    public int getBookValue() {
        return value;
    }
}
