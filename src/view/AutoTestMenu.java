package view;

import model.AutoTest;

import java.util.List;

public class AutoTestMenu extends BaseMenu {
    private List<AutoTest> data;
    public void loadData(List<AutoTest> autoTests) {
        data = autoTests;
    }
    public void show() {
        showTitle();
        for (AutoTest test: data) {
            display_ln('\n' + test.toString());
        }


        String[] msgList =new String[] {
                langData.getProperty("BACK_CMD"),
                langData.getProperty("RESTART_CMD")
        };

        for (int i = 0; i < msgList.length; ++i)
            display_ln(String.format(msgList[i], i));

        display(langData.getProperty("ENTER_CMD"));
    }

    public void Wait() {
        display_ln(langData.getProperty("WAIT"));
    }
}
