package me.xyzlast.bookstore.entities;

import me.xyzlast.bookstore.constants.UserLevel;

/**
 * Created by ykyoon on 12/18/13.
 */
public class User extends BaseEntity {
    private String name;
    private String password;
    private int point;
    private UserLevel level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }
}
