package com.tony.tank.abstractfactory;

import com.tony.tank.ResourceManager;
import com.tony.tank.TankFrame;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/21 10:00
 * Description:
 */
public abstract class BaseExplode {
    protected TankFrame tf = null;

    public abstract void drawExplode(Graphics g);

    public abstract void paint(Graphics g);
}
