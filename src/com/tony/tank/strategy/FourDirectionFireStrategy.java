package com.tony.tank.strategy;

import com.tony.tank.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/19 21:22
 * Description:
 */
public class FourDirectionFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        //把子弹移动到坦克中间发射
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        
        for(Direction dir : Direction.values()){
            new Bullet(bX, bY, dir, tank.getGroup(), tank.tf);
        }

        if(tank.getGroup() == Group.GOOD){
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
