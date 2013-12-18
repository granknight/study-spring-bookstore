package me.xyzlast.bookstore.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ykyoon on 12/18/13.
 */
public interface ExecuteUpdateQuery {
    PreparedStatement getPreparedStatement(Connection conn) throws SQLException;
}

