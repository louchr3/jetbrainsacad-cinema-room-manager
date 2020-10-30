package com.company;
import java.util.Scanner;

public class Main {
    private static int totalRow;
    private static int seatPerRow;
    private static int rowNum;
    private static int seatNum;
    private static char[][] seats;
    private static int currentIncome;
    private static int occupiedSeats;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
	    // write your code here3
        int choice;
        System.out.println("Enter the number of rows:");
        totalRow= scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        seatPerRow = scanner.nextInt();

        seats = new char[totalRow][seatPerRow];
        fillSeats();

        do {
            choice = showChoices();
            if (choice == 1) {
                prinSeats();
            } else if (choice == 2) {
                buyTicket();
            } else if (choice == 3) {
                showStatistics();
            }
        } while(choice != 0);
    }

    public static void prinSeats() {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatPerRow; i++) {
            System.out.print(i);
            if (i < seatPerRow) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (int i = 0; i < totalRow; i++) {
            System.out.print((i + 1) + " ");
            for (int k = 0; k < seatPerRow; k++) {
                System.out.print(seats[i][k]);
                if (k < seatPerRow) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void buyTicket() {
        while (true) {
            System.out.println();
            System.out.println("Enter a row number:");
            rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNum = scanner.nextInt();
            if (rowNum < 1 || rowNum > totalRow || seatNum < 1 || seatNum > seatPerRow) {
                System.out.println();
                System.out.println("Wrong input!");
                continue;
            }
            if (isAvailable(rowNum, seatNum)) {
                break;
            }
            System.out.println();
            System.out.println("That ticket has already been purchased!");
        }

        int seatPrice = 0;
        boolean isSmallRoom = totalRow * seatPerRow <= 60;

        if (isSmallRoom) {
            seatPrice = 10;
        } else {
            seatPrice = rowNum <= (totalRow / 2) ? 10 : 8;
        }
        seats[rowNum - 1][seatNum - 1] = 'B';
        currentIncome += seatPrice;
        occupiedSeats++;
        System.out.println();
        System.out.println("Ticket price: $" + seatPrice);
    }

    public static int showChoices() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        return scanner.nextInt();
    }

    public static void fillSeats() {
        for (int i = 0; i < totalRow; i++) {
            for (int k = 0; k < seatPerRow; k++) {
                seats[i][k] = 'S';
            }
        }
    }

    public static int computeTotalIncome() {
        if (totalRow * seatPerRow <= 60) {
            return totalRow * seatPerRow * 10;
        } else {
            int frontSeats = totalRow / 2;
            int backSeats = totalRow - frontSeats;
            return  (frontSeats * seatPerRow * 10) + (backSeats * seatPerRow * 8);
        }
    }

    public static boolean isAvailable(int rowNum, int seatNum) {
        return seats[rowNum - 1][seatNum - 1] == 'S';
    }

    public static float getPercentage() {
        return (float) occupiedSeats / (totalRow * seatPerRow) * 100;
    }

    public static void showStatistics() {
        System.out.println();
        System.out.println("Number of purchased tickets: " + occupiedSeats);
        System.out.printf("Percentage: %.2f", getPercentage());
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + computeTotalIncome());
    }
}
