package com.tony.tank;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/14 15:46
 * Description:
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
