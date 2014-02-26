package me.xyzlast.bh.services;

import me.xyzlast.bh.entities.History;
import me.xyzlast.bh.entities.User;

import java.util.List;

/**
 * Created by ykyoon on 1/13/14.
 */
public interface UserService {
    boolean rent(int userId, int bookId);
    boolean returnBook(int userId, int bookId);
    List<User> listup();
    List<History> getHistories(int userId);
    void setUserLevelService(UserLevelService userLevelService);
}
