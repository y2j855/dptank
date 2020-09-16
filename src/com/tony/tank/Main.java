package com.tony.tank;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/14 15:46
 * Description:
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int initTankCount = Integer.parseInt((String)PropertyManager.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.enemyTanks.add(new Tank(50 + i*80,200,Direction.DOWN,Group.BAD,tankFrame));
        }

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
