package com.tony.tank.abstractfactory;

import com.tony.tank.ResourceManager;
import com.tony.tank.Tank;
import com.tony.tank.TankFrame;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/21 09:59
 * Description:
 */
public abstract class BaseBullet {

    protected static final int SPEED = 10;
    public static int WIDTH = ResourceManager.bulletD.getWidth();
    public static int HEIGHT = ResourceManager.bulletD.getHeight();
    public boolean isLive = true;

    public TankFrame tf;

    public abstract void drawBullet(Graphics g);

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);

    protected abstract void die();
}
