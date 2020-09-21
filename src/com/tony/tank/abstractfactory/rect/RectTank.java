package com.tony.tank.abstractfactory.rect;

import com.tony.tank.*;
import com.tony.tank.abstractfactory.BaseTank;
import com.tony.tank.strategy.DefaultFireStrategy;
import com.tony.tank.strategy.FireStrategy;

import java.awt.*;
import java.util.Random;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/21 10:58
 * Description:
 */
public class RectTank extends BaseTank {

    public int x, y;

    private Direction dir;
    private static final int SPEED = 5;
    public static final int WIDTH = ResourceManager.goodTankD.getWidth();
    public static final int HEIGHT = ResourceManager.goodTankD.getHeight();

    private boolean moving = true;
    private boolean isLive = true;

    private Random random = new Random();

    private Group group;

    public RectTank(int x, int y, Direction dir, Group group, TankFrame tf) {
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

    @Override
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

    @Override
    public void paint(Graphics g) {
        if (isLive) {

            Color c = g.getColor();
            g.setColor(this.group == Group.GOOD ? Color.RED : Color.YELLOW);
            g.fillRect(this.x, this.y, 40, 40);
            g.setColor(c);
            move();
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

    @Override
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    @Override
    public void setDir(Direction dir) {
        this.dir = dir;
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void die() {
        this.isLive = false;
        int eX = this.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
        int eY = this.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
        tf.explodes.add(tf.factory.createExplode(eX,eY,tf));
    }

    @Override
    public Direction getDir() {
        return dir;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
