package com.tony.tank.chain;

import com.tony.tank.facade.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/22 21:27
 * Description:
 */
public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2){
        for (int i = 0; i < colliders.size(); i++) {
            if(!colliders.get(i).collide(o1,o2)){
                return false;
            }
        }
        return true;
    }

    public void add(Collider collider){
        colliders.add(collider);
    }
}
