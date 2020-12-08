package com.tony.tank.facade;

import com.tony.tank.*;
import com.tony.tank.chain.Collider;
import com.tony.tank.chain.ColliderChain;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/22 15:08
 * Description:
 * Facade(门面/外观模式) 对 TankFrame
 * mediator(调停者/中介者模式) 对 Tank,Bullet,Explode
 * singleton 对外开放add，remove方法。让代码更加简洁
 */
public class GameModel {
    public static final int GAME_WIDTH = 1080;

    public static final int GAME_HEIGHT = 800;

    private static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.initData();
    }

    /**
     * 如果在此处new这个对象，会报空指针，原因是因为INSTANCE还没有初始化好，
     * new Tank就会调用此初始化
     */
    private Tank myTank;

    private List<GameObject> objects = new ArrayList<>();


    Collider chain = new ColliderChain();


    private GameModel() {
    }

    private void initData() {
        myTank = new Tank(200, 500, Direction.DOWN, Group.GOOD);

        int initTankCount = Integer.parseInt((String) PropertyManager.getInstance().get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + i * 80, 200, Direction.DOWN, Group.BAD);
        }

//        int initWallCount = Integer.parseInt((String)PropertyManager.getInstance().get("initWallCount"));
//        for (int i = 0; i < initWallCount; i++) {
//            new Wall(120*(i+1),120*(i+1));
//        }
        // 初始化墙
        new Wall(150, 150, 200, 50);
        new Wall(550, 150, 200, 50);
        new Wall(300, 300, 50, 200);
        new Wall(550, 300, 50, 200);
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    /**
     * @return 获取我的坦克
     */
    public Tank getMyTank() {
        return myTank;
    }

    public void add(GameObject object) {
        objects.add(object);
    }

    public void remove(GameObject object) {
        objects.remove(object);
    }

    public void paint(Graphics g) {
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
//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < enemyTanks.size(); j++) {
//                ((Bullet)bullets.get(i)).collideWith((Tank) enemyTanks.get(j));
//            }
//        }

        //修改以前边界测试实现，引出责任链模式(Chain of Responsibility pattern)
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject object1 = objects.get(i);
                GameObject object2 = objects.get(j);
                chain.collide(object1, object2);
            }
        }

    }

    /**
     * memento模式
     * 添加gamemodel序列化
     */
    public void save() {
        /**
         * TODO 绝对路径 相对路径问题
         * 如果写成相对路径会报空指针，需要代码创建文件，如果写绝对路径就不会出现这个问题
         */
        File file = new File("/Users/chenhao/work/github/dptank/src/file/tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(myTank);
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * memento模式
     * 添加gamemodel反序列化
     */
    public void load(){
        File file = new File("/Users/chenhao/work/github/dptank/src/file/tank.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            myTank = (Tank) ois.readObject();
            objects = (List<GameObject>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(ois !=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
