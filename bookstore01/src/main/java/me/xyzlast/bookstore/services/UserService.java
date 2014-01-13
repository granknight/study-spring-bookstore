package me.xyzlast.bookstore.services;

import me.xyzlast.bookstore.entities.Book;
import me.xyzlast.bookstore.entities.History;
import me.xyzlast.bookstore.entities.User;

import java.util.List;

/**
 * Created by ykyoon on 1/13/14.
 */
public interface UserService {
    boolean rent(int userId, int bookId);
    boolean returnBook(int userId, int bookId);
    public List<User> listup();
    public List<History> getHistories(int userId);
}
