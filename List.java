package homeworkcs;
/*
 * class that has a fixed-size array to store elements. provides ,methods to access, find, 
 * insert and summarize the items.
 */

public class List {

	private static int maxSize = 50;
	private String[] items;
	private int size;

	// constructor making an empty list
	public List() {
		items = new String[maxSize];
		size = 0;

	}

	// Accessor to return the string at a given position
	public String getItem(int position) {
		if (position >= 1 && position <= size) {
			return items[position - 1];

		} else {
			return null;
		}
	}

	// Method to insert a string at a given position
	public void insertItem(String item, int position) {
		if (position >= 1 && position <= size + 1 && size < maxSize) {
			for (int i = size; i >= position; i--) {
				items[i] = items[i - 1];
			}
			items[position - 1] = item;
			size++;
		}
	}

	// method to return the position of a given string in the list
	public int findItem(String item) {
		for (int i = 0; i < size; i++) {
			if (items[i].equals(item)) {
				return i + 1;
			}
		}
		return 0;// item not found
	}

	// Method to summarize the entire contents of the list
	public String toString() {
		String summary = "List Contents: ";
		for (int i = 0; i < size; i++) {
			summary += items[i];
			if (i < size - 1) {
				summary += ", ";
			}
		}
		return summary;
	}

}
