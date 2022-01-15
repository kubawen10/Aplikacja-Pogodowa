package Menu;

import java.util.Scanner;

public class MyScanner {
    Scanner scanner;

    public MyScanner() {
        scanner = new Scanner(System.in);
    }

    public int intChoice() {
        int choice=0;
        boolean flag = true;
        do{
            try {
                System.out.print("Choice: ");
                choice = scanner.nextInt();
                flag = false;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Type number!");
                scanner.nextLine();
            }
        }
        while (flag);

        scanner.nextLine();
        return choice;
    }

    public String stringChoice() {
        String choice;

        choice = scanner.nextLine();
        return choice;
    }

    public void waitForEnter() {
        System.out.println("Press enter to continue...");
        stringChoice();
    }
}