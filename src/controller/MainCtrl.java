package controller;

import java.io.IOException;
import java.util.InputMismatchException;
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

    @Override
    protected void call(Command command) throws InputMismatchException, InterruptedException {
        super.call(command, user.isSudoMode() ? 3 : 1); // TODO
        int cmd = command.getValue();
        switch (cmd) {
            case 1 -> runDB();
            case 2 -> switchDebug();
//                           ((MainMenu) menu).showDebugStatus(user.isDebug());
            case 3 -> runAutoTest();
        }
    }
//            else {
//                logger.log(Level.WARNING, "Invalid command");
//                menu.errorCommand(String.valueOf(cmd));
//            }
    
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
            throw new InterruptedException();
    }

    private void runDB() {
        DBMenu dbMenu = new DBMenu();
        DBCtrl dbCtrl = new DBCtrl(dbMenu);
        dbCtrl.loop();
    }
    
    private void switchDebug() throws InterruptedException {  // TODO
    	if (!user.setDebug(enteredPassword, !user.isDebug())) {
    		throw new InterruptedException();
    	}
    }

    private void runAutoTest(){
        AutoTestMenu autoTestMenu = new AutoTestMenu();
        AutoTestCtrl autoTestCtrl = new AutoTestCtrl(autoTestMenu);
        autoTestCtrl.loop();
    }
}
