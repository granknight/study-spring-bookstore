package me.xyzlast.bookstore.constants;

/**
 * Created by ykyoon on 12/18/13.
 */
public enum ActionType {
    Rent(0),
    Return(1),
    Missing(2);

    private int value;

    private ActionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ActionType valueOf(int value) {
        switch(value) {
            case 0:
                return Rent;
            case 1:
                return Return;
            case 2:
                return Missing;
            default:
                throw new IllegalArgumentException();
        }
    }

}
