package com.tony.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/14 15:41
 * Description:
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 500, Direction.DOWN, Group.GOOD, this);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> enemyTanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public TankFrame() {
        setSize(GameModel.GAME_WIDTH, GameModel.GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
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

    //解决双缓冲
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (null == offScreenImage) {
            offScreenImage = this.createImage(GameModel.GAME_WIDTH, GameModel.GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GameModel.GAME_WIDTH, GameModel.GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }
    //

    private class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bR && !bU && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
            }
            if (bL) {
                myTank.setDir(Direction.LEFT);
            }
            if (bU) {
                myTank.setDir(Direction.UP);
            }
            if (bR) {
                myTank.setDir(Direction.RIGHT);
            }
            if (bD) {
                myTank.setDir(Direction.DOWN);
            }
        }
    }
}
