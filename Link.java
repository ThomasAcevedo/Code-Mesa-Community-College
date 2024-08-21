import java.util.Scanner;

// Inventory class
class Inventory {
	private String _itemName;
	private int _quantity;
	private double _costPerItem;

	public Inventory(String itemName, int quantity, double costPerItem) {
		_itemName = itemName;
		_quantity = quantity;
		_costPerItem = costPerItem;
	}

	public String getItemName() {
		return _itemName;
	}

	public int getQuantity() {
		return _quantity;
	}

	public double getCostPerItem() {
		return _costPerItem;
	}

	public String toString() {
		return "Item: " + _itemName + ", Quantity: " + _quantity + ", Cost Per Item: $" + _costPerItem;
	}

	// equals method
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null || getClass() != other.getClass())
			return false;

		Inventory inventory = (Inventory) other;
		return _itemName.equals(inventory._itemName);
	}
}

// Link class
class Link {
	private Object _data;
	private Link _next;

	public Link(Object data, Link next) {
		_data = data;
		_next = next;
	}

	public void setNext(Link next) {
		_next = next;
	}

	public Link getNext() {
		return _next;
	}

	public Object getData() {
		return _data;
	}
}

// List class
class List {
	private Link head;
	private int size;

	public List() {
		head = null;
		size = 0;
	}

	public Link getHead() {
		return head;
	}

	// insertOnFront
	public void insertFront(Inventory inventory) {
		head = new Link(inventory, head);
		size++;
	}

	// insertAtPos
	public void insertAtPos(Inventory inventory, int pos) {
		if (pos < 1 || pos > size + 1) {
			System.out.println("Invalid Position");
			return;
		}
		if (pos == 1) {
			insertFront(inventory);
			return;
		}

		Link current = head;
		if (current == null) {
			System.out.println("List is empty u silly man");
			return;
		}

		for (int i = 0; i < pos - 1; i++) {
			if (current.getNext() == null) {
				System.out.println("Error, you are out of bounds dear sir");
				return;
			}
			current = current.getNext();
		}

		Link link = new Link(inventory, current.getNext());
		current.setNext(link);
		size++;
	}

	// delete
	public void delete(int pos) {
		if (pos < 1 || pos > size) {
			System.out.println("there is nothing to delete here u goofball");
			return;
		}
		if (pos == 1) {
			head = head.getNext();
		} else {
			Link current = head;
			for (int i = 1; i < pos - 1; i++) {
				current = current.getNext();
			}
			current.setNext(current.getNext().getNext());
		}
		size--;
	}

	// deleteItem
	public void deleteItem(Object data) {
		if (isEmpty())
			return;
		Link current = head;
		Link previous = null;

		while (current != null) {
			if (current.getData().equals(data)) {
				if (previous == null) {
					head = current.getNext();
				} else {
					previous.setNext(current.getNext());
				}
				size--;
			} else {
				previous = current;
			}
			current = current.getNext();
		}
	}

	private boolean isEmpty() {
		return false;
	}

	// retrieve
	public Object retrievePos(int pos) {
		if (pos < 1 || pos > size)
			return null;
		Link current = head;
		for (int i = 1; i < pos; i++) {
			current = current.getNext();
		}
		return current.getData();
	}

	// find
	public List find(Object data) {
		List positions = new List();
		Link current = head;
		int i = 1;

		while (current != null) {
			if (current.getData() instanceof Inventory) {
				Inventory inventory = (Inventory) current.getData();
				if (inventory.equals(data)) {
					positions.insertAtPos(inventory, positions.getSize() + 1);
					i++;
				}
			}
			current = current.getNext();
		}
		return positions;
	}

	// getSize
	public int getSize() {
		return size;
	}

	// show
	public String show() {
		String result = "[";
		Link current = head;

		while (current != null) {
			result += current.getData().toString();
			if (current.getNext() != null) {
				result += ",";
			}
			current = current.getNext();
		}
		result += "]";
		return result;
	}
	// Initiliaze duh

	public void initialize() {
		head = null;
		size = 0;
		System.out.println("List initialized. YIPPIEEE");
	}

	public class TestProgram {
		private static Scanner scanner = new Scanner(System.in);
		private static List inventoryList1 = new List();
		private static List inventoryList2 = new List();
		private static List stringList = new List();

		public static void main(String[] args) {
			int userChoice;

			do {
				displayMainMenu();
				userChoice = scanner.nextInt();

				if (userChoice == 1 || userChoice == 2 || userChoice == 3) {
					manipulateList(userChoice);
				} else if (userChoice == 4) {
					quit();
				} else {
					System.out.println("That, my friend, is an invalid choice.");
				}
			} while (userChoice != 4);
		}

		private static void displayMainMenu() {
			System.out.println("HI, I just lost many braincells trying to make this, so here it goes..\n\n" +
					"Linked List you want to manipulate\n\n" +
					"1-Inventory List 1\n" +
					"2-Inventory List 2\n" +
					"3-String List\n" +
					"3-Quit App\n\n" +
					"For Inventory List 1 (enter'1') for Inventory List 2 (enter '2')\n" +
					"For String List (enter'3') to QUIT(enter'4')");
		}

		private static void manipulateList(int userChoiceForLists) {
			List selectedList = getChosenList(userChoiceForLists);

			System.out.println("You are now manipulating Inventory List #" + userChoiceForLists);
			int operationChoice;
			do {
				displayListOperations();
				operationChoice = scanner.nextInt();

				if (operationChoice == 1) {
					selectedList.initialize();
				} else if (operationChoice == 2) {
					insertItem(selectedList);
				} else if (operationChoice == 3) {
					insertItemAtFront(selectedList);
				} else if (operationChoice == 4) {
					deleteItem(selectedList);
				} else if (operationChoice == 5) {
					retrieveItem(selectedList);
				} else if (operationChoice == 6) {
					findItemPositions(selectedList);
				} else if (operationChoice == 7) {
					getSize(selectedList);
				} else if (operationChoice == 8) {
					displayList(selectedList);
				} else if (operationChoice == 9) {
					if (userChoiceForLists == 1 || userChoiceForLists == 2) {
						computeTotalValue(selectedList);
					} else {
						System.out.println("Option not available for String Linked List.");
					}
				} else if (operationChoice == 10) {
					System.out.println("Going back to the main menu...later alligator");
				} else {
					System.out.println("0_0 not a valid choice dude. not cool");
				}
			} while (operationChoice != 10);
		}

		private static List getChosenList(int userChoice) {
			if (userChoice == 1) {
				return inventoryList1;
			} else if (userChoice == 2) {
				return inventoryList2;
			} else if (userChoice == 3) {
				return stringList;
			} else {
				return null;
			}
		}

		private static void displayListOperations() {
			System.out.println("Operations:\n" +
					"1) Initialize: create an empty Linked List\n" +
					"2) Insert: insert a given Item at a specified Index\n" +
					"3) InsertAtFront: Insert given Item at the front of the List\n" +
					"4) Delete: delete item\n" +
					"5) Retrieve: return the item at a specified Index\n" +
					"6) Find: return a List of the positions of a specified Item\n" +
					"7) getSize: return the size of the List\n" +
					"8) show: Print your List\n" +
					"9) computeTotalValueOfInventory: computes the total value of the inventory\n" +
					"10) To Go back to the main menu");
		}

		private static void insertItem(List selectedList) {
			System.out.print("Enter item to insert: ");
			String itemName = scanner.next();
			System.out.print("Enter quantity: ");
			int quantity = scanner.nextInt();
			System.out.print("Enter cost per item: ");
			double costPerItem = scanner.nextDouble();
			System.out.print("Enter position: ");
			int pos = scanner.nextInt();
			selectedList.insertAtPos(new Inventory(itemName, quantity, costPerItem), pos);
			System.out.println("Item inserted at position " + pos + ".");
		}

		private static void insertItemAtFront(List selectedList) {
			System.out.print("Enter item to insert at front: ");
			String itemName = scanner.next();
			System.out.print("Enter quantity: ");
			int quantity = scanner.nextInt();
			System.out.print("Enter cost per item: ");
			double costPerItem = scanner.nextDouble();
			selectedList.insertFront(new Inventory(itemName, quantity, costPerItem));
			System.out.println("Item inserted at the front.");
		}

		private static void deleteItem(List selectedList) {
			System.out.print("Enter position to delete: ");
			int pos = scanner.nextInt();
			selectedList.delete(pos);
			System.out.println("Item deleted at position " + pos + ".");
		}

		private static void retrieveItem(List selectedList) {
			System.out.print("Enter position to retrieve: ");
			int pos = scanner.nextInt();
			Object retrievedItem = selectedList.retrievePos(pos);
			System.out.println("Retrieved item: " + retrievedItem);
		}

		private static void findItemPositions(List selectedList) {
			System.out.print("Enter item to find positions: ");
			String itemName = scanner.next();
			List positions = selectedList.find(new Inventory(itemName, 0, 0.0));
			System.out.println("Positions: " + positions);
		}

		private static void getSize(List selectedList) {
			int size = selectedList.getSize();
			System.out.println("Size of the list: " + size);
		}

		private static void displayList(List selectedList) {
			System.out.println(selectedList.show());
		}

		private static void computeTotalValue(List selectedList) {
			double totalValue = 0.0;
			for (int i = 1; i <= selectedList.getSize(); i++) {
				if (selectedList.retrievePos(i) instanceof Inventory) {
					Inventory inventory = (Inventory) selectedList.retrievePos(i);
					totalValue += inventory.getCostPerItem() * inventory.getQuantity();
				}
			}
			System.out.println("Total value of inventory: $" + totalValue);
		}

		private static void quit() {
			System.out.println("End Of App\n" + "Closing...");
		}
	}
}
