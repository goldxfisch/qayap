package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    private final Properties configProperties = new Properties();

    private ConfigProperties() {
        try {
            InputStream inputStream = ConfigProperties.class.getClassLoader().getResourceAsStream("Application");
            configProperties.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
            log.debug(" configProperties Error [{}]", e.getMessage());
        }
    }

    private static class Singleton {
        private static final ConfigProperties INSTANCE = new ConfigProperties();
    }

    public static ConfigProperties getInstance() {
        return Singleton.INSTANCE;
    }

    public String getProperty(String key) {
        return configProperties.getProperty(key);
    }

    private final static Logger log = LoggerFactory.getLogger(ConfigProperties.class);

    public static void main(String... cmdArgs) {
        log.info("Hello Information ");
        log.debug("Hello Debug");
    }
}
