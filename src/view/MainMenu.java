package view;

import model.User;

import java.util.Properties;

public class MainMenu extends BaseMenu {
    final boolean sudoPrivileges;
    public MainMenu(Properties langConfig, boolean isSudo) {
        langData = langConfig;
        sudoPrivileges = isSudo;
    }

    public void welcome(String username) {
        String msg = String.format(langData.getProperty("WELCOME"), username);
        display_ln(msg);
        display( langData.getProperty("INPUT_PASS") );
    }

    public void userData(User user) {
        display_ln( user.toString() );
    }

    public void invalidPassword(int attempts) {
        String dopMsg = (attempts > 0) ? String.format(langData.getProperty("LOST_ATTEMPTS"), attempts) : "";
        String msg = langData.getProperty("INVALID_PASS") + dopMsg;
        error(msg);
    }

    public void validPassword() {
        display_ln(langData.getProperty("VALID_PASS"));
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
        display(langData.getProperty("ENTER_CMD"));
    }
    
    public void showDebugStatus(boolean value) {
    	String msg = String.format(langData.getProperty("DEBUG_STATUS"), value);
    	display_ln(msg);
    }
}
