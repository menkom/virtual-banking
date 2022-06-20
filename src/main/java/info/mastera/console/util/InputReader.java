package info.mastera.console.util;

import java.util.Scanner;

public class InputReader {

    private final Scanner scanner = new Scanner(System.in);

    public String readCommand(String text) {
        System.out.print(text);
        return scanner.nextLine();
    }
}
