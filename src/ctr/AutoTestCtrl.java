package ctr;

import model.AutoTest;
import view.AutoTestMenu;
import view.BaseMenu;

import java.util.ArrayList;
import java.util.List;

public class AutoTestCtrl extends BaseCtrl{
    AutoTestCtrl(BaseMenu menu) {
        super(menu);
    }

    public void run() {
        List<AutoTest> result = calc();
        ((AutoTestMenu)menu).show(result);
    }

    private List<AutoTest> calc() {
        List<AutoTest> autoTests = new ArrayList<>();
        for (int i = 100; i < 1000000; i *= 10){
            autoTests.add(new AutoTest(i));
        } return autoTests;
    }
}
