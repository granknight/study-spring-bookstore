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

    public int getMaxMasterPoint() {
        return maxMasterPoint;
    }

    public void setMaxMasterPoint(int maxMasterPoint) {
        this.maxMasterPoint = maxMasterPoint;
    }

    public int maxNormalPoint;
    public int maxMasterPoint;

    @Override
    public UserLevel getUserLevel(int point) {
        if(point < maxNormalPoint) {
            return UserLevel.NORMAL;
        } else if(point < maxMasterPoint) {
            return UserLevel.MASTER;
        } else {
            return UserLevel.MVP;
        }
    }
}
