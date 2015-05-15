import java.util.*;

/**
 * Created by Dom on 4/27/2015.
 */
public class Player {
    public Scanner keyboard;

    //Fields ----------------------------------------
    protected String name;
    protected List<Card> hand;
    //boolean hasBlackjack;
    protected int wager;
    protected double balance;
    protected int handSum;
    protected boolean isBust = false;
    protected boolean hasBalance = true;
    protected boolean hasBlackjack = false;

    //Constructor ----------------------------------------

    /**
     * Overload 1:
     * Creates a human player with a hand, name, and starting balance
     *
     * @param name            the name assigned to the human player
     * @param startingBalance the balance assigned to the human player
     */
    public Player(String name, int startingBalance) {
        hand = new ArrayList<Card>();
        this.name = name;
        this.balance = startingBalance;
    }

    /**
     * Overload 2:
     * Creates an empty player. Used for the dealer.
     */
    public Player() {
    }

    //Properties ----------------------------------------


    public boolean hasBalance() {
        if (getBalance() == 0) {
            return hasBalance = false;
        } else {
            return hasBalance;
        }
    }


    public boolean isBust() {
        return isBust;
    }

    public List<Card> getHand() {
        return hand;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double startingBalance) {
        this.balance = startingBalance;
    }

    public int getWager() {
        return wager;
    }

    public void setWager(int wager) {
        this.wager = wager;
    }

    public String getName() {
        return name;
    }

    public int getHandSum() {
        return handSum;
    }

    public void setHandSum(int handSum) {
        this.handSum = handSum;
    }

    //Public Methods ----------------------------------------

    /**
     * Allows a human player to set a wager.
     * While a valid selection has not been selected:
     * Prompt user for wager amount and sets the wager entered
     * If the wager is at least equal to the balance of the human player,
     * set the wager and break from the loop
     * Else print a message informing the player that the wager they entered
     * is incorrect; continue looping
     */
    public void Wager() {
        keyboard = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter wager: ");
            wager = keyboard.nextInt();
            if (wager <= balance) {
                setWager(wager);
                break;
            } else {
                System.out.println("Wager exceeds balance.");
            }
        }
    }

    /**
     * Prompts human player to either "Hit" or "Stay."
     * Calls the HandleInput method and passes in the correct name of
     * the prompt list and the number of selections that will be prompted.
     *
     * @return the human choice the player has selected
     */
    public int HitPrompt() {
        int choice = Main.HandleInput("Hit", 2);
        return choice;
    }

    /**
     * Adds the top card from a deck to a player's hand
     *
     * @param player a human player or an AI dealer player
     * @param deck   the deck being used in a game
     */
    public void Hit(Player player, Deck deck) {
        player.getHand().add(TakeTopCard(deck));
    }

    /**
     * Removes the top card from a deck and checks its type.
     *
     * @param deck the deck being used in a game
     * @return the top card that is removed
     */
    public Card TakeTopCard(Deck deck) {

        Card topCard = deck.RemoveTopCard();
        CheckForCardType(topCard);
        return topCard;
    }

    /**
     * Checks if a card is a face or value card and prints the corresponding message.
     * @param topCard the card that is being checked for type
     */
    public void CheckForCardType(Card topCard) {

        if (topCard.getCardValue() >= 10 && topCard.getCardRank() != CardRank.TEN) {
            System.out.println("You received a " + topCard.getCardRank() + " of " + topCard.getSuit());
        } else {
            System.out.println("You received a " + topCard.getCardValue() + " of " + topCard.getSuit());
        }
    }

    /**
     * Tests to see if a hand has an Ace.
     * @param hand the player's hand in question.
     * @return true if the hand has an Ace, false if it does not
     */
    public boolean hasAnAce(List<Card> hand) {
        for (Card card : hand) {
            if (card.cardRank == CardRank.ACE) {
                return true;
            }
        }
        return false;
    }
}