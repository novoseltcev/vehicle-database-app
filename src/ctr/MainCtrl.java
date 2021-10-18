package ctr;

import model.User;
import utils.Command;
import view.AutoTestMenu;
import view.BaseMenu;
import view.DBMenu;
import view.MainMenu;

public class MainCtrl extends BaseCtrl {
    private final User user;

    public MainCtrl(BaseMenu menu, User user) throws InterruptedException {
        super(menu);
        this.user = user;
        this.welcome();
    }

    @Override
    public void run() throws InterruptedException {
        ((MainMenu)menu).show(user.isSudoMode());
        super.run();
    }

    @Override
    protected void chooseCMD(Command command) throws InterruptedException {
        super.chooseCMD(command);
        int cmd = command.getValue();

        if (cmd == 1) {
            runDB();
        } else if (cmd == 2 && user.isSudoMode()){
            runAutoTest();
        } else {
            menu.errorCommand(String.valueOf(cmd));
        }
    }

    private void welcome() throws InterruptedException {
        ((MainMenu)menu).welcome(user.getName());
        enterPassword();
        //((MainMenu)menu).userData(user);
    }

    private void enterPassword() throws InterruptedException {
        int attempts = 3;
        while (attempts != 0) {
            String password = scanner.nextLine();
            if (user.checkPassword(password)) {
                this.enteredPassword =  password;
                ((MainMenu)menu).validPassword();
                break;
            } attempts--;
            ((MainMenu)menu).invalidPassword(attempts);
        }
        if (attempts <= 0)
            throw new InterruptedException("-1");
    }

    private void runDB() {
        try {
            DBMenu dbMenu = new DBMenu();
            DBCtrl dbCtrl = new DBCtrl(dbMenu);
            while (true) {
                dbCtrl.run();
            }
        } catch (InterruptedException ignored) {}
    }

    private void runAutoTest() {
        AutoTestMenu autoTestMenu = new AutoTestMenu();
        AutoTestCtrl autoTestCtrl = new AutoTestCtrl(autoTestMenu);
        autoTestCtrl.run();
    }
}
