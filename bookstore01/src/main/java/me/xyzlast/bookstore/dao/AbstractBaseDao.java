package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.sql.ResultSetToObjectConverter;
import me.xyzlast.bookstore.sql.SqlExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykyoon on 12/25/13.
 */
public abstract class AbstractBaseDao<T> implements EntityDao<T> {

    private BeanPropertyRowMapper<T> beanPropertyRowMapper;

    protected AbstractBaseDao(String tableName, String getAllQuery, String addQuery, String updateQuery) {
        this.tableName = tableName;
        this.getAllQuery = getAllQuery;
        this.addQuery = addQuery;
        this.updateQuery = updateQuery;

        beanPropertyRowMapper = new BeanPropertyRowMapper<>();
    }

    private final String getAllQuery;
    private final String updateQuery;
    private final String addQuery;
    private final String tableName;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    protected abstract RowMapper<T> getRowMapper();
    protected abstract Object[] getUpdateObjects(T t);
    protected abstract Object[] getAddObjects(T t);

    @Override
    public List<T> getAll() {
        return jdbcTemplate.query(getAllQuery, getRowMapper());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.execute("DELETE FROM " + tableName);
    }

    @Override
    public T getById(final int id) {
        return jdbcTemplate.queryForObject(getAllQuery + " WHERE id = ?", new Object[] {id}, getRowMapper());
    }

    @Override
    public void add(final T entity) {
        jdbcTemplate.update(addQuery, getAddObjects(entity));
    }

    @Override
    public void update(final T entity) {
        jdbcTemplate.update(updateQuery, getUpdateObjects(entity));
    }

    @Override
    public int countAll() {
        String query = "SELECT count(*) from " + tableName;
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
