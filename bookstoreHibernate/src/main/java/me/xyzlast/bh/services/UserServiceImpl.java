package me.xyzlast.bh.services;

import me.xyzlast.bh.constants.ActionType;
import me.xyzlast.bh.constants.BookStatus;
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
public class UserServiceImpl extends AbstractSessionFactoryService implements UserService {

    private UserLevelService userLevelService;

    @Override
    public boolean rent(int userId, int bookId) {
        Session session = getSession();
        try {
            User user = (User) session.get(User.class, userId);
            Book book = (Book) session.get(Book.class, bookId);

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

            session.save(history);
            session.saveOrUpdate(user);
            session.saveOrUpdate(book);
            session.flush();

            return true;
        } finally {
            session.close();
        }
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
        Session session = getSession();

        return null;
    }

    @Override
    public void setUserLevelService(UserLevelService userLevelService) {
        this.userLevelService = userLevelService;
    }

    @Override
    public User findByName(String name) {
        Session session = getSession();
        try {
            return (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("name", name))
                    .uniqueResult();
        } finally {
            session.close();
        }
    }
}
