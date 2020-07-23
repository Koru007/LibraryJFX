package sample.enumType;

public enum BookType {
    SENSATION(1),
    CRIMINAL(2),
    FANTASY(3),
    THRILLER(4),
    HORROR(5),
    NOVEL_OF_MANNERS(6),
    GUIDE(7),
    BIOGRAPHY(8),
    HISTORICAL(9),
    TRAVEL(10),
    ROMANS(11),
    POPULAR_SCIENCE(12),
    YOUTH(13),
    CHILDREN(14),
    REPORTAGE(15),
    HANDBOOK(16);

    private final int value;
    private BookType(int value) {
        this.value = value;
    }


    public int getBookValue() {
        return value;
    }
}
