package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.constants.UserLevel;
import me.xyzlast.bookstore.entities.User;
import me.xyzlast.bookstore.sql.ResultSetToObjectConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykyoon on 12/18/13.
 */
public class UserDao extends AbstractBaseDao<User> {

    public static final String TABLE_NAME = "users";
    public static final String SELECT_QUERY = "select id, name, password, point, level from users";
    public static final String ADD_QUERY = "insert users(name, password, point, level) values(?, ?, ?, ?)";
    public static final String UPDATE_QUERY = "update users set name=?, password=?, point=?, level=? where id=?";

    public UserDao() {
        super(TABLE_NAME, SELECT_QUERY, ADD_QUERY, UPDATE_QUERY);
    }

    @Override
    protected ResultSetToObjectConverter getConverter() {
        ResultSetToObjectConverter converter = new ResultSetToObjectConverter() {
            @Override
            public List convertTo(ResultSet rs) throws SQLException {
                List<User> users = new ArrayList<>();
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    user.setPoint(rs.getInt("point"));
                    user.setLevel(UserLevel.valueOf(rs.getInt("level")));
                    users.add(user);
                }
                return users;
            }
        };
        return converter;
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
