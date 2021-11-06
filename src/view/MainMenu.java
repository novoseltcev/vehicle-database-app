package view;

public class MainMenu extends BaseMenu {
    final boolean sudoPrivileges;
    public MainMenu(boolean isSudo) {
        sudoPrivileges = isSudo;
    }

    public void show() {
        showTitle();
        String[] msgList =new String[] {
            langData.getProperty("EXIT_CMD"),
            langData.getProperty("DB_CMD"),
            langData.getProperty("DEBUG_CMD"),
            langData.getProperty("TESTS_CMD"),
        };
        for (int i = 0; i <msgList.length; ++i) {
            if (i > 1 && !sudoPrivileges) { break; }
            display_ln(String.format(msgList[i], i));
        }
        showSeparator();
        row(langData.getProperty("ENTER_CMD"));
    }
    
    public void showDebugStatus(boolean value) {
    	String msg = String.format(langData.getProperty("DEBUG_STATUS"), value);
    	success(msg);
    }
}
