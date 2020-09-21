package com.tony.tank.abstractfactory.rect;

import com.tony.tank.*;
import com.tony.tank.abstractfactory.BaseBullet;
import com.tony.tank.abstractfactory.BaseTank;

import java.awt.*;

/**
 * @author: chenhao
 * @date: 2019-07-24
 * @description:
 */

public class RectBullet extends BaseBullet {
    private static final int BULLET_SPEED = 30;
    private int x = 200;
    private int y = 200;
    public static final int WIDTH = ResourceManager.bulletD.getWidth();
    public static final int HEIGHT = ResourceManager.bulletD.getHeight();
    private Direction dir = Direction.DOWN;

    Rectangle rect = null;

    private boolean live = true;

    private Group group = Group.BAD;

    public RectBullet(int x, int y, Direction dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        rect = new Rectangle(x, y, WIDTH, HEIGHT);

        tf.bullets.add(this);
    }

    @Override
    public void drawBullet(Graphics g) {

    }

    @Override
    public void paint(Graphics g) {
        if (!isLive) {
            tf.bullets.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 10, 10);
        g.setColor(c);
        move();
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
    protected void die() {
        this.isLive = false;
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

    /**
     * 碰撞检测方法
     *
     * @param enemyTank
     * @return
     */
    private boolean collisionWith(BaseTank enemyTank) {
        //同一组坦克的子弹不造成伤害
        if (this.group == enemyTank.getGroup()) {
            return false;
        }
        return rect.intersects(enemyTank.rect);
    }
}
