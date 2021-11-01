package ctr;

import model.AutoTest;
import utils.Command;
import view.AutoTestMenu;
import view.BaseMenu;

import java.util.ArrayList;
import java.util.List;

public class AutoTestCtrl extends BaseCtrl{
    AutoTestCtrl(BaseMenu menu) {
        super(menu);
    }

    @Override
    public void run() {
        List<AutoTest> result = calculate();
        ((AutoTestMenu)menu).loadData(result);
        menu.show();
    }

    @Override
    protected void chooseCMD(Command command) throws InterruptedException {
        chooseCMD(command, 1);
        // TODO { 0: EXIT, 1: RESTART }
    }

    private List<AutoTest> calculate() {
        List<AutoTest> autoTests = new ArrayList<>();
        for (int i = 100; i < 1000000; i *= 10){
            autoTests.add(new AutoTest(i));
        } return autoTests;
    }
}
