package com.tony.tank.observer;

import com.tony.tank.Tank;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/18 10:49
 * Description:
 * 观察者事件类
 */
public class TankFireEvent {
    private Tank tank;

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }

    public Tank getSource(){
        return tank;
    }
}
