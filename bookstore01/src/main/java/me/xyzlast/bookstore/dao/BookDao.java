package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.constants.BookStatus;
import me.xyzlast.bookstore.entities.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ykyoon on 12/18/13.
 */
public class BookDao extends AbstractBaseDao<Book> {
    public BookDao() {
        super("books",
                "select id, name, author, publishDate, comment, status, rentUserId from books",
                "insert books(name, author, publishDate, comment, status, rentUserId, id) values(?, ?, ?, ?, ?, ?, ?)",
                "update books set name=?, author=?, publishDate=?, comment=?, status=?, rentUserId=? where id=?"
                );
    }

    @Override
    protected Book convertFromResultSet(ResultSet rs) throws SQLException {
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

    @Override
    protected PreparedStatement setPreparedStatementParametersForUpdate(PreparedStatement ps, Book entity) throws SQLException {
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getAuthor());
        java.sql.Date sqlDate = new java.sql.Date(entity.getPublishDate().getTime());
        ps.setDate(3, sqlDate);
        ps.setString(4, entity.getComment());
        ps.setInt(5, entity.getStatus().getValue());
        ps.setObject(6, entity.getRentUserId());
        ps.setInt(7, entity.getId());
        return ps;
    }

    @Override
    protected PreparedStatement setPreparedStatementParametersForAdd(PreparedStatement ps, Book entity) throws SQLException {
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getAuthor());
        java.sql.Date sqlDate = new java.sql.Date(entity.getPublishDate().getTime());
        ps.setDate(3, sqlDate);
        ps.setString(4, entity.getComment());
        ps.setInt(5, entity.getStatus().getValue());
        ps.setObject(6, entity.getRentUserId());
        ps.setInt(7, entity.getId());
        return ps;
    }
}