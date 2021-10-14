import utils.PropertyStreamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class User {
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
        if (!isSudoMode())
            _debug = _tests = false;
    }

    private void dumpProprieties() throws IOException {
        PropertyStreamer.write(BASE_CONFIG, _properties);
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

    public boolean setName(String currentPassword, String newName) throws IOException {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        _name = newName;
        _properties.setProperty("USER_NAME", _name);
        dumpProprieties();
        return true;
    }

    public boolean setPassword(String currentPassword, String newPassword) throws IOException {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        _password = newPassword;
        _properties.setProperty("USER_PASSWORD", _password);
        dumpProprieties();
        return true;
    }

    public boolean setDebug(String currentPassword, boolean value) throws IOException {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        _debug = value;
        _properties.setProperty("USER_PASSWORD", String.valueOf(_debug));
        dumpProprieties();
        return true;
    }

    public boolean setTests(String currentPassword, boolean value) throws IOException {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        _tests = value;
        _properties.setProperty("USER_PASSWORD", String.valueOf(_tests));
        dumpProprieties();
        return true;
    }

    public boolean setLanguage(String language) throws IOException {
        Path langConfig = Path.of("lang", language + ".ini");
        if (!new File(langConfig.toString()).exists()) {
            return false;
        }
        _language = language;
        _properties.setProperty("LANG", _language);
        dumpProprieties();
        return true;
    }

    public Properties getLangData() throws IOException{
        Path langConfig = Path.of("lang", _language + ".ini");
        return PropertyStreamer.read(langConfig.toString());
    }

    @Override
    public String toString() {
        return  "User {" +
                "\n\tname='" + getName() + '\'' +
                ", \n\tpassword='*******'" +
                ", \n\tlang=" + getLanguage() +
                ", \n\tsudo_mode=" + isSudoMode() + (
                        isSudoMode() ?
                            ", \n\tdebug=" + isDebug() +
                            ", \n\ttests=" + isTests()
                        : ""
                ) + "\n}";
    }
}
