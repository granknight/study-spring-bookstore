package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.constants.BookStatus;
import me.xyzlast.bookstore.entities.Book;
import me.xyzlast.bookstore.sql.ConnectionFactory;
import me.xyzlast.bookstore.sql.ExecuteSelectQuery;
import me.xyzlast.bookstore.sql.ExecuteUpdateQuery;
import me.xyzlast.bookstore.sql.SqlExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykyoon on 12/18/13.
 */
public class BookDao {
    private SqlExecutor executor;

    public SqlExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(SqlExecutor executor) {
        this.executor = executor;
    }

    private Book convertBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        java.util.Date date = new java.util.Date(rs.getDate("publishDate").getTime());
        book.setPublishDate(date);
        book.setComment(rs.getString("comment"));
        book.setRentUserId((Integer) rs.getObject("rentUserId"));
        book.setStatus(BookStatus.valueOf(rs.getInt("status")));

        return book;
    }


    public void add(final Book book) throws Exception {
        ExecuteUpdateQuery query = new ExecuteUpdateQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement st = conn.prepareStatement("insert books(name, author, publishDate, comment, status, rentUserId, id) values(?, ?, ?, ?, ?, ?, ?)");
                st.setString(1, book.getName());
                st.setString(2, book.getAuthor());
                java.sql.Date sqlDate = new java.sql.Date(book.getPublishDate().getTime());
                st.setDate(3, sqlDate);
                st.setString(4, book.getComment());
                st.setInt(5, book.getStatus().getValue());
                st.setObject(6, book.getRentUserId());
                st.setInt(7, book.getId());
                return st;
            }
        };
        executor.execute(query);
    }

    public Book get(final int id) throws Exception {
        ExecuteSelectQuery query = new ExecuteSelectQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement st = conn.prepareStatement("select id, name, author, publishDate, comment, status, rentUserId from books where id=?");
                st.setInt(1, id);
                return st;
            }
            @Override
            public Object parseResultSet(ResultSet rs) throws SQLException {
                rs.next();
                return convertBook(rs);
            }
        };
        return (Book) executor.execute(query);
    }

    public List<Book> search(final String name) throws Exception {
        ExecuteSelectQuery query = new ExecuteSelectQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement("select id, name, author, publishDate, comment, status, rentUserId from books where name like ?");
                ps.setString(1, "%" + name + "%");
                return ps;
            }

            @Override
            public Object parseResultSet(ResultSet rs) throws SQLException {
                List<Book> books = new ArrayList<>();
                while(rs.next()) {
                    Book book = convertBook(rs);
                    books.add(book);
                }
                return books;
            }
        };
        return (List<Book>) executor.execute(query);
    }

    public int countAll() throws Exception {
        ExecuteSelectQuery query = new ExecuteSelectQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                return conn.prepareStatement("select count(*) from books");
            }
            @Override
            public Object parseResultSet(ResultSet rs) throws SQLException {
                rs.next();
                return rs.getInt(1);
            }
        };
        return (int) executor.execute(query);
    }

    public Book update(final Book book) throws Exception {
        ExecuteUpdateQuery query = new ExecuteUpdateQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement("update books set name=?, author=?, publishDate=?, comment=?, status=?, rentUserId=? where id=?");
                ps.setString(1, book.getName());
                ps.setString(2, book.getAuthor());
                ps.setDate(3, new Date(book.getPublishDate().getTime()));
                ps.setString(4, book.getComment());
                ps.setInt(5, book.getStatus().getValue());
                ps.setObject(6, book.getRentUserId());
                ps.setInt(7, book.getId());
                return ps;
            }
        };
        executor.execute(query);
        return book;
    }

    public List<Book> getAll() throws Exception {
        ExecuteSelectQuery query = new ExecuteSelectQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                return conn.prepareStatement("select id, name, author, publishDate, comment, status, rentUserId from books");
            }

            @Override
            public Object parseResultSet(ResultSet rs) throws SQLException {
                List<Book> books = new ArrayList<>();
                while(rs.next()) {
                    Book book = convertBook(rs);
                    books.add(book);
                }
                return books;
            }
        };
        return (List<Book>) executor.execute(query);
    }

    public void deleteAll() throws Exception {
        ExecuteUpdateQuery query = new ExecuteUpdateQuery() {
            @Override
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                return conn.prepareStatement("delete from books");
            }
        };
        executor.execute(query);
    }
}