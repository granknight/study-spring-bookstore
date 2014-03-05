package me.xyzlast.mybatis.services;

import me.xyzlast.mybatis.constants.ActionType;
import me.xyzlast.mybatis.constants.BookStatus;
import me.xyzlast.mybatis.dao.BookDao;
import me.xyzlast.mybatis.dao.HistoryDao;
import me.xyzlast.mybatis.dao.UserDao;
import me.xyzlast.mybatis.entities.Book;
import me.xyzlast.mybatis.entities.History;
import me.xyzlast.mybatis.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ykyoon on 2/25/14.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private HistoryDao historyDao;

    public UserServiceImpl() {

    }

    public UserServiceImpl(BookDao bookDao, UserDao userDao, HistoryDao historyDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
        this.historyDao = historyDao;
    }

    @Autowired
    private UserLevelService userLevelService;

    @Override
    @Transactional
    public boolean rent(int userId, int bookId) {
        User user = userDao.getById(userId);
        Book book = bookDao.getById(bookId);

        if(book.getRentUserId() != null) {
            throw new IllegalArgumentException("It's already rent book");
        }
        user.setPoint(user.getPoint() + 1);
        user.setLevel(userLevelService.getUserLevel(user.getPoint()));
        book.setRentUserId(user.getId());
        book.setStatus(BookStatus.RENTNOW);

        History history = new History();
        history.setBookId(book.getId());
        history.setUserId(user.getId());
        history.setActionType(ActionType.RENT);

        historyDao.add(history);
        bookDao.update(book);
        userDao.update(user);
        return true;
    }

    @Override
    public boolean returnBook(int userId, int bookId) {
        User user = userDao.getById(userId);
        Book book = bookDao.getById(bookId);

        if(book.getRentUserId() == null) {
            throw new IllegalStateException("Book do not has rent user!");
        } else if(user.getId() != book.getRentUserId()) {
            throw new IllegalStateException("Rent User is not same in this book");
        }

        book.setRentUserId(null);
        book.setStatus(BookStatus.CANRENT);

        History history = new History();
        history.setBookId(book.getId());
        history.setUserId(user.getId());
        history.setActionType(ActionType.RETURN);

        historyDao.add(history);
        bookDao.update(book);

        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listup() {
        return userDao.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<History> getHistories(int userId) {
        return null;
    }

    @Override
    public void setUserLevelService(UserLevelService userLevelService) {
        this.userLevelService = userLevelService;
    }
}
