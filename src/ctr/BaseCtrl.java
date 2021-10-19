package ctr;

import utils.Command;
import view.BaseMenu;

import java.util.Scanner;

public class BaseCtrl {
    protected Scanner scanner;
    protected String enteredPassword;
    protected BaseMenu menu;

    public BaseCtrl(BaseMenu menu) {
        scanner = new Scanner(System.in);
        this.menu = menu;
    }

    public void run() throws InterruptedException{
        chooseCMD(new Command(menu, scanner));
    }

    protected void chooseCMD(Command cmd) throws InterruptedException {
        if (cmd.getValue() == 0) {
            throw new InterruptedException("0");
        }
    }
}
