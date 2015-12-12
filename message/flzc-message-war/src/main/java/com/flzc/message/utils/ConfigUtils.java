/**
 * 
 */
package com.flzc.message.utils;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 *
 * @ClassName: ConfigUtils 
 * @Description: TODO
 * @author bing.xiao
 * @date 2015-9-6 下午5:57:29 
 *  
 */
public class ConfigUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigUtils.class);

    public static final String  CONFIG_FILE_NAME = "config/config.properties";

    private static Properties PROPS  = new Properties();
    
    static {
        try {
            PROPS.load(ConfigUtils.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (IOException e) {
        	LOGGER.error("FAILED load properties from " + CONFIG_FILE_NAME, e);
            throw new RuntimeException(e);
        }
    }

    private ConfigUtils() {
        //
    }

    public static String getProperty(String name) {
        return PROPS.getProperty(name);
    }

    public static void main(String[] args) {
        System.out.println(getProperty("支付"));
    }
}
