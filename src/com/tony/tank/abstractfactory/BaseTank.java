package com.tony.tank.abstractfactory;

import com.tony.tank.Direction;
import com.tony.tank.Group;
import com.tony.tank.TankFrame;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/21 09:57
 * Description:
 */
public abstract class BaseTank {
    public Rectangle rect = new Rectangle();
    public TankFrame tf;

    public abstract void drawTank(Graphics g);

    public abstract void fire();

    public abstract void paint(Graphics g);

    public abstract void setMoving(boolean b);

    public abstract void setDir(Direction left);

    public abstract Group getGroup();

    public abstract void die();

    public abstract Direction getDir();

    public abstract int getX();

    public abstract int getY();
}
