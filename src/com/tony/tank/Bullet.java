package com.tony.tank;

import com.tony.tank.facade.GameModel;
import com.tony.tank.facade.GameObject;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/15 10:07
 * Description:
 */
public class Bullet extends GameObject {

    private static final int SPEED = 10;

    public static final int WIDTH = ResourceManager.bulletD.getWidth();

    public static final int HEIGHT = ResourceManager.bulletD.getHeight();

    private Direction dir;

    public boolean isLive = true;

    public Rectangle rect = new Rectangle();

    public Group group;

    public Bullet(int x, int y, Direction dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (!isLive) {
            GameModel.getInstance().remove(this);
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

    public void die() {
        this.isLive = false;
    }
}
