package com.tony.tank.chain;

import com.tony.tank.PropertyManager;
import com.tony.tank.facade.GameObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/22 21:27
 * Description:责任链模式(chain of responsibility)
 */
public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        initCollider();
    }

    /**
     * 初始化碰撞检测责任链
     */
    private void initCollider() {
        String[] colliders = ((String) PropertyManager.getInstance().get("colliders")).split(",");
        for (int i = 0; i < colliders.length; i++) {
            try {
                add((Collider) Class.forName(colliders[i]).newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
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
