package com.tony.tank.strategy;

import com.tony.tank.Bullet;
import com.tony.tank.Direction;
import com.tony.tank.Tank;
import com.tony.tank.TankFrame;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/19 21:22
 * Description:
 */
public class FourDirectionFireStrategy implements FireStrategy{
    @Override
    public void fire(TankFrame tf, Tank tank) {
        //把子弹移动到坦克中间发射
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bX, bY, Direction.UP, tank.getGroup(), tf));
        tf.bullets.add(new Bullet(bX, bY, Direction.DOWN, tank.getGroup(), tf));
        tf.bullets.add(new Bullet(bX, bY, Direction.LEFT, tank.getGroup(), tf));
        tf.bullets.add(new Bullet(bX, bY, Direction.RIGHT, tank.getGroup(), tf));
    }
}
