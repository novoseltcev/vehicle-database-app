package controller;

import model.AutoTest;
import utils.Command;
import view.AutoTestMenu;
import view.BaseMenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

public class AutoTestCtrl extends BaseCtrl{
    AutoTestCtrl(BaseMenu menu) {
        super(menu);
    }

    @Override
    public void loop() throws Exception {  // TODO
        ((AutoTestMenu)menu).Wait();
        List<AutoTest> testsForArrayLists  = generateTests(ArrayList.class.arrayType());
        List<AutoTest> testsForLinkedLists = generateTests(LinkedList.class.arrayType());
        ((AutoTestMenu)menu).loadData(testsForArrayLists);
        super.loop();
    }

    @Override
    protected void call(Command command) throws InputMismatchException, InterruptedException {
        call(command, 1);
    }


    @Deprecated
    private List<AutoTest> generateTests(Class<?> listClass) {
        List<AutoTest> result = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            result.add(new AutoTest(listClass, (int) Math.pow(10, i + 1)));
        }
        return result;
    }

}
