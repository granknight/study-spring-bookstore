package me.xyzlast.bh.jpadao;

import me.xyzlast.bh.entities.History;
import me.xyzlast.bh.intefaces.HistoryDao;
import me.xyzlast.bh.utils.JpaAction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ykyoon on 2/26/14.
 */
@Repository
public class HistoryDaoImpl extends AbstractJpaDao<History> implements HistoryDao {
    public HistoryDaoImpl() {
        super("History");
    }

    @Override
    public List<History> listByUser(final int userId) {
        Query query = em.createQuery("from History where user.id = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
