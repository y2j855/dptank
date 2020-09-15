package com.tony.tank;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/15 10:07
 * Description:
 */
public class Bullet {
    private static final int SPEED = 10;
    private static int WIDTH = 10, HIGHT = 10;

    private int x, y;
    private Direction dir;

    public boolean isLive = true;
    private TankFrame tf = null;

    public Bullet(int x, int y, Direction dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(!isLive){
            tf.bullets.remove(this);
        }
        drawBullet(g);
        move();

    }

    private void drawBullet(Graphics g) {
        switch (dir) {
            case DOWN:
                g.drawImage(ResourceManager.bulletD, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceManager.bulletU, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR, x, y, null);
                break;
        }
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > GameModel.GAME_WIDTH || y > GameModel.GAME_HEIGHT) isLive = false;
    }
}
