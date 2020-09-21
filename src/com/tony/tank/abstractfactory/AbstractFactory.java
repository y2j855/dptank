package com.tony.tank.abstractfactory;

import com.tony.tank.Direction;
import com.tony.tank.Group;
import com.tony.tank.TankFrame;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/21 10:02
 * Description:
 */
public abstract class AbstractFactory {
    public abstract BaseTank createTank(int x, int y, Direction dir, Group group, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Direction dir, Group group, TankFrame tf);
    public abstract BaseExplode createExplode(int x, int y,TankFrame tf);
}
