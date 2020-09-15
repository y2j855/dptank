package com.tony.tank;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/14 21:18
 * Description:
 */
public class Tank {
    private int x, y;
    private Direction dir = Direction.DOWN;
    private static final int SPEED = 10;

    public Tank(int x, int y,Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);

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
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}
