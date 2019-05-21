package fb.logs.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppConfig {
	
	private static final Logger logger = LogManager.getLogger(AppConfig.class);
	private static Properties properties;
	
	public static String getConfig(String key) {
		if (properties == null) {
			loadConfig();
		}
		return properties.getProperty(key);
	}

	private static void loadConfig() {
		synchronized (AppConfig.class) {
			if (properties == null) {
				properties = new Properties();
				String configFile = System.getProperty("configFile");
				if (configFile == null) {
					throw new RuntimeException("configFile system property not set");
				}
				try {
					InputStream input = new FileInputStream(configFile);
					properties.load(input);
					logger.info("Config file loaded");
				} catch (IOException e) {
					throw new RuntimeException("Could not load configFile");
				}
			}
		}
	}
	
}
