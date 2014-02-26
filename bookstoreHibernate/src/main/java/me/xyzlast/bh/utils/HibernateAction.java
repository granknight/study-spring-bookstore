package me.xyzlast.bh.utils;

import org.hibernate.Session;

/**
 * Created by ykyoon on 2/26/14.
 */
public interface HibernateAction {
    Object doProcess(Session session);
}
