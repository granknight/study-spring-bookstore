package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.constants.ActionType;
import me.xyzlast.bookstore.entities.History;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by ykyoon on 12/24/13.
 */
public class HistoryDao extends AbstractBaseDao<History> {

    public HistoryDao() {
        super(
                "histories",
                "select id, bookId, userId, actionType, insertDate from histories",
                "insert histories(bookId, userId, actionType, insertDate) values(?, ?, ?, ?)",
                "update histories set bookId = ?, userId = ? actionType = ?, insertDate = ? where id = ?"
        );
    }

    @Override
    protected History convertFromResultSet(ResultSet rs) throws SQLException {
        History history = new History();
        history.setId(rs.getInt("id"));
        history.setBookId(rs.getInt("bookId"));
        history.setUserId(rs.getInt("userId"));
        history.setActionType(ActionType.valueOf(rs.getInt("actionType")));
        Date dt = new Date(rs.getDate("insertDate").getTime());
        history.setInsertTime(dt);
        return history;
    }

    @Override
    protected PreparedStatement setPreparedStatementParametersForUpdate(PreparedStatement ps, History entity) throws SQLException {
        ps.setInt(1, entity.getBookId());
        ps.setInt(2, entity.getUserId());
        ps.setInt(3, entity.getActionType().getValue());
        java.sql.Date date = new java.sql.Date(entity.getInsertTime().getTime());
        ps.setDate(4, date);
        ps.setInt(5, entity.getId());
        return ps;
    }

    @Override
    protected PreparedStatement setPreparedStatementParametersForAdd(PreparedStatement ps, History entity) throws SQLException {
        ps.setInt(1, entity.getBookId());
        ps.setInt(2, entity.getUserId());
        ps.setInt(3, entity.getActionType().getValue());
        java.sql.Date date = new java.sql.Date(entity.getInsertTime().getTime());
        ps.setDate(4, date);
        return ps;
    }
}
