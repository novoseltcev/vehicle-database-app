package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyStreamer {
    public static Properties read(String filePath) throws IOException {
        Properties properties = new Properties();
        File config = new File(filePath);
        if (!config.exists()) {
            throw new FileNotFoundException(config.getAbsolutePath());
        } properties.load(new FileInputStream(config));
        return properties;
    }
    
    public static void write(String filePath, Properties properties) throws IOException {
        File config = new File(filePath);
        properties.store(new FileOutputStream(config), null);
    }
}
