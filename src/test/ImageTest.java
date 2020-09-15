package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/15 15:12
 * Description:
 */
public class ImageTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        try {
            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader()
                    .getResourceAsStream("images/tankD.gif"));
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}