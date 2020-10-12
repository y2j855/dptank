package com.tony.tank.chain;

import com.tony.tank.Tank;
import com.tony.tank.Wall;
import com.tony.tank.facade.GameObject;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/12 10:22
 * Description:
 */

public class TankWallCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;
            if(tank.rect.intersects(wall.rect)){
                tank.stop();
                return false;
            }
        }
        return true;
    }
}
