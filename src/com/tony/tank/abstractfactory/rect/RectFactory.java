package com.tony.tank.abstractfactory.rect;

import com.tony.tank.Direction;
import com.tony.tank.Group;
import com.tony.tank.TankFrame;
import com.tony.tank.abstractfactory.AbstractFactory;
import com.tony.tank.abstractfactory.BaseBullet;
import com.tony.tank.abstractfactory.BaseExplode;
import com.tony.tank.abstractfactory.BaseTank;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/21 11:34
 * Description:
 */
public class RectFactory extends AbstractFactory {
    private static final RectFactory rect = new RectFactory();

    private RectFactory(){}

    public static RectFactory getInstance(){
        return rect;
    }
    @Override
    public BaseTank createTank(int x, int y, Direction dir, Group group, TankFrame tf) {
        return new RectTank(x,y,dir,group,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direction dir, Group group, TankFrame tf) {
        return new RectBullet(x,y,dir,group,tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x,y,tf);
    }
}
