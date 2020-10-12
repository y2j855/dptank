package com.tony.tank.chain;

import com.tony.tank.Bullet;
import com.tony.tank.Tank;
import com.tony.tank.facade.GameObject;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/22 16:45
 * Description:
 */
public class BulletTankCollider implements Collider {


    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            if (bullet.group == tank.getGroup()) {
                return true;
            }
            if (bullet.rect.intersects(tank.rect)) {
                tank.die();
                bullet.die();
                return false;
            }
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            collide(o2,o1);
        }
        return true;
    }
}
