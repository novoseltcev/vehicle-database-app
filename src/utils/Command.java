package utils;

import java.util.Scanner;

public class Command{
    private final Scanner sc;
    private int value;
    private String errorBuffer = "";

    public Command(Scanner scanner) {
        sc = scanner;
    }

    public int getValue() {
        return value;
    }

    public int getInt() throws NumberFormatException {
        String buffer = sc.nextLine().toLowerCase();
        try {
            value = Integer.parseInt(buffer);
            return value;
        } catch (NumberFormatException error) {
            errorBuffer = buffer;
            throw error;
        }
    }

    public String getError() {
        if (!errorBuffer.isEmpty()) {
            return errorBuffer;
        }   return null;
    }
}
