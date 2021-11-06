package view;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Properties;

public abstract class BaseMenu{
    protected static Properties langData;
    protected String title = this.getClass().getName().split("\\.")[this.getClass().getName().split("\\.").length - 1].split("Menu")[0] + " Menu";
    protected static PrintWriter writer = new PrintWriter(System.out, true);
    protected static HashMap<Integer, String> digits = new HashMap<>() {{
        put(0, "\uFF10");
        put(1, "\uFF11");
        put(2, "\uFF12");
        put(3, "\uFF13");
        put(4, "\uFF14");
        put(5, "\uFF15");
        put(6, "\uFF16");
        put(7, "\uFF17");
        put(8, "\uFF18");
        put(9, "\uFF19");
    }};

    protected void row(String msg) {
        System.out.print(msg);
    }

    protected void display_ln(String msg) {
        writer.println(msg);
    }

    protected void success(String msg) {
        display_ln("\u2705 " + msg);
    }

    protected void error(String msg) {
        display_ln("\u274C " + msg);
    }

    protected void critical(String msg) {
        display_ln("\u2620 " + msg);
    }

    public void errorCommand(String command) {
        String msg = String.format(langData.getProperty("INVALID_CMD"), command);
        error(msg);
    }

    public void invalidInt(int integer) {
        String msg = String.format(langData.getProperty("INVALID_INT"), integer);
        error(msg);
    }

    public abstract void show();

    public void showSeparator() {
        display_ln("-".repeat(25));
    }

    public void showTitle() {
        showSeparator();
        display_ln("\u27A4 " + title.strip());
        showSeparator();
    }

    public void clear() {
        writer.print("\033[H\033[2J");
        writer.flush();
    }
}
