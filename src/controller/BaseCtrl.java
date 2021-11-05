package controller;

import utils.Command;
import view.BaseMenu;

import java.lang.reflect.Constructor;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public abstract class BaseCtrl {
	public static Logger logger;
    protected static Scanner scanner = new Scanner(System.in);
    protected static String enteredPassword;
    protected BaseMenu menu;

    public BaseCtrl(BaseMenu menu) throws Exception{
        this.menu = menu;
    }

    public void loop() throws Exception {
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
                isError = true;
                menu.invalidInt(command.getValue());
            } catch (NumberFormatException e) {
                isError = true;
                menu.errorCommand(String.valueOf(command.getValue()));
            } catch (InterruptedException e) {
                isRunning = false;
            }
        }
    }

    public void runSubController(Class<? extends BaseCtrl> controller, Class<? extends BaseMenu> menu) throws Exception {
        Constructor<? extends BaseMenu> menuConstructor = menu.getDeclaredConstructor();
        Constructor<? extends BaseCtrl> ctrlConstructor = controller.getDeclaredConstructor(BaseMenu.class);

        BaseMenu subMenu = menuConstructor.newInstance();
        BaseCtrl subCtrl = ctrlConstructor.newInstance(subMenu);

        subCtrl.loop();
    }

    protected void call(Command command, int lastCMD) throws InputMismatchException, InterruptedException {
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
        return getString().toLowerCase();
    }

    protected int getInt() throws NumberFormatException {
        String buffer = scanner.nextLine().toLowerCase();
        return Integer.parseInt(buffer);
    }
}
