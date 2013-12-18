package me.xyzlast.bookstore;

/**
 * Created by ykyoon on 12/18/13.
 */
import java.util.Date;

public class Book {
    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", author=" + author
                + ", publishDate=" + publishDate + ", comment=" + comment + "]";
    }
    private int id;
    private String name;
    private String author;
    private Date publishDate;
    private String comment;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
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
    public Date getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}

