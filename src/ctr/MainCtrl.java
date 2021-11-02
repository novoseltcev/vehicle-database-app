package ctr;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;
import utils.Command;
import view.AutoTestMenu;
import view.BaseMenu;
import view.DBMenu;
import view.MainMenu;

public class MainCtrl extends BaseCtrl {
    private final User user;

    public MainCtrl(BaseMenu menu, User user) throws InterruptedException, IOException {
        super(menu);
        this.user = user;
        setLogger();
        this.welcome();
    }
    
    protected void chooseCMD(Command command) throws InterruptedException {
        int cmd = super.chooseCMD(command, 100); // TODO
        if (cmd == 1) {

            runDB();
        } else {
            if (user.isSudoMode()) {
                chooseSudoCMD(cmd);
            } else {
                logger.log(Level.WARNING, "Invalid command");
                menu.errorCommand(String.valueOf(cmd));
            }
        }
    }
    
    private void chooseSudoCMD(int cmd) {
    	switch(cmd) {
            case (2) -> {
                try {
                    switchDebug();
                    ((MainMenu) menu).showDebugStatus(user.isDebug());
                } catch (IOException ignored) {}
            }

            case (3) -> runAutoTest();
            default -> {
                menu.errorCommand(String.valueOf(cmd));
                logger.log(Level.WARNING, "Invalid command");
            }
    	}
    }
    
    private void setLogger() throws IOException {
    	logger = Logger.getLogger(this.getClass().getName());
    	logger.setUseParentHandlers(false);
    	logger.setLevel(
    		user.isDebug() ? Level.ALL : Level.INFO
    	);
		Handler handler = new FileHandler("log", true);
	    logger.addHandler(handler);
    	
	}

    private void welcome() throws InterruptedException {
    	logger.log(Level.INFO, "User " + user.getName() + " start application");
        ((MainMenu)menu).welcome(user.getName());
        enterPassword();
        ((MainMenu)menu).userData(user);
        if (user.isTests()) {
        	runAutoTest();
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
    
    private void switchDebug() throws IOException{
    	if (!user.setDebug(enteredPassword, !user.isDebug())) {
    		throw new IOException("");
    	}
    }

    private void runAutoTest(){
        AutoTestMenu autoTestMenu = new AutoTestMenu();
        AutoTestCtrl autoTestCtrl = new AutoTestCtrl(autoTestMenu);
        try {
            while (true) {
                autoTestCtrl.run();
            }
        } catch (InterruptedException ignored) {}
    }
}
