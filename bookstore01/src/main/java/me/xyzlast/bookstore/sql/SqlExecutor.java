package me.xyzlast.bookstore.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ykyoon on 12/18/13.
 */
public class SqlExecutor {
    private ConnectionFactory connectionFactory;
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String query, Object... objects) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = initPreparedStatement(conn, query, objects);
        ps.executeUpdate();
        closeObject(ps, conn);
    }

    public List<Object> execute(String query, ResultSetToObjectConverter converter, Object... objects) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = initPreparedStatement(conn, query, objects);
        ResultSet rs = ps.executeQuery();

        List<Object> results = converter.convertTo(rs);
        closeObject(rs, ps, conn);
        return results;
    }

    private PreparedStatement initPreparedStatement(Connection conn, String query, Object... objects) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        int index = 1;
        for(Object obj : objects) {
            ps.setObject(index, obj);
            index++;
        }
        return ps;
    }

    private void closeObject(AutoCloseable... closeables) throws Exception {
        for(AutoCloseable c : closeables) {
            try {
                c.close();
            } catch(Exception ex) {

            }
        }
    }
}
