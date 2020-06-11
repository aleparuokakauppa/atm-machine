package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    private Scanner scanner;

    public UserInput() {
        scanner = new Scanner(System.in);
    }

    public String readString(String question) {
        System.out.print(question + " : ");
        return scanner.nextLine();
    }

    public long readLong(String question) {
        long number = 0;
        while(true) {
            System.out.print(question + " : ");
            try {
                number = scanner.nextLong();
                scanner.nextLine();
                break;
            } catch (InputMismatchException exception) {
                System.out.println("Numbers only.");
            }
            scanner.nextLine();
        }
        return number;
    }
}
