package me.xyzlast.bookstore.dao;

import me.xyzlast.bookstore.constants.BookStatus;
import me.xyzlast.bookstore.entities.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ykyoon on 12/18/13.
 */
@Repository
public class BookDaoImpl extends AbstractBaseDao<Book> implements BookDao {

    public static final String TABLE_NAME = "books";
    public static final String SELECT_QUERY = "select id, name, author, publishDate, comment, status, rentUserId from books";
    public static final String ADD_QUERY = "insert books(name, author, publishDate, comment, status, rentUserId) values(?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_QUERY = "update books set name=?, author=?, publishDate=?, comment=?, status=?, rentUserId=? where id=?";

    public BookDaoImpl() {
        super(TABLE_NAME, SELECT_QUERY, ADD_QUERY, UPDATE_QUERY);
    }

    @Override
    protected RowMapper<Book> getRowMapper() {
        return new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
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
        };
    }

    @Override
    protected Object[] getUpdateObjects(Book book) {
        return new Object[]{
                book.getName(), book.getAuthor(), new java.sql.Date(book.getPublishDate().getTime()),
                book.getComment(), book.getStatus().getValue(), book.getRentUserId(), book.getId()
        };
    }

    @Override
    protected Object[] getAddObjects(Book book) {
        return new Object[]{
                book.getName(), book.getAuthor(), new java.sql.Date(book.getPublishDate().getTime()),
                book.getComment(), book.getStatus().getValue(), book.getRentUserId()
        };
    }

    @Override
    public List<Book> listByStatus() {
        return getJdbcTemplate().query(SELECT_QUERY + " Order By status", getRowMapper());
    }
}