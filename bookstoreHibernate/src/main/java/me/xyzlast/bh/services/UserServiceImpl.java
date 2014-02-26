package me.xyzlast.bh.services;

import me.xyzlast.bh.constants.ActionType;
import me.xyzlast.bh.constants.BookStatus;
import me.xyzlast.bh.dao.BookDao;
import me.xyzlast.bh.dao.HistoryDao;
import me.xyzlast.bh.dao.UserDao;
import me.xyzlast.bh.entities.Book;
import me.xyzlast.bh.entities.History;
import me.xyzlast.bh.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ykyoon on 2/25/14.
 */
public class UserServiceImpl implements UserService {

    private BookDao bookDao;
    private UserDao userDao;
    private HistoryDao historyDao;

    public UserServiceImpl() {

    }

    public UserServiceImpl(BookDao bookDao, UserDao userDao, HistoryDao historyDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
        this.historyDao = historyDao;
    }

    private UserLevelService userLevelService;

    @Override
    public boolean rent(int userId, int bookId) {
        User user = userDao.getById(userId);
        Book book = bookDao.getById(bookId);

        if(book.getRentUser() != null) {
            throw new IllegalArgumentException("It's already rent book");
        }
        user.setPoint(user.getPoint() + 1);
        user.setLevel(userLevelService.getUserLevel(user.getPoint()));
        book.setRentUser(user);
        book.setStatus(BookStatus.RENTNOW);

        History history = new History();
        history.setBook(book);
        history.setUser(user);
        history.setActionType(ActionType.RENT);

        historyDao.add(history);
        bookDao.update(book);
        userDao.update(user);
        return true;
    }

    @Override
    public boolean returnBook(int userId, int bookId) {
        return false;
    }

    @Override
    public List<User> listup() {
        return null;
    }

    @Override
    public List<History> getHistories(int userId) {
        return null;
    }

    @Override
    public void setUserLevelService(UserLevelService userLevelService) {
        this.userLevelService = userLevelService;
    }
}
