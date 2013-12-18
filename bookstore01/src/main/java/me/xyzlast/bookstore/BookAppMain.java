package me.xyzlast.bookstore;

import java.util.List;

/**
 * Created by ykyoon on 12/18/13.
 */
public class BookAppMain {
    public static void main(String[] args) throws Exception {
        System.out.println("start main app");

        Book book = new Book();
        book.setName("Spring 3.1");
        book.setAuthor("작가");
        book.setPublishDate(new java.util.Date());
        book.setComment("좋은 책입니다.");

        BookApp app = new BookApp();
        app.add(book);

        List<Book> books = app.getAll();
        for(Book b : books) {
            System.out.println(b);
        }
        return;
    }
}
