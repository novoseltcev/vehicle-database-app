package controller;

import model.User;
import utils.Command;
import view.AutoTestMenu;
import view.BaseMenu;
import view.UserMenu;

import java.util.InputMismatchException;
import java.util.logging.Logger;

public class UserCtrl extends BaseCtrl{
    public UserCtrl(BaseMenu menu, User user, Logger logger) {
        super(menu);
        BaseCtrl.user = user;
        BaseCtrl.logger = logger;
    }

    public void welcome() throws Exception {
        ((UserMenu)menu).welcome(user.getName());
        enterPassword();
        if (user.isTests()) {
            runSubController(AutoTestCtrl.class, AutoTestMenu.class);
        }
    }

    private void enterPassword() throws InputMismatchException {
        int attempts = 3;
        while (attempts != 0) {
            String password = scanner.nextLine();
            if (user.checkPassword(password)) {
                enteredPassword = password;
                ((UserMenu)menu).validPassword();
                break;
            } attempts--;
            ((UserMenu)menu).invalidPassword(attempts);
        }
        if (attempts <= 0)
            throw new InputMismatchException();
    }

    @Override
    protected void call(Command command) throws Exception {
        call(command, 1);
    }
}
