package com.tony.tank;

import com.tony.tank.facade.GameModel;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/14 15:46
 * Description:
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int initTankCount = Integer.parseInt((String)PropertyManager.getInstance().get("initTankCount"));
        GameModel gameModel = GameModel.getInstance();
        for (int i = 0; i < initTankCount; i++) {
            gameModel.enemyTanks.add(new Tank(50 + i*80,200,Direction.DOWN,Group.BAD,gameModel));
        }

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
