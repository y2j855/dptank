package com.tony.tank;

import java.awt.*;
import java.awt.event.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/14 15:41
 * Description:
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200,400,Direction.DOWN);

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
        myTank.paint(g);
    }

    private class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bL&&!bR&&!bU&&!bD){
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
            }
            if (bL) myTank.setDir(Direction.LEFT);
            if (bU) myTank.setDir(Direction.UP);
            if (bR) myTank.setDir(Direction.RIGHT);
            if (bD) myTank.setDir(Direction.DOWN);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
    }
}
