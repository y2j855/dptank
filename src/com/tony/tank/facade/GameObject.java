package com.tony.tank.facade;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/22 15:55
 * Description:所有画板上游戏对象的超类
 */
public abstract class GameObject {

    public int x,y;

    public int WIDTH;

    public int HEIGHT;

    public abstract void paint(Graphics g);

}
