package me.xyzlast.mybatis.entities;

import me.xyzlast.mybatis.constants.BookStatus;

import java.sql.Timestamp;

/**
 * Created by ykyoon on 2/21/14.
 */
public class Book extends BaseEntity {
    private String name;
    private String author;
    private Timestamp publishDate;
    private String comment;
    private BookStatus status;
    private Integer rentUserId;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publishDate=" + publishDate +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                ", rentUserId=" + rentUserId +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Integer getRentUser() {
        return rentUserId;
    }

    public void setRentUser(Integer usersByRentUserId) {
        this.rentUserId = usersByRentUserId;
    }
}
