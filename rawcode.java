package packageTWO;

import java.util.Scanner;

public class rawcode {

	public static void main(String[] args) {
		System.out.println("Welcome to JDM imports!");// WELCOME LINE

		double x = 90000.99;// COST OF ITEM

		System.out.println("The following car(s) we have for sale are:\n\nCAR MODEL: \t\t PRICE:"); // TITLES OF DISPLAY
		System.out.println("\nSupra MK4 (4th Gen)\t" + x); // NAME OF ITEMS AND PRICES
		System.out.println("\t*Note* MAX PURCHASE AMOUNT IS 4"); // TELLING USER 4 IS THE MAX AMOUNT TO PURCHASE

		System.out.println("\nDid you find the car you are looking for ?\nEnter 'y; for YES or 'n' for NO"); // ASKING
																												// USER
																												// IF
																												// THEY
																												// WANT
																												// TO
																												// BUY

		Scanner console = new Scanner(System.in);
		char firstLetter = console.next().charAt(0); // SCANNING CHARACTER FROM USER

		if (firstLetter == 'y') { // IF y IS PRESSED THEN PRINT NEXT LINE

			System.out.println("How many cars would you like to buy? \nEnter Amount");
			int amount = console.nextInt(); // SCANNING INTERGER FORM USER

			if (amount < 0) { // IF VALUE IS GREATER LESS THAN 0 THEN PRINT NEXT LINE
				System.out.println(amount + " is not a vlaid amount");
				amount = 0; // VALUE IS SET TO 0
				System.out.println("Setting amount to 0");

			} else if (amount > 4) { // IF VALUE IS GREATER THAN 4 THEN PRINT NEXT LINE
				System.out.println(amount + " is not a valid amount");
				amount = 4;// VLAUE IS SET TO 4
				System.out.println("Setting amount to 4");
			}
			double answer1 = x * amount;// SETTING A VRAIBLE FOR THE ANSWER OF THE CACULATION

			System.out.println("Your total is " + amount + " * " + "$" + x + " = " + "$" + answer1);// DISPLAYING TOTAL
																									// WITH WORKING

		} else if (firstLetter == 'n') {// IF USER DOESTNT WANT TO SHOP THEN PRINT NEXT LINE
			System.out.println("Thank you for shopping with JDM improts!!");
		}

	}
}
