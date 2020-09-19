package com.tony.tank.strategy;

import com.tony.tank.Tank;
import com.tony.tank.TankFrame;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/19 20:53
 * Description:
 */
public interface FireStrategy {
    /**
     * 坦克开火方式
     * @param tf 画板对象
     * @param tank  坦克对象
     */
    void fire(TankFrame tf, Tank tank);
}
