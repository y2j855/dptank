package com.tony.tank.observer;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/18 10:48
 * Description:
 * 观察者抽象类
 */
public abstract class TankFireObserver {
    public abstract void actionOnFire(TankFireEvent event);
}
