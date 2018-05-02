package DAL;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
    private String filename;

    public ConfigManager(String filename) {
        this.filename = filename;
    }

    /**
     * Read from the properties file, and return a properties object
     * @return properties object, with properties from the file
     */
    public Properties getProperties() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(new File(getClass().getClassLoader().getResource(filename).getFile()))) {
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
