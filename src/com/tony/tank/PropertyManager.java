package com.tony.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/16 15:27
 * Description:
 */
public class PropertyManager {
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyManager.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String name) {
        return props.get(name);
    }
}
