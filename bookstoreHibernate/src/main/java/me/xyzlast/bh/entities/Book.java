package me.xyzlast.bh.entities;

import me.xyzlast.bh.constants.BookStatus;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ykyoon on 2/21/14.
 */
@Entity
@Table(name = "books", schema = "", catalog = "bookstore2")
public class Book {
    private int id;
    private String name;
    private String author;
    private Timestamp publishDate;
    private String comment;
    private BookStatus status;
    private User rentUser;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "publishDate")
    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (status != book.status) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (comment != null ? !comment.equals(book.comment) : book.comment != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (publishDate != null ? !publishDate.equals(book.publishDate) : book.publishDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + status.ordinal();
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "rentUserId", referencedColumnName = "id")
    public User getRentUser() {
        return rentUser;
    }

    public void setRentUser(User usersByRentUserId) {
        this.rentUser = usersByRentUserId;
    }
}
