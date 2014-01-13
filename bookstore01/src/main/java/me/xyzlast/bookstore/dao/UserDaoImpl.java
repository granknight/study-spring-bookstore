package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.constants.UserLevel;
import me.xyzlast.bookstore.entities.User;
import me.xyzlast.bookstore.sql.ResultSetToObjectConverter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykyoon on 12/18/13.
 */
@Repository
public class UserDaoImpl extends AbstractBaseDao<User> implements UserDao {

    public static final String TABLE_NAME = "users";
    public static final String SELECT_QUERY = "select id, name, password, point, level from users";
    public static final String ADD_QUERY = "insert users(name, password, point, level) values(?, ?, ?, ?)";
    public static final String UPDATE_QUERY = "update users set name=?, password=?, point=?, level=? where id=?";

    public UserDaoImpl() {
        super(TABLE_NAME, SELECT_QUERY, ADD_QUERY, UPDATE_QUERY);
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setPoint(rs.getInt("point"));
                user.setLevel(UserLevel.valueOf(rs.getInt("level")));
                return user;
            };
        };
    }

    @Override
    protected Object[] getUpdateObjects(User user) {
        return new Object[]{
                user.getName(), user.getPassword(), user.getPoint(), user.getLevel().getValue(), user.getId()
        };
    }

    @Override
    protected Object[] getAddObjects(User user) {
        return new Object[]{
                user.getName(), user.getPassword(), user.getPoint(), user.getLevel().getValue()
        };
    }
}
