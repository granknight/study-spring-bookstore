package me.xyzlast.bh.utils;

import javax.persistence.EntityManager;

/**
 * Created by ykyoon on 2/26/14.
 */
public interface JpaAction {
    Object execute(EntityManager em);
}
