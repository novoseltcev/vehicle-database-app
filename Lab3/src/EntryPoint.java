import java.util.Properties;


public class EntryPoint {
    static final String baseConfig = "config.ini";

    public static void main(String[] args) {
        PropertyReader propReader = new PropertyReader();
        Properties baseProperties = propReader.load(baseConfig);
        try {
            User user = new User(baseProperties);
            Menu view = new Menu();
            Main controller = new Main(view, user);
            Boolean run_flag = true;
            while (run_flag) {
                run_flag = controller.run();
            }
        } catch (Exception ignored) {}  // TODO
    }
}