import java.util.Scanner;

public class MortgageCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double monthlySalary = 0;
        double creditScore = 0;
        boolean hasCriminalRecord = false;

        while (true) {
            System.out.print("Enter your monthly salary: ");
            if (scanner.hasNextDouble()) {
                monthlySalary = scanner.nextDouble();
                if (monthlySalary >= 100000) {
                    break;
                } else {
                    System.out.println("Monthly salary must be at least 1,00,000.");
                }
            } else {
                System.out.println("Invalid Input! Please enter a numeric value.");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("Enter your credit score (0 - 500): ");
            if (scanner.hasNextDouble()) {
                creditScore = scanner.nextDouble();
                if (creditScore >= 0 && creditScore <= 500) {
                    break;
                } else {
                    System.out.println("Credit score must be between 0 and 500.");
                }
            } else {
                System.out.println("Invalid Input! Please enter a numeric value between 0 and 500.");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("Do you have a criminal record? (true/false): ");
            if (scanner.hasNextBoolean()) {
                hasCriminalRecord = scanner.nextBoolean();
                break;
            } else {
                System.out.println("Invalid Input! Please type 'true' or 'false':");
                scanner.next();
            }
        }

        System.out.println();
        if (creditScore >= 300 && !hasCriminalRecord) {
            System.out.println("Congratulations! You are eligible for a loan.");
        } else {
            System.out.println("Sorry, you are not eligible for a loan.");
            if (creditScore < 300 && hasCriminalRecord) {
                System.out.println("Reason: You must have a good credit score (≥ 300) and no criminal record.");
            } else if (creditScore < 300) {
                System.out.println("Reason: You must have a good credit score (≥ 300).");
            } else {
                System.out.println("Reason: You must have no criminal record.");
            }
        }

        scanner.close();
    }
}