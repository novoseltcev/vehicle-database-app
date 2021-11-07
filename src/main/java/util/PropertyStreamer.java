package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class PropertyStreamer {
    public static Properties read(Path path) throws IOException {
        Properties properties = new Properties();
        File config = path.toFile();
        if (!config.exists()) {
            throw new FileNotFoundException(config.getAbsolutePath());
        }
        FileInputStream stream = new FileInputStream(config);
        properties.load(stream);
        stream.close();
        return properties;
    }
    
    public static void write(Path path, Properties properties) throws IOException {
        File config = path.toFile();
        FileOutputStream stream = new FileOutputStream(config);
        properties.store(stream, null);
        stream.close();
    }
}
