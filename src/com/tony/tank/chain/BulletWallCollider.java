package com.tony.tank.chain;

import com.tony.tank.Bullet;
import com.tony.tank.Wall;
import com.tony.tank.facade.GameObject;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/12 10:08
 * Description:子弹与墙碰撞的实现类
 */
public class BulletWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall) o2;
            if(bullet.rect.intersects(wall.rect)){
                bullet.die();
                return false;
            }
        }else if(o1 instanceof Wall && o2 instanceof Bullet){
            collide(o2,o1);
        }
        return true;
    }
}
