package hwplace;

public class HomeWorkFive {
	// this loop returns true if the given number is present in the given array,
	// false otherwise.
	public static boolean isNumberInArray(int num, int[] list) {
		for (int x = 0; x < list.length; x++) {
			if (list[x] == num) {
				return true;
			}
		}
		return false;
	}

//This function takes two integer arrays, list 1 and list 2, as input parameters and return an array containing union of both arrays
	public static int[] unionArrays(int[] list1, int[] list2) {
		// Creates a new array to store the union of the two input arrays
		int[] unionArray = new int[list1.length + list2.length];
		// Keeps track of the current index of the union array
		int index = 0;
		// loop through each element of list1
		for (int i : list1) {
			// If current element is not already in the union array, add it to the next
			// available index and increment the index counter
			if (!isNumberInArray(i, unionArray)) {
				unionArray[index] = i;
				index++;
			}

		}
		for (int i : list2) { // same thing as list 1 but with list 2
			if (!isNumberInArray(i, unionArray)) {
				unionArray[index] = i;
				index++;
			}
		}
		return unionArray;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
