package ctr;

import utils.Command;
import view.BaseMenu;

import java.util.InputMismatchException;
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

    public void run() {
        boolean isRunning = true;
        boolean isError   = false;
        Command command = new Command((scanner));

        while (isRunning) {
            if (isError) {
                menu.show();
            }

            isError = false;
            try {
                command.getInt();
                chooseCMD(command);
            } catch (InputMismatchException e) {
                isError = true;
                menu.errorCommand(String.valueOf(command.getValue()));
            } catch (NumberFormatException e) {
                isError = true;
                menu.invalidInt(command.getValue());
            } catch (InterruptedException e) {
                isRunning = false;
            }
        }
    }

    protected void chooseCMD(Command command, int lastCMD) throws InputMismatchException, InterruptedException {
        int cmd = command.getValue();
        if  (cmd == 0) {
            throw new InterruptedException();
        }

        if (cmd < 1 || lastCMD < cmd) {
            throw new InputMismatchException();
        }
    }
    
    protected abstract void chooseCMD(Command command) throws InputMismatchException, InterruptedException;
}
