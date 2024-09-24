package org.baticuisine.Helpers;

import java.util.Scanner;

public class Helpers {
    private static Scanner scanner = new Scanner(System.in);
    public static boolean askYesNo(String prompt) {
        System.out.print(prompt);
        String response = scanner.next().toLowerCase();
        return response.equals("y");
    }
}
