package com.tony.tank.abstractfactory.rect;

import com.tony.tank.ResourceManager;
import com.tony.tank.TankFrame;
import com.tony.tank.abstractfactory.BaseExplode;

import java.awt.*;

/**
 * @author: chenhao
 * @date: 2019-07-24
 * @description:
 */

public class RectExplode extends BaseExplode {
    private int x, y;

    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    @Override
    public void paint(Graphics g) {
        drawExplode(g);
    }

    @Override
    public void drawExplode(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10 * step,10*step);
        step++;
        g.setColor(c);

        if (step >= ResourceManager.explodes.length){
            tf.explodes.remove(this);
        }

    }
}
