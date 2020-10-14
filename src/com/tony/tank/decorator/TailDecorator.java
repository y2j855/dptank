package com.tony.tank.decorator;

import com.tony.tank.facade.GameObject;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/14 13:35
 * Description:
 */
public class TailDecorator extends GODecorator{
    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(go.x, go.y, go.x + go.WIDTH, go.y + go.HEIGHT);
        g.setColor(c);
    }
}
