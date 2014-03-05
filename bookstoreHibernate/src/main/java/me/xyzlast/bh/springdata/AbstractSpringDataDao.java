package me.xyzlast.bh.springdata;

import me.xyzlast.bh.entities.BaseEntity;
import me.xyzlast.bh.intefaces.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ykyoon on 3/5/14.
 */
public abstract class AbstractSpringDataDao<T extends BaseEntity> implements EntityDao<T> {

    @Autowired
    protected JpaRepository<T, Integer> repository;

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public T getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public void add(T entity) {
        repository.save(entity);
    }

    @Override
    public void update(T entity) {
        repository.save(entity);
    }

    @Override
    public int countAll() {
        return Long.valueOf(repository.count()).intValue();
    }
}
