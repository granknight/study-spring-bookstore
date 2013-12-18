package me.xyzlast.bookstore.constants;

/**
 * Created by ykyoon on 12/18/13.
 */
public enum UserLevel {
    NORMAL(0),
    MASTER(1),
    MVP(2);

    private int value;

    public int getValue() {
        return value;
    }

    private UserLevel(int value) {
        this.value = value;
    }

    public static UserLevel valueOf(int value) {
        switch(value) {
            case 0:
                return NORMAL;
            case 1:
                return MASTER;
            case 2:
                return MVP;
            default:
                throw new IllegalArgumentException();
        }
    }

}
