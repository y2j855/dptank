package com.tony.tank;

import com.tony.tank.abstractfactory.BaseBullet;
import com.tony.tank.abstractfactory.BaseTank;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/15 10:07
 * Description:
 */
public class Bullet extends BaseBullet {
    ;

    private int x, y;
    private Direction dir;

    private Rectangle rect = new Rectangle();

    private Group group = Group.GOOD;

    public Bullet(int x, int y, Direction dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.bullets.add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (!isLive) {
            tf.bullets.remove(this);
        }
        drawBullet(g);
        move();

    }

    @Override
    public void drawBullet(Graphics g) {
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
            default:
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

        //update rect
        rect.x = x;
        rect.y = y;

        if (x < 0 || y < 0 || x > GameModel.GAME_WIDTH || y > GameModel.GAME_HEIGHT) {
            isLive = false;
        }
    }

    @Override
    public void collideWith(BaseTank tank) {
        if (this.group == tank.getGroup()) {
            return;
        }
        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();
        }
    }

    @Override
    public void die() {
        this.isLive = false;
    }
}
