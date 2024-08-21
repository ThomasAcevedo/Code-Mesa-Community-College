/*
 * Thomas Acevedo 
 *the program offers a menu-driven interface with the following options:
display all cars: Lists all cars in the database, showing their model, manufacturer, year, horsepower, weight, fuel efficiency (in miles per gallon), and price.

display car details: Allows you to enter a car model and displays its details if found in the database.

calculate average horsepower: Takes a price range as input and calculates the average horsepower of cars within that price range.

display fuel efficiency distribution: Sorts the cars by fuel efficiency and displays a distribution chart showing how many cars fall within specific fuel efficiency ranges.

exit: exits the program.
 */

package homeworkcs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Car {
	String model;
	String manufacturer;
	int year;
	int horsepower;
	int weight;
	int fuelEfficiency;
	double price;
}

public class CarDatabase {
	private static final int MAX_CARS = 100; // Maximum number of cars in the database
	private static Car[] cars = new Car[MAX_CARS];
	private static int numCars = 0; // Number of cars currently in the database

	public static void main(String[] args) {
		// Load data from the data file
		try {
			loadDatabase();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
			System.exit(1);
		}

		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		while (choice != 5) {
			// Display menu options
			System.out.println("Menu:");
			System.out.println("1. Display all cars");
			System.out.println("2. Display car details");
			System.out.println("3. Calculate average horsepower");
			System.out.println("4. Display fuel efficiency distribution");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			// Read user choice
			if (scanner.hasNextInt()) {
				choice = scanner.nextInt();

				// Process user choice
				if (choice == 1) {
					displayAllCars();
				} else if (choice == 2) {
					displayCarDetails(scanner);
				} else if (choice == 3) {
					calculateAverageHorsepower(scanner);
				} else if (choice == 4) {
					displayFuelEfficiencyDistribution();
				} else if (choice != 5) {
					System.out.println("Invalid choice. Please try again.");
				}
			} else {
				System.out.println("Invalid input. Please enter a number.");
				scanner.next(); // Consume invalid input
			}
		}

		// Close the scanner
		scanner.close();
		System.out.println("Goodbye!");
	}

	private static void loadDatabase() throws FileNotFoundException {
		File file = new File("C:\\Users\\thoma\\OneDrive\\Documents\\carlist.txt");

		// Check if the file exists before attempting to read from it
		if (!file.exists()) {
			throw new FileNotFoundException("File not found: " + file.getPath());
		}

		Scanner fileScanner = new Scanner(file);

		while (fileScanner.hasNext()) {
			String model = fileScanner.next();
			String manufacturer = fileScanner.next();
			int year = fileScanner.nextInt();
			int horsepower = fileScanner.nextInt();
			int weight = fileScanner.nextInt();
			int fuelEfficiency = fileScanner.nextInt();
			double price = fileScanner.nextDouble();

			addCar(model, manufacturer, year, horsepower, weight, fuelEfficiency, price);
		}
		fileScanner.close();
	}

	private static void addCar(String model, String manufacturer, int year, int horsepower, int weight,
			int fuelEfficiency, double price) {
		if (numCars < MAX_CARS) {
			Car car = new Car();
			car.model = model;
			car.manufacturer = manufacturer;
			car.year = year;
			car.horsepower = horsepower;
			car.weight = weight;
			car.fuelEfficiency = fuelEfficiency;
			car.price = price;
			cars[numCars] = car;
			numCars++;
		} else {
			System.out.println("Database is full. Cannot add more cars.");
		}
	}

	private static void displayAllCars() {
		System.out.println("\nList of All Cars:");
		// Display all cars
		System.out.printf("%-15s%-15s%-8s%-10s%-10s%-20s%-15s%n", "Model", "Manufacturer", "Year", "HP", "Weight",
				"Fuel Efficiency", "Price");
		for (int i = 0; i < numCars; i++) {
			Car car = cars[i];
			System.out.printf("%-15s%-15s%-8d%-10d%-10d%-20d%-15.2f%n", car.model, car.manufacturer, car.year,
					car.horsepower, car.weight, car.fuelEfficiency, car.price);
		}
	}

	private static void displayCarDetails(Scanner scanner) {
		scanner.nextLine();
		System.out.print("Enter the model of the car: ");
		String model = scanner.nextLine();
		boolean found = false;

		for (int i = 0; i < numCars; i++) {
			Car car = cars[i];
			if (car.model.equalsIgnoreCase(model)) {
				System.out.println("\nCar Details:");
				System.out.println("Model: " + car.model);
				System.out.println("Manufacturer: " + car.manufacturer);
				System.out.println("Year: " + car.year);
				System.out.println("Horsepower: " + car.horsepower);
				System.out.println("Weight: " + car.weight);
				System.out.println("Fuel Efficiency: " + car.fuelEfficiency);
				System.out.println("Price: $" + car.price);
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("Car not found in the database.");
		}
	}

	private static void calculateAverageHorsepower(Scanner scanner) {
		System.out.print("Enter the lower boundary of the price range: $");
		double lowerPrice = scanner.nextDouble();
		System.out.print("Enter the upper boundary of the price range: $");
		double upperPrice = scanner.nextDouble();
		int count = 0;
		int totalHorsepower = 0;

		for (int i = 0; i < numCars; i++) {
			Car car = cars[i];
			if (car.price >= lowerPrice && car.price <= upperPrice) {
				totalHorsepower += car.horsepower;
				count++;
			}
		}

		if (count > 0) {
			double averageHorsepower = (double) totalHorsepower / count;
			System.out.printf("\nAverage Horsepower in the specified price range: %.2f%n", averageHorsepower);
		} else {
			System.out.println("No cars found in the specified price range.");
		}
	}

	private static void displayFuelEfficiencyDistribution() {
		// Bubble sort cars by fuel efficiency in ascending order
		for (int i = 0; i < numCars - 1; i++) {
			for (int j = 0; j < numCars - i - 1; j++) {
				if (cars[j].fuelEfficiency > cars[j + 1].fuelEfficiency) {
					// swap cars[j] and cars[j + 1]
					Car temp = cars[j];
					cars[j] = cars[j + 1];
					cars[j + 1] = temp;
				}
			}
		}

		if (numCars == 0) {
			System.out.println("No cars in the database.");
			return;
		}

		int minFuelEfficiency = cars[0].fuelEfficiency;
		int maxFuelEfficiency = cars[numCars - 1].fuelEfficiency;
		int decadeWidth = (maxFuelEfficiency - minFuelEfficiency + 1) / 10;
		int[] decadeCounts = new int[10]; // Initialize decadeCounts array

		// Count the number of cars in each decade
		for (int i = 0; i < numCars; i++) {
			Car car = cars[i];
			int fuelEfficiency = car.fuelEfficiency;
			int index = (fuelEfficiency - minFuelEfficiency) / decadeWidth;
			if (index >= 10) {
				// Handle edge case where index goes out of bounds
				index = 9;
			}
			decadeCounts[index]++;
		}

		// Display fuel efficiency distribution
		System.out.println("\nFuel Efficiency Distribution (Miles per Gallon):");
		int lowerBound = minFuelEfficiency;
		int upperBound;
		for (int i = 0; i < 10; i++) {
			upperBound = lowerBound + decadeWidth - 1;
			System.out.printf("%3d-%-3d: ", lowerBound, upperBound);
			for (int j = 0; j < decadeCounts[i]; j++) {
				System.out.print("*");
			}
			System.out.println();
			lowerBound = upperBound + 1;
		}
	}
}
