package controller;

import model.User;
import utils.Command;
import view.BaseMenu;

import java.lang.reflect.Constructor;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public abstract class BaseCtrl {
    protected static User user;
	public static Logger logger;
    protected static Scanner scanner = new Scanner(System.in);
    protected static String enteredPassword;
    protected BaseMenu menu;

    public BaseCtrl(BaseMenu menu) {
        this.menu = menu;
    }

    public void loop() throws Exception {
        logger.finer("Start method: \"loop\"");
        boolean isRunning = true;
        boolean isError   = false;
        Command command = new Command((scanner));

        while (isRunning) {
            if (!isError) {
                menu.show();
            }

            isError = false;
            try {
                command.getInt();
                call(command);
            } catch (InputMismatchException e) {
                logger.warning("Invalid command: " + command.getValue());
                isError = true;
                menu.invalidInt(command.getValue());
            } catch (NumberFormatException e) {
                logger.warning("Invalid command: " + command.getError());
                isError = true;
                menu.errorCommand(String.valueOf(command.getValue()));
            } catch (InterruptedException e) {
                isRunning = false;
            }
        }
    }

    public void runSubController(Class<? extends BaseCtrl> controller, Class<? extends BaseMenu> menu) throws Exception {
        logger.finer("Start method: \"runSubController\"");
        Constructor<? extends BaseMenu> menuConstructor = menu.getDeclaredConstructor();
        Constructor<? extends BaseCtrl> ctrlConstructor = controller.getDeclaredConstructor(BaseMenu.class);

        BaseMenu subMenu = menuConstructor.newInstance();
        BaseCtrl subCtrl = ctrlConstructor.newInstance(subMenu);
        subCtrl.loop();
    }

    protected void call(Command command, int lastCMD) throws InputMismatchException, InterruptedException {
        logger.finer("Start method: \"call\"");
        int cmd = command.getValue();
        menu.clear();
        if  (cmd == 0) {
            throw new InterruptedException();
        }

        if (cmd < 1 || lastCMD < cmd) {
            throw new InputMismatchException();
        }

    }
    
    protected abstract void call(Command command) throws Exception;


    private String getString() {
        return scanner.nextLine().toLowerCase();
    }

    protected String getLowerString() {
        logger.finer("Start method: \"getLowerString\"");
        return getString().toLowerCase();
    }

    protected int getInt() throws NumberFormatException {
        logger.finer("Start method: \"getInt\"");
        String buffer = scanner.nextLine().toLowerCase();
        return Integer.parseInt(buffer);
    }
}
