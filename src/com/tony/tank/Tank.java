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
    private static final int SPEED = 5;

    private boolean moving = false;

    /**
     * 根据业务，之所以把画板的类的引用放到tank里边，是应为tank需要打子弹
     * 为什么不用一个返回子弹对象的方法，是因为如果只返回一个子弹对象是可以这么做的，
     * 但考虑到以后要返回多个子弹对象，这种方式就不灵活了。所以把画板的类放到tank里
     * 对扩展更加灵活。
     */
    private TankFrame tf;

    public Tank(int x, int y,Direction dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        g.setColor(c);
        move();
    }

    private void move() {
        if(moving) {
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
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void fire() {
        tf.bullet = new Bullet(x, y, dir);
    }
}
