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
    }
}
