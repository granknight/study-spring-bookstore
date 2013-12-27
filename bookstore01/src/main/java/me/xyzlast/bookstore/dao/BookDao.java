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

    public static final String TABLE_NAME = "books";
    public static final String SELECT_QUERY = "select id, name, author, publishDate, comment, status, rentUserId from books";
    public static final String ADD_QUERY = "insert books(name, author, publishDate, comment, status, rentUserId) values(?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_QUERY = "update books set name=?, author=?, publishDate=?, comment=?, status=?, rentUserId=? where id=?";

    public BookDao() {
        super(TABLE_NAME, SELECT_QUERY, ADD_QUERY, UPDATE_QUERY);
    }

    @Override
    protected Book convertFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setPublishDate(new java.util.Date(rs.getDate("publishDate").getTime()));
        book.setComment(rs.getString("comment"));
        book.setRentUserId((Integer) rs.getObject("rentUserId"));
        book.setStatus(BookStatus.valueOf(rs.getInt("status")));
        return book;
    }

    @Override
    protected PreparedStatement setPreparedStatementParametersForUpdate(PreparedStatement ps, Book entity) throws SQLException {
        PreparedStatement addPs = setPreparedStatementParametersForAdd(ps, entity);
        addPs.setInt(7, entity.getId());
        return ps;
    }

    @Override
    protected PreparedStatement setPreparedStatementParametersForAdd(PreparedStatement ps, Book entity) throws SQLException {
        return initPreparedStatement(ps, entity.getName(), entity.getAuthor(), new java.sql.Date(entity.getPublishDate().getTime()),
                entity.getComment(), entity.getStatus().getValue(), entity.getRentUserId());
    }
}