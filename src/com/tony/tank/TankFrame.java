package com.tony.tank;

import java.awt.*;
import java.awt.event.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/14 15:41
 * Description:
 */
public class TankFrame extends Frame {
    int x = 200, y = 200;

    public TankFrame() {
        setSize(800, 600);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        g.fillRect(x, y, 50, 50);
//        x += 10;
    }

    private class MyKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    x -= 10;
                    break;
                case KeyEvent.VK_RIGHT:
                    x +=10;
                    break;
                case KeyEvent.VK_UP:
                    y -=10;
                    break;
                case KeyEvent.VK_DOWN:
                    y += 10;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
