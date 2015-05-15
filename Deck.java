import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Dom on 4/27/2015.
 */
public class Deck {

    //Field declarations----------------------------------------
    public List<Card> cards;
    public Card topCard;

    //Properties
    public List<Card> getCards() {
        return cards;
    }

    //Constructor----------------------------------------

    /**
     * Creates a new deck of cards.
     * An array list of card objects is produced by assigning
     * each card rank 4 unique card suits.
     */
    public Deck() {

        this.cards = new ArrayList<Card>();

        //Creates a new card for every rank/suit pair
        for(CardRank value: CardRank.values()){

            for (Suit suit: Suit.values()){
                cards.add(new Card(value, value.getCardValue(), suit));
            }
        }

    }

    //Public Methods ----------------------------------------

    //Gets current top card
    public Card getTopCard() {
        topCard = cards.get(cards.size() - 1);
        return topCard;
    }

    /**
     * Removes the top card from a list of cards.
     * @return the top card
     */
    public Card RemoveTopCard() {
        topCard = cards.remove(cards.size() - 1);
        return topCard;
    }

    /**
     * Shuffles a deck of cards.
     * A card is randomly selected from an initial list of cards (cards) and added to a new list of cards (shuffledCards).
     * Once all of the cards have been transferred from 'cards' to the 'shuffledCards,' 'shuffledCards' is reassigned as 'cards'
     * to prevent referencing issues.
     */
    public void Shuffle() {

        //Create separate array list of cards for new shuffled deck
        //Create random number generator to randomly index non-shuffled deck
        List<Card> shuffledCards = new ArrayList<Card>();
        Random rand = new Random();
        Card nextCard;

        //Index array from end to beginning
        //Remove cards at a randomly selected index between 1 and the current index + 1, exclusive
        //Add card to new shuffled deck
        for (int i = cards.size() - 1; i >= 0; i--){
            nextCard = cards.remove(rand.nextInt(i + 1));
            shuffledCards.add(nextCard);
        }
        //Reassign the shuffled deck to the old deck;
        cards = shuffledCards;
    }
}
