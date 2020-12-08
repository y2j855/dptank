package com.tony.tank;

import com.tony.tank.facade.GameModel;
import com.tony.tank.strategy.DefaultFireStrategy;
import com.tony.tank.strategy.FireStrategy;
import com.tony.tank.strategy.FourDirectionFireStrategy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/14 15:41
 * Description:
 */
public class TankFrame extends Frame {

    GameModel gm = GameModel.getInstance();


    public TankFrame() {
        setSize(com.tony.tank.facade.GameModel.GAME_WIDTH, com.tony.tank.facade.GameModel.GAME_HEIGHT);
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
        gm.paint(g);
    }

    //解决双缓冲
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (null == offScreenImage) {
            offScreenImage = this.createImage(com.tony.tank.facade.GameModel.GAME_WIDTH, com.tony.tank.facade.GameModel.GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, com.tony.tank.facade.GameModel.GAME_WIDTH, com.tony.tank.facade.GameModel.GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }
    //

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
                case KeyEvent.VK_S:
                    gm.save();
                    break;
                case KeyEvent.VK_L:
                    gm.load();
                    break;
                default:
                    break;
            }
            setMainTankDir();
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
                case KeyEvent.VK_SPACE:
//                    gm.getMyTank().fire();
                    gm.getMyTank().handlerFireKey();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bR && !bU && !bD) {
                gm.getMyTank().setMoving(false);
            } else {
                gm.getMyTank().setMoving(true);
            }
            if (bL) {
                gm.getMyTank().setDir(Direction.LEFT);
            }
            if (bU) {
                gm.getMyTank().setDir(Direction.UP);
            }
            if (bR) {
                gm.getMyTank().setDir(Direction.RIGHT);
            }
            if (bD) {
                gm.getMyTank().setDir(Direction.DOWN);
            }
        }
    }
}
