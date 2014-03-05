package me.xyzlast.mybatis.entities;

import me.xyzlast.mybatis.constants.ActionType;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by ykyoon on 2/21/14.
 */
public class History extends BaseEntity {
    private int userId;
    private int bookId;
    private ActionType actionType;
    private Timestamp insertDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Timestamp getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Timestamp insertDate) {
        this.insertDate = insertDate;
    }
}
