package me.xyzlast.bh.querydsl;

import me.xyzlast.bh.intefaces.HistoryDao;
import me.xyzlast.bh.entities.History;
import me.xyzlast.bh.entities.QHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ykyoon on 2/26/14.
 */
@Repository
public class HistoryDaoImpl extends AbstractQueryDslDao<History, QHistory> implements HistoryDao {

    public HistoryDaoImpl() {
        super(History.class, QHistory.history);
    }

    @Override
    public List<History> listByUser(int userId) {
        return getSelectQuery()
                .where(q.user.id.eq(userId))
                .orderBy(q.insertDate.desc())
                .list(q);
    }
}
