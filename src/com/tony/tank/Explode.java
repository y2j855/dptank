package com.tony.tank;

import com.tony.tank.abstractfactory.BaseExplode;

import java.awt.*;

/**
 * @author: chenhao
 * @date: 2019-07-15
 * @description: 爆炸类
 */

public class Explode extends BaseExplode {

    public static final int WIDTH = ResourceManager.explodes[0].getWidth();
    public static final int HEIGHT = ResourceManager.explodes[0].getHeight();
    private int x,y;
    private int step = 0;

    public Explode(int x, int y,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        drawExplode(g);
    }

    @Override
    public void drawExplode(Graphics g) {
        //TODO 要有多个Explode对象，目前只有一个对象，所以会出现一个还没有画完，另一个已经触发。
        g.drawImage(ResourceManager.explodes[step++], x, y, null);
        if (step >= ResourceManager.explodes.length){
            tf.explodes.remove(this);
        }
    }


}
