import static org.junit.jupiter.api.Assertions.assertEquals;

import DAL.ConfigManager;
import org.junit.jupiter.api.Test;

import java.util.Properties;

public class ConfigManagerTest {

    @Test
    public void PropertiesTest() {
        ConfigManager configManager = new ConfigManager("config.properties");
        Properties properties = configManager.getProperties();

        assertEquals("1234", properties.get("api-key"));
    }
}