package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.entities.BaseEntity;
import me.xyzlast.bookstore.sql.ExecuteSelectQuery;
import me.xyzlast.bookstore.sql.ExecuteUpdateQuery;
import me.xyzlast.bookstore.sql.SqlExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykyoon on 12/25/13.
 */
public abstract class AbstractBaseDao<T extends BaseEntity> implements EntityDao<T> {

    protected AbstractBaseDao(String tableName, String getAllQuery, String addQuery, String updateQuery) {
        this.tableName = tableName;
        this.getAllQuery = getAllQuery;
        this.addQuery = addQuery;
        this.updateQuery = updateQuery;
    }

    private final String getAllQuery;
    private final String updateQuery;
    private final String addQuery;
    private final String tableName;
    private SqlExecutor sqlExecutor;

    public SqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }
    public void setSqlExecutor(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }
    protected abstract T convertFromResultSet(ResultSet rs) throws SQLException;
    protected abstract PreparedStatement setPreparedStatementParametersForUpdate(PreparedStatement ps, T entity) throws SQLException;
    protected abstract PreparedStatement setPreparedStatementParametersForAdd(PreparedStatement ps, T entity) throws SQLException;

    @Override
    public List<T> getAll() throws Exception {
        return (List<T>) sqlExecutor.execute(new ExecuteSelectQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                return conn.prepareStatement(getAllQuery);
            }

            @Override
            public Object parseResultSet(ResultSet rs) throws SQLException {
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(convertFromResultSet(rs));
                }
                return list;
            }
        });
    }

    @Override
    public void deleteAll() throws Exception {
        sqlExecutor.execute(new ExecuteUpdateQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                return conn.prepareStatement("DELETE FROM " + tableName);
            }
        });
    }

    @Override
    public T getById(final int id) throws Exception {
        return (T) sqlExecutor.execute(new ExecuteSelectQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(getAllQuery + " where id = ?");
                ps.setInt(1, id);
                return ps;
            }

            @Override
            public Object parseResultSet(ResultSet rs) throws SQLException {
                rs.next();
                return convertFromResultSet(rs);
            }
        });
    }

    @Override
    public void add(final T entity) throws Exception {
        sqlExecutor.execute(new ExecuteUpdateQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                return setPreparedStatementParametersForAdd(conn.prepareStatement(addQuery), entity);
            }
        });
    }

    @Override
    public void update(final T entity) throws Exception {
        sqlExecutor.execute(new ExecuteUpdateQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                return setPreparedStatementParametersForUpdate(conn.prepareStatement(updateQuery), entity);
            }
        });
    }

    @Override
    public int countAll() throws Exception {
        return (int) sqlExecutor.execute(new ExecuteSelectQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                String query = "SELECT count(*) from " + tableName;
                return conn.prepareStatement(query);
            }

            @Override
            public Object parseResultSet(ResultSet rs) throws SQLException {
                rs.next();
                return rs.getInt(1);
            }
        });
    }

    protected PreparedStatement initPreparedStatement(PreparedStatement ps, Object... objects) throws SQLException {
        int index = 1;
        for(Object obj : objects) {
            ps.setObject(index, obj);
            index++;
        }
        return ps;
    }
}
