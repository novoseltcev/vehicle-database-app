import java.io.File;
import java.util.Properties;

public class PropertyReader {
    public Properties load(String filePath) {
        Properties properties = new Properties();
        try {
            File config = new File(filePath);
            if (!config.createNewFile()) {
//                setupDefaultProperties(properties);
            }
//            setupProperties(configPath, properties);
        } catch (Exception ignored) {}
        return properties;
    }
}
