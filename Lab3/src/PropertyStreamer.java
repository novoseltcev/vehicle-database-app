import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyStreamer {
    public static Properties read(String filePath) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        File config = new File(filePath);
        //System.l.print("File: " + config.getAbsolutePath());
        if (!config.exists()) {
            throw new FileNotFoundException();
        }  // TODO
        properties.load(new FileInputStream(config));
        return properties;
    }
    
    public static boolean write(String filePath, Properties properties) throws FileNotFoundException, IOException {
        File config = new File(filePath);
        properties.store(new FileOutputStream(config), null);
        return true;
    }
}
