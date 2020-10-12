package com.tony.tank;

import com.tony.tank.facade.GameModel;
import com.tony.tank.facade.GameObject;

import java.awt.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/11 17:02
 * Description:
 */
public class Wall extends GameObject {

    public static final int WIDTH = 80;

    public static final int HEIGHT = 150;

    public Rectangle rect;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        rect = new Rectangle(x,y,WIDTH,HEIGHT);

        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(c);
    }
}
