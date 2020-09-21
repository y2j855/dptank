package com.tony.tank.strategy;

import com.tony.tank.Tank;
import com.tony.tank.abstractfactory.BaseTank;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/19 20:53
 * Description:
 */
public interface FireStrategy {
    /**
     * 坦克开火方式
     * @param tank  坦克对象
     */
    void fire(BaseTank tank);
}
