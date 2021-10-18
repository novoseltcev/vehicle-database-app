package ctr;

import model.User;
import model.vehicle.Vehicle;
import view.AutoTestMenu;
import view.BaseMenu;
import view.DBMenu;
import view.MainMenu;

import java.util.List;

public class MainCtrl extends BaseCtrl {
    private final User user;
    private List<Vehicle> vehicles;

    public MainCtrl(BaseMenu menu, User user) throws InterruptedException {
        super(menu);
        this.user = user;
        this.menu = menu;
        this.welcome();
    }

    private void welcome() throws InterruptedException {
        ((MainMenu)menu).welcome(user.getName());
        enterPassword();
        ((MainMenu)menu).userData(user);
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

    public void run() throws InterruptedException {
        ((MainMenu)menu).show(user.isSudoMode());
        int cmd = enterInt();
        chooseCMD(cmd);
    }

    private void chooseCMD(int cmd) throws InterruptedException {
        switch (cmd) {
            case (0):
                throw new InterruptedException("0");

            case (1):
                runDB();
                break;

            default:
                if (user.isSudoMode())
                    chooseSudoCommand(cmd);
                else
                    menu.errorCommand(String.valueOf(cmd));
        }
    }

    private void chooseSudoCommand(int cmd){
        if (cmd == 3) {
            AutoTestMenu autoTestMenu = new AutoTestMenu();
            AutoTestCtrl autoTestCtrl = new AutoTestCtrl(autoTestMenu);
            autoTestCtrl.run();
        } else
            menu.errorCommand(String.valueOf(cmd));
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
}
