package me.xyzlast.bh.springdata;

import me.xyzlast.bh.entities.History;
import me.xyzlast.bh.entities.User;
import me.xyzlast.bh.intefaces.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ykyoon on 3/5/14.
 */
@Repository
public class HistoryDaoImpl extends AbstractSpringDataDao<History> implements HistoryDao {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<History> listByUser(int userId) {
        User user = userRepository.findOne(userId);
        return historyRepository.findByUserOrderByInsertDateDesc(user);
    }
}
