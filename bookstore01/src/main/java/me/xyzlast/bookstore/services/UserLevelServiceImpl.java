package me.xyzlast.bookstore.services;

import me.xyzlast.bookstore.constants.UserLevel;

/**
 * Created by ykyoon on 1/13/14.
 */
public class UserLevelServiceImpl implements UserLevelService {

    public int getMaxNormalPoint() {
        return maxNormalPoint;
    }

    public void setMaxNormalPoint(int maxNormalPoint) {
        this.maxNormalPoint = maxNormalPoint;
    }

    public int getMaxMVPPoint() {
        return maxMVPPoint;
    }

    public void setMaxMVPPoint(int maxMVPPoint) {
        this.maxMVPPoint = maxMVPPoint;
    }

    public int maxNormalPoint;
    public int maxMVPPoint;

    @Override
    public UserLevel getUserLevel(int point) {
        if(point < maxNormalPoint) {
            return UserLevel.NORMAL;
        } else if(point < maxMVPPoint) {
            return UserLevel.MVP;
        } else {
            return UserLevel.MASTER;
        }
    }
}
