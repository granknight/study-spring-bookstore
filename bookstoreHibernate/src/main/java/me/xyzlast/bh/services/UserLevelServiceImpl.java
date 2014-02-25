package me.xyzlast.bh.services;

import me.xyzlast.bh.constants.UserLevel;

/**
 * Created by ykyoon on 2/25/14.
 */
public class UserLevelServiceImpl implements UserLevelService {

    public UserLevelServiceImpl() {
        maxNormalPoint = 100;
        maxMasterPoint = 300;
    }

    public UserLevelServiceImpl(int maxNormalPoint, int maxMasterPoint) {
        this.maxNormalPoint = maxNormalPoint;
        this.maxMasterPoint = maxMasterPoint;
    }

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
