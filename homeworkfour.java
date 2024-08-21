package hwplace;

public class homeworkfour {

	public static int absolute(int num) { // method to calculate the absolute value of any integer
		if (num < 0) {
			return -num; // if number is negative then return positive

		}
		return num;
	}

	public static double absolute(double num) { // method to calculate the absolute value of a double
		if (num < 0) {
			return -num;
		}
		return num;
	}

	public static int Fac(int num) { // method to calculate the factorial of a number
		int result = 1;
		for (int i = 2; i <= num; i++) {
			result *= i; // this multiplies y each number from 2 to "num"
		}
		return result;
	}

	public static int binomialfunction(int r, int n) { // method to calculate the binomial coefficient of two numbers
		int num = Fac(n);
		int denom = Fac(r) * Fac(n - r); // formula
		int result = num / denom;
		return result;
	}

	public static void printGraph(int G) {

		if (G < 1) { // if size is less than 1 then return nothing
			return;
		}
		int size = 2 * G + 1; // calculating size of the graph as its always going to be odd due to the origin
								// point "+"
		for (int x = size - 1; x >= 0; x--) // loop for the rows
			for (int y = 0; y < size; y++) // loop for the columns
				if (y == G && x == G) {
					// print statements
					System.out.print("+");
				} else if (y == G) {
					System.out.print("|\n");
				} else if (x == G) {
					System.out.print("-");
				} else {
					System.out.print(" ");
				}

	}

}
