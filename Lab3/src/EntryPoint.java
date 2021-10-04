import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class EntryPoint {
    static final String baseConfig = "config.ini";
    static Map<String, String> languageConfig = new HashMap<>();

    static {
        languageConfig.put("rus", "russian.ini");
        languageConfig.put("eng", "english.ini");
        languageConfig.put("sp", "spain.ini");
    }

    public static void main(String[] args) {
        PropertyReader propReader = new PropertyReader();
        Properties baseProperties = propReader.load(baseConfig);
        try {
            Menu view = new Menu();
            Main controller = new Main(view, baseProperties);
            Boolean run_flag = true;
            while (run_flag) {
                run_flag = controller.run();
            }
        } catch (Exception ignored) {}  // TODO
    }
}