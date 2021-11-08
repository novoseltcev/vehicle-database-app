package model;

import util.PropertyStreamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class User {
    private final Path path;
    private String name;
    private String password;
    private String language;
    private final String mode;
    private boolean debug;
    private boolean tests;
    private final Properties properties;
    private HashMap<String, String> languageData = new HashMap<>();

    public User(Path path) throws IOException {
        this.path = path;
        properties = PropertyStreamer.read(path);
        name = properties.getProperty("NAME");
        password = properties.getProperty("PASSWORD");
        language = properties.getProperty("LANG");
        loadLanguageData();

        mode = properties.getProperty("MODE");
        debug = Boolean.parseBoolean(properties.getProperty("DEBUG"));
        tests = Boolean.parseBoolean(properties.getProperty("TESTS"));
    }

    private void dumpProprieties() throws IOException {
        PropertyStreamer.write(path, properties);
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

    public HashMap<String, String> getLanguageData() {
        return languageData;
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
        properties.setProperty("NAME", name);
        dumpProprieties();
        return true;
    }

    public boolean setPassword(String currentPassword, String newPassword) throws IOException {
        if (!checkPassword(currentPassword)) {
            return false;
        }
        password = newPassword;
        properties.setProperty("PASSWORD", password);
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
        Path langConfig = Path.of("" +
                "lang", language + ".ini");
        if (!new File(langConfig.toString()).exists()) {
            return false;
        }
        this.language = language;
        properties.setProperty("LANG", this.language);
        dumpProprieties();
        loadLanguageData();
        return true;
    }

    public void loadLanguageData() throws IOException {
        Path langConfig = Path.of("lang", language + ".ini");
        Properties langData = PropertyStreamer.read(langConfig);
        languageData.clear();
        for (Map.Entry<Object, Object> property : langData.entrySet()) {
            languageData.put( (String) property.getKey(), (String) property.getValue() );
        }
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
