package LAB;

import java.util.Scanner;

public class TASK1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter score of student 1:");
        double s1 = input.nextDouble();
        System.out.print("Enter score of student 2:");
        double s2 = input.nextDouble();
        System.out.print("Enter score of student 2:");
        double s3 = input.nextDouble();
        double avg = (s1 + s2 + s3) / 3;
        System.out.printf("Average" + avg);
        System.out.println();

    }

}
