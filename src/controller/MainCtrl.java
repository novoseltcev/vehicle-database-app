package controller;

import utils.Command;
import view.AutoTestMenu;
import view.BaseMenu;
import view.DBMenu;
import view.MainMenu;

import java.io.IOException;


public class MainCtrl extends BaseCtrl {
    public MainCtrl(BaseMenu menu) {
        super(menu);
    }

    @Override
    protected void call(Command command) throws Exception {
        super.call(command, user.isSudoMode() ? 3 : 1);
        int cmd = command.getValue();
        switch (cmd) {
            case 1 -> runSubController(DBCtrl.class, DBMenu.class);
            case 2 -> switchDebug();
            case 3 -> runSubController(AutoTestCtrl.class, AutoTestMenu.class);
        }
    }

    @Deprecated
    private void switchDebug() throws IOException {  // TODO
        logger.finer("Start method: \"switchDebug\"");
    	user.setDebug(enteredPassword, !user.isDebug());
        ((MainMenu) menu).showDebugStatus(user.isDebug());
    }
}
