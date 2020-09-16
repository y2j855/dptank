package com.tony.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author: chenhao
 * @date: 2019-07-17
 * @description:
 */

public class ResourceManager {
    private ResourceManager(){}

    public static BufferedImage goodTankU, goodTankD, goodTankL, goodTankR;

    public static BufferedImage badTankU, badTankD, badTankL, badTankR;
    public static BufferedImage bulletU,bulletD,bulletL,bulletR;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {

            goodTankU = ImageIO.read(com.tony.tank.ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankD = ImageUtil.rotateImage(goodTankU,180);
            goodTankL = ImageUtil.rotateImage(goodTankU,-90);
            goodTankR = ImageUtil.rotateImage(goodTankU,90);

//            goodTankU2 = ImageIO.read(com.tony.tank.ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
//            goodTankD2 = ImageUtil.rotateImage(goodTankU2,180);
//            goodTankL2 = ImageUtil.rotateImage(goodTankU2,-90);
//            goodTankR2 = ImageUtil.rotateImage(goodTankU2,90);
//
//            goodTankU[0] = goodTankU1;
//            goodTankU[1] = goodTankU2;
//
//            goodTankD[0] = goodTankD1;
//            goodTankD[1] = goodTankD2;
//
//            goodTankL[0] = goodTankL1;
//            goodTankL[1] = goodTankL2;
//
//            goodTankR[0] = goodTankR1;
//            goodTankR[1] = goodTankR2;

            badTankU = ImageIO.read(com.tony.tank.ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankD = ImageUtil.rotateImage(badTankU,180);
            badTankL = ImageUtil.rotateImage(badTankU,-90);
            badTankR = ImageUtil.rotateImage(badTankU,90);

            bulletU = ImageIO.read(com.tony.tank.ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletD = ImageUtil.rotateImage(bulletU,180);
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);

            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(com.tony.tank.ResourceManager.class.getClassLoader().getResourceAsStream("images/e" + (i+1) +".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
