package ctr;

import utils.Command;
import view.BaseMenu;

import java.util.Scanner;
import java.util.logging.Logger;

public abstract class BaseCtrl {
	public static Logger logger;
    protected static Scanner scanner;
    protected static String enteredPassword;
    protected BaseMenu menu;

    public BaseCtrl(BaseMenu menu) {
        scanner = new Scanner(System.in);
        this.menu = menu;
    }

    public void run() throws InterruptedException{
        menu.show();
        chooseCMD(new Command(menu, scanner));
    }

    protected int chooseCMD(Command command, int lastCMD) throws InterruptedException {
        int cmd = command.getValue();
        if (0 <= cmd && cmd <= lastCMD) {
            if (command.getValue() == 0) {
                throw new InterruptedException("0");
            }
            return cmd;
        }
        menu.errorCommand(String.valueOf(cmd));
        return -1;
    }
    
    protected abstract void chooseCMD(Command command) throws InterruptedException;
}
