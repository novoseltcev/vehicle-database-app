package view;

import model.User;
import model.vehicle.Vehicle;

import java.util.List;
import java.util.Properties;

public class MainMenu extends BaseMenu {
    public MainMenu(Properties langConfig) {
        langData = langConfig;
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

    public void show(boolean isSudo) {
        String[] msgList =new String[] {
            langData.getProperty("EXIT_CMD"),
            langData.getProperty("DB_CMD"),
            langData.getProperty("TESTS_CMD"),
        };
        display_ln("\n---------------------------");
        display_ln("\t\t" + title);
        display_ln("---------------------------");
        for (int i = 0; i <msgList.length; ++i) {
            if (i > 1 && !isSudo)
                break;
            display_ln(String.format(msgList[i], i));
        }
        display(langData.getProperty("ENTER_CMD"));
    }
}
