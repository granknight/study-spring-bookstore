package me.xyzlast.bookstore.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ykyoon on 12/29/13.
 */
public interface ResultSetToObjectConverter {
    List<Object> convertTo(ResultSet rs) throws SQLException;
}
