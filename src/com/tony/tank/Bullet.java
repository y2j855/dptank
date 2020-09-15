package com.tony.tank;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/15 10:07
 * Description:
 */
public class Bullet {
    private static final int SPEED = 10;
    public static int WIDTH = ResourceManager.bulletD.getWidth();
    public static int HEIGHT = ResourceManager.bulletD.getHeight();;

    private int x, y;
    private Direction dir;

    public boolean isLive = true;
    private TankFrame tf = null;

    private Group group = Group.GOOD;

    public Bullet(int x, int y, Direction dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
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

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return;

        //TODO:用一个rect来记录子弹的位置，现在是每次都会创建Rectangle对象，对象创建太多了。
        Rectangle rect = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rectTank = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if(rect.intersects(rectTank)){
            tank.die();
            this.die();
        }

    }

    private void die() {
        this.isLive = false;
    }
}
