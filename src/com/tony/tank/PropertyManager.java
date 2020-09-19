package com.tony.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: Tony.Chen
 * Create Time : 2020/9/16 15:27
 * Description:
 */
public class PropertyManager {
    private PropertyManager() {
    }

    public Object get(String name) {
        return PropertyManagerHolder.props.get(name);
    }

    public static PropertyManager getInstance(){
        return PropertyManagerHolder.proper;
    }

    /**
     * 使用静态内部类实现懒加载功能，保证了Properties只有调用get方法时才会被jvm加载
     * 做到了用时加载，这样节省了内存空间
     */
    private static class PropertyManagerHolder {
        private static final Properties props = new Properties();
        private static final PropertyManager proper = new PropertyManager();

        static {
            try {
                props.load(PropertyManager.class.getClassLoader().getResourceAsStream("config"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(PropertyManager.getInstance().get("initTankCount"));
    }
}
