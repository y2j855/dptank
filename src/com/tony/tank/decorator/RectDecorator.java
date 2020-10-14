package com.tony.tank.decorator;

import com.tony.tank.facade.GameObject;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/12 21:25
 * Description:
 */
public class RectDecorator extends GODecorator {

    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);


        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(go.x, go.y, go.WIDTH+2, go.HEIGHT+2);
        g.setColor(c);
    }
}
