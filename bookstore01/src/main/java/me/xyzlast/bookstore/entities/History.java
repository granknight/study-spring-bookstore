package me.xyzlast.bookstore.entities;

import me.xyzlast.bookstore.constants.ActionType;

import java.util.Date;

/**
 * Created by ykyoon on 12/18/13.
 */
public class History extends BaseEntity {
    private int userId;
    private int bookId;
    private ActionType actionType;
    private Date insertTime;

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

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}
