package me.xyzlast.bh.services;

import me.xyzlast.bh.constants.UserLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by ykyoon on 2/25/14.
 */
@Service
public class UserLevelServiceImpl implements UserLevelService {

    public UserLevelServiceImpl() {

    }

    public UserLevelServiceImpl(int maxNormalPoint, int maxMasterPoint) {
        this.maxNormalPoint = maxNormalPoint;
        this.maxMasterPoint = maxMasterPoint;
    }

    public int getMaxNormalPoint() {
        return maxNormalPoint;
    }
    @Value(value = "${level.maxNormalPoint}")
    public void setMaxNormalPoint(int maxNormalPoint) {
        this.maxNormalPoint = maxNormalPoint;
    }
    public int getMaxMasterPoint() {
        return maxMasterPoint;
    }
    @Value(value = "${level.maxMasterPoint}")
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
