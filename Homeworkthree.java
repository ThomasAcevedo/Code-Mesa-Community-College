package hwplace;

public class Homeworkthree {

	public static void main(String[] args) {
		// Setting the upper and lower limit of my code
		int lowerLimit = (int) -(40 * Math.random());
		int upperLimit = (int) (40 * Math.random());

		// making sure non of the numbers are negative as 2 is the smallest prime number
		if (lowerLimit < 0)
			lowerLimit = 2;

		// Setting a variable for my prime number count
		int primeNumberCount = 0;

		// Going over each number in the range of my lower limit and upper limit
		for (int Number = lowerLimit; Number <= upperLimit; Number++) {
			boolean prime = true; // Here we are assuming that every number is prime for now

			// If my number is lower than 2 then its not prime
			if (Number < 2)
				prime = false;

			// check all the divisors possible up to the number that was decided
			for (int divisor = 2; divisor < Number; divisor++) {
				if (Number % divisor == 0) {
					// its not a prime number and therefore break the loop
					prime = false;
					break;
				}
			}

			// if the number is still prime after checking the divisors, then increase the
			// prime count by 1
			if (prime) {
				primeNumberCount++;
			}

		}

		// Printing out the amount of prime number in the given range
		System.out.println(
				"The amount of prime numbers between " + lowerLimit + " and " + upperLimit + " is " + primeNumberCount);

	}
}