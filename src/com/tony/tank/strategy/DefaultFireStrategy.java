package com.tony.tank.strategy;

import com.tony.tank.Audio;
import com.tony.tank.Group;
import com.tony.tank.Tank;
import com.tony.tank.abstractfactory.BaseBullet;
import com.tony.tank.abstractfactory.BaseTank;
import com.tony.tank.abstractfactory.rect.RectFactory;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/19 20:54
 * Description:
 */
public class DefaultFireStrategy implements FireStrategy{

    @Override
    public void fire(BaseTank tank) {
        //把子弹移动到坦克中间发射
        int bX = tank.getX() + Tank.WIDTH / 2 - BaseBullet.WIDTH / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - BaseBullet.HEIGHT / 2;
//        new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.tf);
        tank.tf.factory.createBullet(bX,bY,tank.getDir(),tank.getGroup(),tank.tf);
        if(tank.getGroup() == Group.GOOD){
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
