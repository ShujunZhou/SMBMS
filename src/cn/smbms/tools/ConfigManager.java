package cn.smbms.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by alren on 16-8-18.
 */
//读取配置文件的工具类---单例模式
public class ConfigManager {
    private static ConfigManager configManager = null;
    private static Properties properties = null;

    //私用构造器--读取配置文件
    private ConfigManager() {
        String configFile = "database.properties";
        properties = new Properties();
        //通过classPath找资源.在当前类所在包的根目录下寻找configFile
        InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
        try {
            properties.load(is);
            is.close(); //加载完成后，关闭流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //需要考略线程安全问题,加双重if判断可以减少判断锁的次数,提高效率
    public static ConfigManager getInstance() {
        if (configManager == null) {
            synchronized (ConfigManager.class) {
                if (configManager == null) {
                    configManager = new ConfigManager();
                    return configManager;
                }
            }
            return configManager;
        }

        return configManager;
    }

    //通过给定键值获取value
    public String getValue(String key) {
        return properties.getProperty(key);
    }

}
