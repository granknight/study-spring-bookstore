package me.xyzlast.mybatis.dao;

import me.xyzlast.mybatis.entities.History;

import java.util.List;

/**
 * Created by ykyoon on 1/13/14.
 */
public interface HistoryDao extends EntityDao<History> {
    List<History> listByUser(int userId);
}
