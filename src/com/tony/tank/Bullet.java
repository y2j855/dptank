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
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x, y, WIDTH, HIGHT);
        g.setColor(c);
        move();

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
