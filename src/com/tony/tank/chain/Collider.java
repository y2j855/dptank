package com.tony.tank.chain;

import com.tony.tank.facade.GameObject;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/22 16:40
 * Description:
 */
public interface Collider {
    /**
     * @param o1
     * @param o2
     * @return  true:执行下一个collider false:不执行下一个collider
     */
    boolean collide(GameObject o1, GameObject o2);
}
