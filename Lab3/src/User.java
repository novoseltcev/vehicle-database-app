import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class User {
    private static final Map<String, String> LANGUAGE_CONFIG = new HashMap<>();
    static {
        LANGUAGE_CONFIG.put("rus", "russian.ini");
        LANGUAGE_CONFIG.put("eng", "english.ini");
        LANGUAGE_CONFIG.put("sp", "spain.ini");
    }
    
    private static final String BASE_CONFIG = "config.ini";
    private String _name;
    private String _password;
    private String _language;
    private final String _mode;
    private boolean _debug;
    private boolean _tests;
    private final Properties _properties;

    User() throws IOException {
        _properties = PropertyStreamer.read(BASE_CONFIG);
        _name = _properties.getProperty("USER_NAME");
        _password = _properties.getProperty("USER_PASSWORD");
        _language = _properties.getProperty("LANG");
        _mode = _properties.getProperty("MODE");
        _debug = Boolean.parseBoolean(_properties.getProperty("DEBUG"));
        _tests = Boolean.parseBoolean(_properties.getProperty("TESTS"));
    }

    public String getName() {
        return _name;
    }

    public boolean checkPassword(String value) {
        return _password.equals(value);
    }

    public String getLanguage() {
        return _language;
    }

    public boolean isSudoMode() {
        return _mode.equalsIgnoreCase("root");
    }

    public boolean isDebug() {
        return _debug;
    }

    public boolean isTests() {
        return _tests;
    }

    public boolean setName(String currentPassword, String newName) {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        _name = newName;
        _properties.setProperty("USER_NAME", _name);
        return true;
    }

    public boolean setPassword(String currentPassword, String newPassword) {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        _password = newPassword;
        _properties.setProperty("USER_PASSWORD", _password);
        return true;
    }

    public boolean setDebug(String currentPassword, boolean value) {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        _debug = value;
        _properties.setProperty("USER_PASSWORD", String.valueOf(_debug));
        return true;
    }

    public boolean setTests(String currentPassword, boolean value) {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        _tests = value;
        _properties.setProperty("USER_PASSWORD", String.valueOf(_tests));
        return true;
    }

    public boolean setLanguage(String value) {
        if (!LANGUAGE_CONFIG.containsKey(value)) {
            return false;
        }
        _language = value;
        _properties.setProperty("LANG", _language);
        return true;
    }
    
    private boolean dumpProprieties() throws IOException {
        return PropertyStreamer.write(BASE_CONFIG, _properties);
    }

    public Properties getLangData() throws IOException{
        Path langConfig = Path.of("lang", LANGUAGE_CONFIG.get(_language));
        return PropertyStreamer.read(langConfig.toString());
    }

    @Override
    public String toString() {
        return  "User {" +
                "name='" + getName() + '\'' +
                ", password='*******'" +
                ", lang=" + getLanguage() +
                ", sudo_mode=" + isSudoMode() + (
                        isSudoMode() ?
                            ", debug=" + isDebug() +
                            ", tests=" + isTests()
                        : ""
                ) + '}';
    }
}
