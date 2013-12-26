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

    public static final String TABLE_NAME = "histories";
    public static final String SELECT_QUERY = "select id, bookId, userId, actionType, insertDate from histories";
    public static final String ADD_QUERY = "insert histories(bookId, userId, actionType, insertDate) values(?, ?, ?, ?)";
    public static final String UPDATE_QUERY = "update histories set bookId = ?, userId = ? actionType = ?, insertDate = ? where id = ?";

    public HistoryDao() {
        super(TABLE_NAME, SELECT_QUERY, ADD_QUERY, UPDATE_QUERY);
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
        PreparedStatement addPs = setPreparedStatementParametersForAdd(ps, entity);
        addPs.setInt(5, entity.getId());
        return addPs;
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
