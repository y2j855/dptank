package com.tony.tank.abstractfactory;

import com.tony.tank.*;
import com.tony.tank.abstractfactory.rect.RectTank;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/21 10:04
 * Description:
 */
public class NormalFactory extends AbstractFactory{
    private static final NormalFactory normal = new NormalFactory();

    private NormalFactory(){

    }

    public static NormalFactory getInstance(){
        return normal;
    }


    @Override
    public BaseTank createTank(int x, int y, Direction dir, Group group, TankFrame tf) {
        return new Tank(x,y,dir,group,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direction dir, Group group, TankFrame tf) {
        return new Bullet(x,y,dir,group,tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x,y,tf);
    }
}
