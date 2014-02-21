package me.xyzlast.bh.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ykyoon on 2/21/14.
 */
@Entity
@Table(name = "histories", schema = "", catalog = "bookstore2")
public class History {
    private int id;
    private int userId;
    private int bookId;
    private int actionType;
    private Timestamp insertDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "bookId")
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "actionType")
    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    @Basic
    @Column(name = "insertDate")
    public Timestamp getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Timestamp insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        History history = (History) o;

        if (actionType != history.actionType) return false;
        if (bookId != history.bookId) return false;
        if (id != history.id) return false;
        if (userId != history.userId) return false;
        if (insertDate != null ? !insertDate.equals(history.insertDate) : history.insertDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + bookId;
        result = 31 * result + actionType;
        result = 31 * result + (insertDate != null ? insertDate.hashCode() : 0);
        return result;
    }
}
