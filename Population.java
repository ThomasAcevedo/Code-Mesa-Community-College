/*Thomas Acevedo
 * This program predicts the growth of a prairie dog population
 * It allows for calculations of future population based on
 * birth rate, death rate, and migration rate
 */

package homeworkcs;

import java.util.Scanner;

public class Population {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Prairie Dog Population Growth Calculator");

		int thresholdPopulation = getNonNegativeInt(scanner, "Enter the overcrowding threshold population: ");
		double overpopulationDeathPercentage = getValidPercentage(scanner,
				"Enter the percentage that die from overpopulation: ") / 100.0;

		boolean continueCalculations = true;
		while (continueCalculations) {
			System.out.println("\nChoose an Option:");
			System.out.println("1. Calculate how many prairie dogs you'll have after a number of years");
			System.out.println("2. Calculate how many years it will take to reach a specific population");
			System.out.print("Enter your choice 1 or 2: ");
			int choice = scanner.nextInt();

			if (choice == 1) {
				handleFirstOption(scanner, thresholdPopulation, overpopulationDeathPercentage);
			} else if (choice == 2) {
				handleSecondOption(scanner, thresholdPopulation, overpopulationDeathPercentage);
			} else {
				System.out.println("Invalid choice :/. Please enter 1 or 2.");
			}

			System.out.print("Do you want to perform another calculation? (y/n): ");
			continueCalculations = scanner.next().toLowerCase().startsWith("y");
		}

		scanner.close();
	}

	private static void handleFirstOption(Scanner scanner, int thresholdPopulation,
			double overpopulationDeathPercentage) {
		int initialPopulation = getPositiveInt(scanner, "Enter the initial prairie dog population: ");
		double birthRate = getValidPercentage(scanner, "Enter the birth rate (percentage1-100): ") / 100.0;
		double deathRate = getValidPercentage(scanner, "Enter the death rate (percentage1-100): ") / 100.0;
		int migrationRate = getInt(scanner,
				"Enter the migration rate (net number of prairie dogs moving in or out each year): ");
		int years = getPositiveInt(scanner, "Enter the number of years: ");
		boolean displayTable = getYesNo(scanner, "Do you want to display the yearly growth table? (y/n): ");

		int population = initialPopulation;
		if (displayTable) {
			System.out.println("Year\tPopulation");
			System.out.println("0\t" + population);
		}

		for (int year = 1; year <= years; year++) {
			double growthRate = birthRate - deathRate;
			double tempPopulation = population * (1 + growthRate) + migrationRate;

			if (tempPopulation > thresholdPopulation) {
				tempPopulation = tempPopulation - tempPopulation * overpopulationDeathPercentage;
			}

			population = (int) Math.round(tempPopulation);
			if (population < 0)
				population = 0;

			if (displayTable) {
				System.out.println(year + "\t" + population);
			}
		}
	}

	private static void handleSecondOption(Scanner scanner, int thresholdPopulation,
			double overpopulationDeathPercentage) {
		int initialPopulation = getPositiveInt(scanner, "Enter the initial prairie dog population: ");
		double birthRate = getValidPercentage(scanner, "Enter the birth rate (percentage 1- 100): ") / 100.0;
		double deathRate = getValidPercentage(scanner, "Enter the death rate (percentage 1-100): ") / 100.0;
		int migrationRate = getInt(scanner,
				"Enter the migration rate (net number of prairie dogs moving in or out each year): ");
		int targetPopulation = getPositiveInt(scanner, "Enter the target prairie dog population: ");

		int years = 0;
		int population = initialPopulation;

		while (population < targetPopulation) {
			double growthRate = birthRate - deathRate;
			double tempPopulation = population * (1 + growthRate) + migrationRate;

			if (tempPopulation > thresholdPopulation) {
				tempPopulation = tempPopulation - tempPopulation * overpopulationDeathPercentage;
			}

			population = (int) Math.round(tempPopulation);
			if (population < 0)
				population = 0;

			years++;
		}

		System.out
				.println("It will take approximately " + years + " years to reach a population of " + targetPopulation);

	}

	private static int getNonNegativeInt(Scanner scanner, String prompt) {
		System.out.print(prompt);
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter a non-negative integer.");
			scanner.next();
			System.out.print(prompt);
		}
		int number = scanner.nextInt();
		while (number < 0) {
			System.out.println("The number cannot be negative. Please enter a non-negative integer.");
			System.out.print(prompt);
			number = scanner.nextInt();
		}
		return number;
	}

	private static int getPositiveInt(Scanner scanner, String prompt) {
		System.out.print(prompt);
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter a positive integer.");
			scanner.next();
			System.out.print(prompt);
		}
		int number = scanner.nextInt();
		while (number <= 0) {
			System.out.println("The number must be positive. Please enter a positive integer.");
			System.out.print(prompt);
			number = scanner.nextInt();
		}
		return number;
	}

	private static double getValidPercentage(Scanner scanner, String prompt) {
		System.out.print(prompt);
		while (!scanner.hasNextDouble()) {
			System.out.println("Invalid input. Please enter a valid percentage.");
			scanner.next();
			System.out.print(prompt);
		}
		double percentage = scanner.nextDouble();
		while (percentage < 0 || percentage > 100) {
			System.out.println("Invalid percentage. Enter a value between 0 and 100.");
			System.out.print(prompt);
			percentage = scanner.nextDouble();
		}
		return percentage;
	}

	private static int getInt(Scanner scanner, String prompt) {
		System.out.print(prompt);
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter an integer.");
			scanner.next();
			System.out.print(prompt);
		}
		return scanner.nextInt();
	}

	private static boolean getYesNo(Scanner scanner, String prompt) {
		String input;
		do {
			System.out.print(prompt);
			input = scanner.next().trim().toLowerCase();
			if (!input.equals("y") && !input.equals("n")) {
				System.out.println("Invalid input. Please enter 'y' (yes) or 'n' (no).");
			}
		} while (!input.equals("y") && !input.equals("n"));

		return input.equals("y");
	}

}
