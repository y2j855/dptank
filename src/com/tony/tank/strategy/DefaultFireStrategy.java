package com.tony.tank.strategy;

import com.tony.tank.Audio;
import com.tony.tank.Bullet;
import com.tony.tank.Group;
import com.tony.tank.Tank;
import com.tony.tank.decorator.RectDecorator;
import com.tony.tank.decorator.TailDecorator;
import com.tony.tank.facade.GameModel;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/19 20:54
 * Description:
 */
public class DefaultFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank tank) {
        //把子弹移动到坦克中间发射
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        //Bug? new Bullet把自己加了一遍 只是为了明白装饰器模式的用法，现实很少用
//		GameModel.getInstance().add(
//				new RectDecorator(
//						new TailDecorator(
//						new Bullet(bX, bY, tank.getDir(), tank.getGroup()))));

        new Bullet(bX, bY, tank.getDir(), tank.getGroup());

        if(tank.getGroup() == Group.GOOD){
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
