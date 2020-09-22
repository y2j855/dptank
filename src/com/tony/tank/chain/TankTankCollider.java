package com.tony.tank.chain;

import com.tony.tank.Tank;
import com.tony.tank.facade.GameObject;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/22 17:08
 * Description:
 */
public class TankTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if(t1.rect.intersects(t2.rect)){
                t1.stop();
                t2.stop();
            }
        }
        return true;
    }
}
