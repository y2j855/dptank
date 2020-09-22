package com.tony.tank.facade;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/22 15:55
 * Description:
 * mediator(调停者/中介者模式)
 */
public abstract class GameObject {

    public int x,y;

    public GameModel gm;

    public abstract void paint(Graphics g);

}
