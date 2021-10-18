package utils;

import view.BaseMenu;

import java.util.Scanner;

public class Command{
    private final Scanner sc;
    private final BaseMenu baseMenu;
    private final int value;
    public Command(BaseMenu menu, Scanner scanner) {
        baseMenu = menu;
        sc = scanner;
        value = getInt();
    }

    public int getValue() {
        return value;
    }

    protected int getInt() {
        while (true) {
            String buffer = sc.nextLine().toLowerCase();
            try {
                return Integer.parseInt(buffer);
            } catch (NumberFormatException e) {
                baseMenu.errorCommand(buffer);
            }
        }
    }
}
