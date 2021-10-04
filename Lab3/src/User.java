import java.util.Properties;

public class User {
    private String _name;
    private String _password;
    private final String _mode;
    private boolean _debug;
    private boolean _tests;
    private final Properties _properties;

    User(Properties properties) {
        _properties =properties;
        _name = _properties.getProperty("USER_NAME");
        _password = _properties.getProperty("USER_PASSWORD");
        _mode = _properties.getProperty("USER_NAME");
        _debug = _properties.getProperty("DEBUG").equals("1");
        _tests = _properties.getProperty("TESTS").equals("1");
    }

    public String getName() { return _name; }

    public boolean checkPassword(String value) {
        return _password.equals(value);
    }

    public String getMode() { return _mode; }

    public boolean isDebug() { return _debug; }

    public boolean isTests() { return _tests; }

    public boolean changeName(String currentPassword, String newName) {
        if (checkPassword(currentPassword)) {
            return false;
        }
        _name = newName;
        _properties.setProperty("USER_NAME", _name);
        return true;
    }

    public boolean changePassword(String currentPassword, String newPassword) {
        if (checkPassword(currentPassword)) {
            return false;
        }
        _password = newPassword;
        _properties.setProperty("USER_PASSWORD", _password);
        return true;
    }

    public boolean changeDebug(String currentPassword, boolean value) {
        if (checkPassword(currentPassword)) {
            return false;
        }
        _debug = value;
        _properties.setProperty("USER_PASSWORD", _debug ? "1" : "0");
        return true;
    }

    public boolean changeTests(String currentPassword, boolean value) {
        if (checkPassword(currentPassword)) {
            return false;
        }
        _tests = value;
        _properties.setProperty("USER_PASSWORD", _tests ? "1" : "0");
        return true;
    }
}
