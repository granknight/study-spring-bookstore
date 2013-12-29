package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.sql.ResultSetToObjectConverter;
import me.xyzlast.bookstore.sql.SqlExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykyoon on 12/25/13.
 */
public abstract class AbstractBaseDao<T> implements EntityDao<T> {

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

    protected abstract ResultSetToObjectConverter getConverter();
    protected abstract Object[] getUpdateObjects(T t);
    protected abstract Object[] getAddObjects(T t);

    @Override
    public List<T> getAll() throws Exception {
        return (List<T>) sqlExecutor.execute(getAllQuery, getConverter());
    }

    @Override
    public void deleteAll() throws Exception {
        sqlExecutor.execute("DELETE FROM " + tableName);
    }

    @Override
    public T getById(final int id) throws Exception {
        return (T) sqlExecutor.execute(getAllQuery + " WHERE id = ?", getConverter(), id).get(0);
    }

    @Override
    public void add(final T entity) throws Exception {
        sqlExecutor.execute(addQuery, getAddObjects(entity));
    }

    @Override
    public void update(final T entity) throws Exception {
        sqlExecutor.execute(updateQuery, getUpdateObjects(entity));
    }

    @Override
    public int countAll() throws Exception {
        return (int) sqlExecutor.execute("SELECT count(*) from " + tableName, new ResultSetToObjectConverter() {
            @Override
            public List convertTo(ResultSet rs) throws SQLException {
                List<Integer> output = new ArrayList<>();
                rs.next();
                output.add(rs.getInt(1));
                return output;
            }
        }).get(0);
    }
}
