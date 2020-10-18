package com.tony.tank.observer;

import com.tony.tank.Tank;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/18 10:53
 * Description:
 * 观察者实现类
 */
public class TankFireHandler extends TankFireObserver{
    @Override
    public void actionOnFire(TankFireEvent event) {
        Tank tank = event.getSource();
        tank.fire();
    }
}
