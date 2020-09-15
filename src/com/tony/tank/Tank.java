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
    public static final int WIDTH = ResourceManager.goodTankD.getWidth();
    public static final int HEIGHT = ResourceManager.goodTankD.getHeight();

    private boolean moving = false;

    private Group group = Group.GOOD;

    /**
     * 根据业务，之所以把画板的类的引用放到tank里边，是应为tank需要打子弹
     * 为什么不用一个返回子弹对象的方法，是因为如果只返回一个子弹对象是可以这么做的，
     * 但考虑到以后要返回多个子弹对象，这种方式就不灵活了。所以把画板的类放到tank里
     * 对扩展更加灵活。
     */
    private TankFrame tf;

    public Tank(int x, int y, Direction dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
        drawTank(g);
        move();
    }

    private void drawTank(Graphics g) {
        switch (dir) {
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankD :
                        ResourceManager.badTankD, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankU :
                        ResourceManager.badTankU, x, y, null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankL :
                        ResourceManager.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankR :
                        ResourceManager.badTankR, x, y, null);
                break;
        }
    }

    private void move() {
        if (moving) {
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

    public void fire() {
        //把子弹移动到坦克中间发射
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bX, bY, dir, this.group, this.tf));
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}
