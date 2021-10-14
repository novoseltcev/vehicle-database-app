package ctr;

import view.BaseMenu;

import java.util.Scanner;

public class BaseCtrl {
    protected Scanner scanner;
    protected String enteredPassword;
    protected BaseMenu menu;

    BaseCtrl(BaseMenu menu) {
        scanner = new Scanner(System.in);
        this.menu = menu;
    }

    protected int enterInt() {
        int result = -1;
        while (true) {
            String buffer = scanner.nextLine();
            try {
                result = Integer.parseInt(buffer);
                break;
            } catch (NumberFormatException e) {
                menu.errorCommand(buffer);
            }
        } return result;
    }

}
