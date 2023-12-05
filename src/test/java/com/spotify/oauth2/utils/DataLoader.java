package com.spotify.oauth2.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader() {
        properties = PropertyUtils.propertiesLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance() {
        if (dataLoader == null) {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getReadPlaylist() {
        String prop = properties.getProperty("read_playlist");
        if (prop != null) return prop;
        else throw new RuntimeException("Property read_playlist is not specified in the config.properties file");
    }

    public String getUpdatePlaylist() {
        String prop = properties.getProperty("update_playlist");
        if (prop != null) return prop;
        else throw new RuntimeException("Property update_playlist is not specified in the config.properties file");
    }
}
