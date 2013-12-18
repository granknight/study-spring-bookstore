package me.xyzlast.bookstore.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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


    public Object execute(ExecuteSelectQuery query) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = query.getPreparedStatement(conn);
        ResultSet rs = ps.executeQuery();
        Object result = query.parseResultSet(rs);
        closeObject(conn, ps, rs);
        return result;
    }

    public void execute(ExecuteUpdateQuery query) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = query.getPreparedStatement(conn);
        ps.executeUpdate();
        closeObject(conn, ps);
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
