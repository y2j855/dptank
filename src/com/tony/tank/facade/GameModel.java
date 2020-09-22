package com.tony.tank.facade;

import com.tony.tank.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/22 15:08
 * Description:
 */
public class GameModel {
    public static final int GAME_WIDTH = 1080;
    public static final int GAME_HEIGHT = 800;

    public Tank myTank = new Tank(200, 500, Direction.DOWN, Group.GOOD, this);
    public List<Bullet> bullets = new ArrayList<>();
    public List<Tank> enemyTanks = new ArrayList<>();
    public List<Explode> explodes = new ArrayList<>();

    private static final GameModel gm = new GameModel();

    private GameModel(){
    }

    public static GameModel getInstance(){
        return gm;
    }

    public Tank getMyTank(){
        return myTank;
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量：" + bullets.size(), 10, 60);
        g.drawString("敌军坦克数量：" + enemyTanks.size(), 100, 60);
        g.drawString("爆炸数量：" + explodes.size(), 200, 60);
        g.setColor(c);
        myTank.paint(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < enemyTanks.size(); i++) {
            enemyTanks.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemyTanks.size(); j++) {
                bullets.get(i).collideWith(enemyTanks.get(j));
            }
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }


}
