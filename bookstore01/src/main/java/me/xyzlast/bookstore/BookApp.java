package me.xyzlast.bookstore;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykyoon on 12/18/13.
 */
public class BookApp {

    private Connection getConnection() throws InstantiationException,
            IllegalAccessException, ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/bookstore";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection (url, "root", "qwer12#$");
        return conn;
    }

    private void closeObject(AutoCloseable... closeables) throws Exception {
        for(AutoCloseable c : closeables) {
            c.close();
        }
    }

    public void add(Book book) throws Exception {
        Connection conn = getConnection();

        PreparedStatement st = conn.prepareStatement("insert books(name, author, publishDate, comment, id) values(?, ?, ?, ?, ?)");
        st.setString(1, book.getName());
        st.setString(2, book.getAuthor());
        java.sql.Date sqlDate = new java.sql.Date(book.getPublishDate().getTime());
        st.setDate(3, sqlDate);
        st.setString(4, book.getComment());
        st.setInt(5, book.getId());
        st.execute();

        closeObject(st, conn);
    }

    public Book get(int id) throws Exception {
        Connection conn = getConnection();

        PreparedStatement st = conn.prepareStatement("select id, name, author, publishDate, comment from books where id=?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        rs.next();

        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        java.util.Date date = new java.util.Date(rs.getDate("publishDate").getTime());
        book.setPublishDate(date);
        book.setComment(rs.getString("comment"));

        closeObject(rs, st, conn);

        return book;
    }

    public List<Book> search(String name) throws Exception {
        Connection conn = getConnection();

        PreparedStatement ps = conn.prepareStatement("select id, name, author, publishDate, comment from books where name like ?");
        ps.setString(1, "%" + name + "%");
        ResultSet rs = ps.executeQuery();

        List<Book> books = new ArrayList<>();
        while(rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            java.util.Date date = new java.util.Date(rs.getDate("publishDate").getTime());
            book.setPublishDate(date);
            book.setComment(rs.getString("comment"));
            books.add(book);
        }

        closeObject(rs, ps, conn);

        return books;
    }

    public int countAll() throws Exception {
        Connection conn = getConnection();

        PreparedStatement ps = conn.prepareStatement("select count(*) from books");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        closeObject(rs, ps, conn);

        return count;
    }

    public Book update(Book book) throws Exception {
        Connection conn = getConnection();

        PreparedStatement ps = conn.prepareStatement("update books set name=?, author=?, publishDate=?, comment=? where id=?");
        ps.setString(1, book.getName());
        ps.setString(2, book.getAuthor());
        ps.setDate(3, new Date(book.getPublishDate().getTime()));
        ps.setString(4, book.getComment());
        ps.setInt(5, book.getId());

        ps.executeUpdate();

        closeObject(ps, conn);

        return book;
    }

    public List<Book> getAll() throws Exception {
        Connection conn = getConnection();

        PreparedStatement ps = conn.prepareStatement("select id, name, author, publishDate, comment from books");
        ResultSet rs = ps.executeQuery();

        List<Book> books = new ArrayList<>();
        while(rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            java.util.Date date = new java.util.Date(rs.getDate("publishDate").getTime());
            book.setPublishDate(date);
            book.setComment(rs.getString("comment"));
            books.add(book);
        }
        closeObject(rs, ps, conn);
        return books;
    }

    public void deleteAll() throws Exception {
        Connection conn = getConnection();

        PreparedStatement ps = conn.prepareStatement("delete from books");
        ps.executeUpdate();

        closeObject(ps, conn);
    }
}