package view;

import model.AutoTest;

import java.util.List;

public class AutoTestMenu extends BaseMenu {
    public void show() {
        showTitle();

        String[] msgList =new String[] {
                langData.getProperty("BACK_CMD"),
                langData.getProperty("RESTART_CMD")
        };

        for (int i = 0; i < msgList.length; ++i)
            display_ln(String.format(msgList[i], i));

        showSeparator();
        row(langData.getProperty("ENTER_CMD"));
    }

    public void Wait() {
        display_ln(langData.getProperty("WAIT"));
    }

    public void showData(List<AutoTest> data) {
        for (AutoTest test: data) {
            display_ln('\n' + test.toString());
        }
    }
}
