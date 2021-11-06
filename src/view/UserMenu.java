package view;

import java.util.Properties;

public class UserMenu extends BaseMenu {
    public UserMenu(Properties langConfig) {
        langData = langConfig;
    }

    public void welcome(String username) {
        clear();
        String msg = String.format(langData.getProperty("WELCOME"), username);
        display_ln(msg);
        row( langData.getProperty("INPUT_PASS") );
    }

    public void invalidPassword(int attempts) {
        String dopMsg = (attempts > 0) ? String.format(langData.getProperty("LOST_ATTEMPTS"), attempts) : "";
        String msg = langData.getProperty("INVALID_PASS") + dopMsg;
        error(msg);
    }

    public void validPassword() {
        success(langData.getProperty("VALID_PASS"));
    }


    @Override
    public void show() {
        showTitle();
    }
}
