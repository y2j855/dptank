package com.tony.tank.decorator;

import com.tony.tank.facade.GameObject;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/12 21:06
 * Description:
 */
public abstract class GODecorator extends GameObject {

    protected GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public abstract void paint(Graphics g);

}
