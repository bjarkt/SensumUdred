import DAL.ConfigManager;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigManagerTest {

    @Test
    public void PropertiesTest() {
        Properties properties = ConfigManager.getInstance().getProperties();

        assertEquals("1234", properties.get("api-key"));
    }
}