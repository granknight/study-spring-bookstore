package me.xyzlast.bookstore.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ykyoon on 12/18/13.
 */
public class ConnectionFactory {

    private String driverName;
    private String url;
    private String username;
    private String password;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() throws ClassNotFoundException,
            IllegalAccessException, InstantiationException, SQLException {
        Class.forName(driverName).newInstance();
        return DriverManager.getConnection(url, username, password);
    }
}
