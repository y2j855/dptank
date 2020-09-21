package com.tony.tank.strategy;

import com.tony.tank.*;
import com.tony.tank.abstractfactory.BaseBullet;
import com.tony.tank.abstractfactory.BaseTank;
import com.tony.tank.abstractfactory.rect.RectFactory;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/19 21:22
 * Description:
 */
public class FourDirectionFireStrategy implements FireStrategy{
    @Override
    public void fire(BaseTank tank) {
        //把子弹移动到坦克中间发射
        int bX = tank.getX() + Tank.WIDTH / 2 - BaseBullet.WIDTH / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - BaseBullet.HEIGHT / 2;
        
        for(Direction dir : Direction.values()){
            tank.tf.factory.createBullet(bX,bY,dir,tank.getGroup(),tank.tf);
        }

        if(tank.getGroup() == Group.GOOD){
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
