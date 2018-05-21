package DAL;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static ConfigManager INSTANCE;
    private String filename;
    private Properties properties;

    private ConfigManager(String filename) {
        this.filename = filename;
    }

    public static ConfigManager getInstance() {
        if (INSTANCE == null) INSTANCE = new ConfigManager("config.properties");
        return INSTANCE;
    }

    /**
     * Read from the properties file, and return a properties object
     * @return properties object, with properties from the file
     */
    public Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
