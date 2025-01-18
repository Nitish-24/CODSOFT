import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
         Scanner scn = new Scanner(System.in);

        // Get the number of subjects from the user
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scn.nextInt();

        // Initialize an array to store marks and a variable to calculate the total
        double[] marks = new double[numSubjects];
        double totalMarks = 0;

        // Take input for marks of each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scn.nextDouble();
            totalMarks += marks[i];
        }

        // Calculate the average percentage
        double averagePercentage = totalMarks / numSubjects;

        // Determine the grade based on average percentage
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Print the total marks, average percentage, and grade
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage);
        System.out.println("Grade: " + grade);

        scn.close();
    }
}
