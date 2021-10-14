package view;

import model.AutoTest;

import java.util.List;

public class AutoTestMenu extends BaseMenu {
    public void show(List<AutoTest> autoTests) {
//        clearStack();
        display_ln(langData.getProperty("AUTOTEST_TITLE"));
        for (AutoTest autoTest: autoTests) {
            display_ln('\n' + autoTest.toString());
        }
    }
}
