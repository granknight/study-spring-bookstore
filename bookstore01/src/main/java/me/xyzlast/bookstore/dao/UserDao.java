package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.constants.UserLevel;
import me.xyzlast.bookstore.entities.User;
import me.xyzlast.bookstore.sql.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ykyoon on 12/18/13.
 */
public class UserDao extends AbstractBaseDao<User> {

    public UserDao() {
        super("users",
                "select id, name, password, point, level from users",
                "insert users(name, password, point, level) values(?, ?, ?, ?)",
                "update users set name=?, password=?, point=?, level=? where id=?");
    }

    @Override
    protected User convertFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setPoint(rs.getInt("point"));
        user.setLevel(UserLevel.valueOf(rs.getInt("level")));
        return user;
    }

    @Override
    protected PreparedStatement setPreparedStatementParametersForUpdate(PreparedStatement ps, User entity) throws SQLException {
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getPassword());
        ps.setInt(3, entity.getPoint());
        ps.setInt(4, entity.getLevel().getValue());
        ps.setInt(5, entity.getId());
        return ps;
    }

    @Override
    protected PreparedStatement setPreparedStatementParametersForAdd(PreparedStatement ps, User entity) throws SQLException {
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getPassword());
        ps.setInt(3, entity.getPoint());
        ps.setInt(4, entity.getLevel().getValue());
        return ps;
    }
}
