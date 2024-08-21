
/*Thomas Acevedo
 * This program includes classes that include classes that represent playing cards. 
 * The program allows the User to play Go Fish against the computer opponent.
 * The game keeps lpaying until the deck is empty or all pairs of cards have been collected.
 * The Games progress and final results are displayed in the console , whowing the novellas completed and  
 * the overall winner 
 */
import java.util.Random;
import java.util.Scanner;

class PokerCard {
    private int _suit;
    private int _value;

    public PokerCard(int suit, int value) {
        _suit = suit;
        _value = value;
    }

    public int getSuit() {
        return _suit;
    }

    public int getValue() {
        return _value;
    }

    public String toString() {
        String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
        String[] values = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

        return values[_value - 1] + " of " + suits[_suit - 1];
    }
}

class VegasDeck {
    private PokerCard[] cards;
    private int size;
    private static final int INITIAL_CAPACITY = 52;
    private static final int RESIZE_FACTOR = 2;
    private static final Random random = new Random();

    public VegasDeck() {
        cards = new PokerCard[INITIAL_CAPACITY];
        size = 0;
    }

    public void insert(PokerCard card) {
        if (size >= cards.length) {
            PokerCard[] newCards = new PokerCard[size * RESIZE_FACTOR];
            for (int i = 0; i < size; i++) {
                newCards[i] = cards[i];
            }
            cards = newCards;
        }
        cards[size] = card;
        size++;
    }

    public PokerCard deleteAny() {
        if (size > 0) {
            int randomIndex = random.nextInt(size);
            PokerCard deletedCard = cards[randomIndex];
            cards[randomIndex] = cards[size - 1];
            cards[size - 1] = null;
            size--;
            return deletedCard;
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += cards[i].toString() + "\n";
        }
        return result;
    }
}

public class Program4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VegasDeck stock = initializeDeck();
        VegasDeck userHand = dealHand(stock);
        VegasDeck computerHand = dealHand(stock);

        int userNovellas = 0;
        int computerNovellas = 0;

        int choice = 0;
        while (choice != 3) {
            displayMenu();
            choice = scanner.nextInt();
            if (choice == 1) {
                testVegasDeck();
            } else if (choice == 2) {
                playGoFishy(userHand, computerHand, stock, scanner, userNovellas, computerNovellas);
            } else if (choice != 3) {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Goodbye!");
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("(1) Test the VegasDeck");
        System.out.println("(2) Play \"Go Fishy\"");
        System.out.println("(3) Quit");
        System.out.print("Enter your choice: ");
    }

    private static VegasDeck initializeDeck() {
        VegasDeck deck = new VegasDeck();
        for (int suit = 1; suit <= 4; suit++) {
            for (int value = 1; value <= 13; value++) {
                PokerCard card = new PokerCard(suit, value);
                deck.insert(card);
            }
        }
        return deck;
    }

    private static VegasDeck dealHand(VegasDeck deck) {
        VegasDeck hand = new VegasDeck();
        for (int i = 0; i < 8; i++) {
            PokerCard card = deck.deleteAny();
            if (card != null) {
                hand.insert(card);
            }
        }
        return hand;
    }

    private static void testVegasDeck() {
        VegasDeck deck = new VegasDeck();
        deck.insert(new PokerCard(1, 1)); // Ace of Clubs
        System.out.println(deck.toString());
    }

    private static void playGoFishy(VegasDeck userHand, VegasDeck computerHand, VegasDeck stock, Scanner scanner,
            int userNovellas, int computerNovellas) {
        while (userHand.getSize() > 0 && computerHand.getSize() > 0 && stock.getSize() > 0) {
            displayGameState(userHand, computerHand, userNovellas, computerNovellas);
            userTurn(userHand, computerHand, stock, scanner);
            if (userHand.getSize() > 0 && computerHand.getSize() > 0 && stock.getSize() > 0) {
                computerTurn(userHand, computerHand, stock);
            }
        }

        displayFinalResults(userHand, computerHand, userNovellas, computerNovellas);
    }

    private static void displayGameState(VegasDeck userHand, VegasDeck computerHand, int userNovellas,
            int computerNovellas) {
        System.out.println("User's Hand:");
        System.out.println(userHand.toString());
        System.out.println("User's Novellas: " + userNovellas);

        System.out.println("Computer's Hand:");
        System.out.println(computerHand.toString());
        System.out.println("Computer's Novellas: " + computerNovellas);
    }

    private static void userTurn(VegasDeck userHand, VegasDeck computerHand, VegasDeck stock, Scanner scanner) {
        System.out.println("Your turn!");
        System.out.println("Your Hand:");
        System.out.println(userHand.toString());

        System.out.print("Enter the rank you want to ask for (1-13): ");
        int rankToAsk = scanner.nextInt();

        int cardsTransferred = transferCards(computerHand, userHand, rankToAsk);

        if (cardsTransferred > 0) {
            System.out.println("Computer gave you " + cardsTransferred + " card(s) of rank " + rankToAsk + "!");
            checkForNovellas(userHand);
            userTurn(userHand, computerHand, stock, scanner);
        } else {
            System.out.println("Go fish!");
            drawCard(userHand, stock);
        }
    }

    private static int transferCards(VegasDeck fromHand, VegasDeck toHand, int rank) {
        int count = fromHand.count(rank);
        for (int i = 0; i < count; i++) {
            PokerCard card = fromHand.delete(rank);
            if (card != null) {
                toHand.insert(card);
            }
        }
        return count;
    }

    private static void checkForNovellas(VegasDeck hand) {
        for (int value = 1; value <= 13; value++) {
            if (hand.count(value) == 4) {
                System.out.println("You completed a novella of rank " + value + "!");
                removeNovella(hand, value);
            }
        }
    }

    private static void removeNovella(VegasDeck hand, int value) {
        int count = 0;
        for (int i = 0; i < hand.getSize(); i++) {
            if (hand.getSize() > count && hand.cards[count].getValue() == value) {
                hand.delete(value);
            } else {
                count++;
            }
        }
    }

    private static void drawCard(VegasDeck hand, VegasDeck stock) {
        PokerCard card = stock.deleteAny();
        if (card != null) {
            hand.insert(card);
            System.out.println("You drew a " + card.toString() + " from the stock.");
        }
    }

    private static void computerTurn(VegasDeck userHand, VegasDeck computerHand, VegasDeck stock) {
        int randomRank = random.nextInt(13) + 1;
        int cardsTransferred = transferCards(userHand, computerHand, randomRank);

        if (cardsTransferred > 0) {
            System.out.println("Computer asked for rank " + randomRank + " and took " + cardsTransferred + " card(s)!");
            checkForNovellas(computerHand);
        } else {
            drawCard(computerHand, stock);
        }
    }

    private static void displayFinalResults(VegasDeck userHand, VegasDeck computerHand, int userNovellas,
            int computerNovellas) {
        System.out.println("Game Over!");

        System.out.println("User's Novellas: " + userNovellas);
        System.out.println("Computer's Novellas: " + computerNovellas);

        if (userNovellas > computerNovellas) {
            System.out.println("Congratulations! You win!YIPPIEE");
        } else if (userNovellas < computerNovellas) {
            System.out.println("Computer wins! Better luck next time:((((((");
        } else {
            System.out.println("It's a tie! Well played!:)))");
        }
    }
}
