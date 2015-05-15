import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dom on 4/27/2015.
 */
public class Dealer extends Player {

    //Fields ----------------------------------------
    Card topCard;

    //Constructor----------------------------------------

    /**
     * Creates a new dealer with a hand.
     */
    public Dealer() {
        this.hand = new ArrayList<Card>();
    }

    //Public Methods----------------------------------------

    /**
     * Gets the top card from the dealers hand.
     *
     * @param hand the dealer's hand
     */
    public void ShowTopCard(List<Card> hand) {
        Card topCard = hand.get(hand.size() - 1);
        if (topCard.getCardValue() >= 10 && topCard.getCardRank() != CardRank.TEN) {
            System.out.println("\nDealer has a " + topCard.getCardRank() + " of " + topCard.getSuit() + " showing.");
        } else {
            System.out.println("\nDealer has a " + topCard.getCardValue() + " of " + topCard.getSuit() + " showing.");
        }
    }

    /**
     * Removes the top card from a deck and returns it.
     * Cards are not displayed using the 'CheckForCardType' method
     * until after the dealer has received its initial hand (hand size >=2).
     * @param deck the deck being used in a game
     */
    @Override
    public Card TakeTopCard(Deck deck) {
        Card topCard = deck.RemoveTopCard();
        if (hand.size() >= 2){
            CheckForCardType(topCard);
        }
        return topCard;
    }

    /**
     * Checks if a card is a face or value card and prints the corresponding message.
     * Altered print statements for dealer.
     * @param topCard the card that is being checked for type
     */
    @Override
    public void CheckForCardType(Card topCard) {
            if (topCard.getCardValue() >= 10 && topCard.getCardRank() != CardRank.TEN) {
                System.out.println("\nDealer received a " + topCard.getCardRank() + " of " + topCard.getSuit() + ".");
            } else {
                System.out.println("\nDealer received a " + topCard.getCardValue() + " of " + topCard.getSuit() + ".");
            }
    }
}
