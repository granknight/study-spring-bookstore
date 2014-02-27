package me.xyzlast.bh.entities;

import me.xyzlast.bh.constants.UserLevel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ykyoon on 2/21/14.
 */
@Entity
@Table(name = "users", schema = "", catalog = "bookstore2")
public class User extends BaseEntity {
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "point")
    private int point;
    @Basic
    @Enumerated
    @Column(name = "level")
    private UserLevel level;

    @OneToMany(mappedBy = "rentUser")
    private Collection<Book> rentBooks = new ArrayList<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (level != user.level) return false;
        if (point != user.point) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + point;
        result = 31 * result + level.ordinal();
        return result;
    }


    public Collection<Book> getRentBooks() {
        return rentBooks;
    }

    public void setRentBooks(Collection<Book> booksesById) {
        this.rentBooks = booksesById;
    }
}
