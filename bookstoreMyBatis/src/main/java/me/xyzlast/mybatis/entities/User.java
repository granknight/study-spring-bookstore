package me.xyzlast.mybatis.entities;

import me.xyzlast.mybatis.constants.UserLevel;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ykyoon on 2/21/14.
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
