package model;

import utils.PropertyStreamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class User {
    private static final String BASE_CONFIG = "config.ini";
    private String name;
    private String password;
    private String language;
    private final String mode;
    private boolean debug;
    private boolean tests;
    private final Properties properties;

    public User() throws IOException {
        properties = PropertyStreamer.read(BASE_CONFIG);
        name = properties.getProperty("USER_NAME");
        password = properties.getProperty("USER_PASSWORD");
        language = properties.getProperty("LANG");
        mode = properties.getProperty("MODE");
        debug = Boolean.parseBoolean(properties.getProperty("DEBUG"));
        tests = Boolean.parseBoolean(properties.getProperty("TESTS"));
        if (!isSudoMode())
            debug = tests = false;
    }

    private void dumpProprieties() throws IOException {
        PropertyStreamer.write(BASE_CONFIG, properties);
    }

    public String getName() {
        return name;
    }

    public boolean checkPassword(String value) {
        return password.equals(value);
    }

    public String getLanguage() {
        return language;
    }

    public boolean isSudoMode() {
        return mode.equalsIgnoreCase("root");
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isTests() {
        return tests;
    }

    public boolean setName(String currentPassword, String newName) throws IOException {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        name = newName;
        properties.setProperty("USER_NAME", name);
        dumpProprieties();
        return true;
    }

    public boolean setPassword(String currentPassword, String newPassword) throws IOException {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        password = newPassword;
        properties.setProperty("USER_PASSWORD", password);
        dumpProprieties();
        return true;
    }

    public boolean setDebug(String currentPassword, boolean value) throws IOException {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        debug = value;
        properties.setProperty("DEBUG", String.valueOf(debug));
        dumpProprieties();
        return true;
    }

    public boolean setTests(String currentPassword, boolean value) throws IOException {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        tests = value;
        properties.setProperty("TESTS", String.valueOf(tests));
        dumpProprieties();
        return true;
    }

    public boolean setLanguage(String language) throws IOException {
        Path langConfig = Path.of("lang", language + ".ini");
        if (!new File(langConfig.toString()).exists()) {
            return false;
        }
        this.language = language;
        properties.setProperty("LANG", this.language);
        dumpProprieties();
        return true;
    }

    public Properties getLangData() throws IOException{
        Path langConfig = Path.of("lang", language + ".ini");
        return PropertyStreamer.read(langConfig.toString());
    }

    @Override
    public String toString() {
        return  "model.User {" +
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
