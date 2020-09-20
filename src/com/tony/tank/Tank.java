package com.tony.tank;

import com.tony.tank.strategy.DefaultFireStrategy;
import com.tony.tank.strategy.FireStrategy;
import com.tony.tank.strategy.FourDirectionFireStrategy;

import java.awt.*;
import java.util.Random;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/14 21:18
 * Description:
 */
public class Tank {
    public int x, y;

    private Direction dir = Direction.DOWN;
    private static final int SPEED = 5;
    public static final int WIDTH = ResourceManager.goodTankD.getWidth();
    public static final int HEIGHT = ResourceManager.goodTankD.getHeight();

    private boolean moving = true;
    private boolean isLive = true;

    private Random random = new Random();

    private Group group = Group.GOOD;

    public Rectangle rect = new Rectangle();

    /**
     * 根据业务，之所以把画板的类的引用放到tank里边，是因为tank需要打子弹
     * 为什么不用一个返回子弹对象的方法，是因为如果只返回一个子弹对象是可以这么做的，
     * 但考虑到以后要返回多个子弹对象，这种方式就不灵活了。所以把画板的类放到tank里
     * 对扩展更加灵活。
     */
    public TankFrame tf;

    public Tank(int x, int y, Direction dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if (!isLive) {
            tf.enemyTanks.remove(this);
        }
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


        if (random.nextInt(100) > 95 && group == Group.BAD) {
            this.fire();
            randomDir();
        }
        //坦克画板的边界检测
        boundsCheck();

        //update rect
        rect.x = x;
        rect.y = y;
    }

    private void boundsCheck() {
        if (this.x < 0) {
            x = 0;
        }
        if (this.y < 30) {
            y = 30;
        }
        if (this.x > GameModel.GAME_WIDTH - Tank.WIDTH) {
            x = GameModel.GAME_WIDTH - Tank.WIDTH;
        }
        if (this.y > GameModel.GAME_HEIGHT - Tank.HEIGHT) {
            y = GameModel.GAME_HEIGHT - Tank.HEIGHT;
        }
    }

    private void randomDir() {
        this.dir = Direction.values()[random.nextInt(4)];
    }

    public boolean isMoving() {
        return moving;
    }

    public void fire() {
        FireStrategy fs = new DefaultFireStrategy();
        if(group== Group.GOOD){
            try {
                fs = (FireStrategy) Class.forName((String)PropertyManager.getInstance().get("goodFS")).newInstance();
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        fs.fire(this);
    }

    public void die() {
        this.isLive = false;
        int eX = this.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
        int eY = this.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
        tf.explodes.add(new Explode(eX, eY, tf));
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
