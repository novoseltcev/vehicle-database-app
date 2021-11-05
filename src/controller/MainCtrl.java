package controller;

import model.User;
import utils.Command;
import view.AutoTestMenu;
import view.BaseMenu;
import view.DBMenu;
import view.MainMenu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainCtrl extends BaseCtrl {
    private final User user;

    public MainCtrl(BaseMenu menu, User user, Logger logger) throws Exception {
        super(menu);
        BaseCtrl.logger = logger;

        this.user = user;
        this.welcome();
    }

    @Override
    protected void call(Command command) throws Exception {
        super.call(command, user.isSudoMode() ? 3 : 1); // TODO
        int cmd = command.getValue();
        switch (cmd) {
            case 1 -> runSubController(DBCtrl.class, DBMenu.class);
            case 2 -> switchDebug();
            case 3 -> runSubController(AutoTestCtrl.class, AutoTestMenu.class);
        }
    }
//            else {
//                logger.log(Level.WARNING, "Invalid command");
//                menu.errorCommand(String.valueOf(cmd));
//            }

    private void welcome() throws Exception {
    	logger.log(Level.INFO, "User " + user.getName() + " start application");
        ((MainMenu)menu).welcome(user.getName());
        enterPassword();
//        ((MainMenu)menu).userData(user);
        if (user.isTests()) {
            runSubController(AutoTestCtrl.class, AutoTestMenu.class);
        }
    }

    private void enterPassword() throws InterruptedException {
        int attempts = 3;
        while (attempts != 0) {
            String password = scanner.nextLine();
            if (user.checkPassword(password)) {
               enteredPassword = password;
               ((MainMenu)menu).validPassword();
               break;
            } attempts--;
            ((MainMenu)menu).invalidPassword(attempts);
        }
        if (attempts <= 0)
            throw new InterruptedException();
    }

    @Deprecated
    private void switchDebug() throws IOException {  // TODO
    	user.setDebug(enteredPassword, !user.isDebug());
        ((MainMenu) menu).showDebugStatus(user.isDebug());
    }
}
