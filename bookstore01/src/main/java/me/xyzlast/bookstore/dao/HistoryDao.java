package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.constants.ActionType;
import me.xyzlast.bookstore.entities.History;
import me.xyzlast.bookstore.sql.ResultSetToObjectConverter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ykyoon on 12/24/13.
 */
@Repository
public class HistoryDao extends AbstractBaseDao<History> {

    public static final String TABLE_NAME = "histories";
    public static final String SELECT_QUERY = "select id, bookId, userId, actionType, insertDate from histories";
    public static final String ADD_QUERY = "insert histories(bookId, userId, actionType, insertDate) values(?, ?, ?, ?)";
    public static final String UPDATE_QUERY = "update histories set bookId = ?, userId = ? actionType = ?, insertDate = ? where id = ?";

    public HistoryDao() {
        super(TABLE_NAME, SELECT_QUERY, ADD_QUERY, UPDATE_QUERY);
    }

    @Override
    protected RowMapper<History> getRowMapper() {
        return new RowMapper<History>() {
            @Override
            public History mapRow(ResultSet rs, int rowNum) throws SQLException {
                History history = new History();
                history.setId(rs.getInt("id"));
                history.setBookId(rs.getInt("bookId"));
                history.setUserId(rs.getInt("userId"));
                history.setActionType(ActionType.valueOf(rs.getInt("actionType")));
                history.setInsertTime(new Date(rs.getDate("insertDate").getTime()));
                return history;
            }
        };
    }

    @Override
    protected Object[] getUpdateObjects(History history) {
        return new Object[]{
                history.getBookId(),
                history.getUserId(),
                history.getActionType().getValue(),
                new java.sql.Date(history.getInsertTime().getTime()),
                history.getId()
        };
    }

    @Override
    protected Object[] getAddObjects(History history) {
        return new Object[]{
                history.getBookId(),
                history.getUserId(),
                history.getActionType().getValue(),
                new java.sql.Date(history.getInsertTime().getTime())
        };
    }
}
