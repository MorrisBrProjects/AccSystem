package de.morrisbr.wolfscripthubserver.config;

import com.github.jsixface.YamlConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WebConfig {

    private static String mongodb, username, password = "";
    private static String defaultKeyType = "ONE_DAY";
    private static int port = 100;

    public static void load() {
        FileInputStream resource = null;
        try {
            resource = new FileInputStream("config.yml"); //resources/config/
            YamlConfig config = YamlConfig.load(resource);
            mongodb = config.getString("mongodb");
            username = config.getString("username");
            password = config.getString("password");
            defaultKeyType = config.getString("defaultKeyType");
            port = config.getInt("port");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String getMongodb() {
        return mongodb;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }


    public static int getPort() {
        return port;
    }
}
