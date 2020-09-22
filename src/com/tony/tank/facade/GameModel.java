package com.tony.tank.facade;

import com.tony.tank.Direction;
import com.tony.tank.Group;
import com.tony.tank.PropertyManager;
import com.tony.tank.Tank;
import com.tony.tank.chain.Collider;
import com.tony.tank.chain.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/22 15:08
 * Description:
 * Facade(门面/外观模式) 对 TankFrame
 * mediator(调停者/中介者模式) 对 Tank,Bullet,Explode
 */
public class GameModel {
    public static final int GAME_WIDTH = 1080;
    public static final int GAME_HEIGHT = 800;

    public Tank myTank = new Tank(200, 500, Direction.DOWN, Group.GOOD, this);

    private List<GameObject> objects = new ArrayList<>();

    private static final GameModel gm = new GameModel();

    Collider chain = new ColliderChain();

    private GameModel(){
        int initTankCount = Integer.parseInt((String)PropertyManager.getInstance().get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            add(new Tank(50 + i*80,200,Direction.DOWN,Group.BAD,this));
        }
    }

    public static GameModel getInstance(){
        return gm;
    }

    /**
     * @return 获取我的坦克
     */
    public Tank getMyTank(){
        return myTank;
    }

    public void add(GameObject object){
        objects.add(object);
    }

    public void remove(GameObject object){
        objects.remove(object);
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹数量：" + bullets.size(), 10, 60);
//        g.drawString("敌军坦克数量：" + enemyTanks.size(), 100, 60);
//        g.drawString("爆炸数量：" + explodes.size(), 200, 60);
        g.setColor(c);
        myTank.paint(g);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
        //修改以前边界测试实现，引出责任链模式(Chain of Responsibility pattern)
//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < enemyTanks.size(); j++) {
//                ((Bullet)bullets.get(i)).collideWith((Tank) enemyTanks.get(j));
//            }
//        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                GameObject object1 = objects.get(i);
                GameObject object2 = objects.get(j);
                chain.collide(object1,object2);
            }
        }

    }


}
