package homeworkcs; 

import java.util.Scanner; 

public class PopulationGrowth { 

    public static Scanner kb = new Scanner(System.in); // Scanner object for user input.

    public static void main(String[] args) {

        // Prompting the user for input.
        System.out.println("Enter overcrowding threshold population:");
        int thresholdPopulation = kb.nextInt(); // Reading an integer value from the user.
        System.out.println("Enter the death percentage that die from overpopulation:");
        double overpopulationDeathPercentage = kb.nextDouble() / 100.0; // Reading a double value and converting it to a percentage.

        // Option Questions
        boolean continueCalculations = true; // A boolean flag to control the loop.
        while (continueCalculations) { // A loop for performing calculations based on user choice.

            // Displaying menu options.
            System.out.println("Choose an Option");
            System.out.println("1. Calculate how many prairie dogs you'll have after a number of years");
            System.out.println("2. Calculate how many years it will take to reach a specific population");
            System.out.print("Enter your choice (1 or 2): ");
            int choice = kb.nextInt(); // Reading the users choice.

            if (choice == 1) {
                // Get user inputs for option 1.
                int initialPopulation = getPositiveIntInput("Enter the initial prairie dog population:");
                double birthRate = getPercentage("Enter the annual birth rate as a percentage:");
                double deathRate = getPercentage("Enter the annual death rate as a percentage:");
                int immigrationRate = getPositiveIntInput("Enter the annual immigration rate (number of dogs that sneak in):");
                int emigrationRate = getPositiveIntInput("Enter the annual emigration rate (number of dogs that sneak out):");
                int years = getPositiveIntInput("Enter the number of years to project:");

                // Do calculations and display results for option 1.
                int finalPopulation = calculatePopulationAfterYears(initialPopulation, birthRate, deathRate, immigrationRate, emigrationRate, years, thresholdPopulation, overpopulationDeathPercentage);
                System.out.println("After " + years + " years, you'll have approximately " + finalPopulation + " prairie dogs.");
            } else if (choice == 2) { 
                // Get user inputs for option 2.
                int initialPopulation = getPositiveIntInput("Enter the initial prairie dog population:");
                int goalPopulation = getPositiveIntInput("Enter the desired prairie dog population:");

                // Do calculations and display results for option 2.
                int yearsToReachGoal = calculateYearsToReachPopulation(initialPopulation, goalPopulation, thresholdPopulation, overpopulationDeathPercentage);
                System.out.println("It will take approximately " + yearsToReachGoal + " years to reach a population of " + goalPopulation + ".");
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2."); 
            }

            System.out.print("Do you want to perform another calculation? (y/n): ");
            char response = kb.next().charAt(0);

            // Allow the user to continue or exit the loop based on their response.
            continueCalculations = (response == 'y' || response == 'Y');

        }

        kb.close(); // Closing the Scanner object to free resources.
    }

    //  method to get a positive integer input from the user.
    public static int getPositiveIntInput(String prompt) {
        int value;
        do {
            System.out.print(prompt);
            value = kb.nextInt();
            if (value < 0) {
                System.out.println("Please enter a positive number.");
            }
        } while (value < 0);
        return value;
    }

    // Helper method to get a percentage input from the user.
    public static double getPercentage(String prompt) {
        double value;
        do {
            System.out.print(prompt);
            value = kb.nextDouble();
            if (value < 0 || value > 100) {
                System.out.println("Please enter a percentage between 0 and 100.");
            }
        } while (value < 0 || value > 100);
        return value / 100.0; // Converting the percentage to a decimal.
    }

    // Method to calculate the population after a specified number of years.
    public static int calculatePopulationAfterYears(int initialPopulation, double birthRate, double deathRate,
            int immigrationRate, int emigrationRate, int years, int thresholdPopulation, double overpopulationDeathPercentage) {
        int population = initialPopulation;

        for (int year = 0; year < years; year++) {
            double growthRate = birthRate - deathRate;
            int migrationRate = immigrationRate - emigrationRate;

            // Calculate the new population
            double newPopulation = population + (population * growthRate) + migrationRate;

            if (newPopulation > thresholdPopulation) {
                int deathsDueToOverpopulation = (int) Math.round(newPopulation * overpopulationDeathPercentage);
                newPopulation -= deathsDueToOverpopulation;
            }

            // Ensure the population is non-negative
            if (newPopulation < 0) {
                newPopulation = 0;
            }

            population = (int) Math.round(newPopulation);

            // Display yearly result
            System.out.println(year + " " + population);
        }

        return population;
    }

    // Method to calculate the number of years to reach a specific population goal.
    public static int calculateYearsToReachPopulation(int initialPopulation, int goalPopulation,
            int thresholdPopulation, double overpopulationDeathPercentage) {
        int population = initialPopulation;
        int years = 0;

        while (population < goalPopulation) {
            double growthRate = 0.5 - 0.1; 
            int migrationRate = 5 - 10; 

            // Calculate the new population
            double newPopulation = population + (population * growthRate) + migrationRate;

            if (newPopulation > thresholdPopulation) {
                int deathsDueToOverpopulation = (int) Math.round(newPopulation * overpopulationDeathPercentage);
                newPopulation -= deathsDueToOverpopulation;
            }

            // Ensure the population is not negative
            if (newPopulation < 0) {
                newPopulation = 0;
            }

            population = (int) Math.round(newPopulation);

            years++;
        }

        return years;
    }
}
