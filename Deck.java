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
    //No parameters
    //Creates Card array list
    public Deck() {

        this.cards = new ArrayList<Card>();

        //Creates a new card for every rank/suit pair
        for(CardRank value: CardRank.values()){

            for (Suit suit: Suit.values()){
                cards.add(new Card(value, value.getCardValue(), suit));
            }
        }

    }

    //Methods----------------------------------------

    //Gets current top card
    public Card getTopCard() {
        topCard = cards.get(cards.size() - 1);
        return topCard;
    }

    //Removes top card from deck, returns top card
    public Card RemoveTopCard() {
        topCard = cards.remove(cards.size() - 1);
        return topCard;
    }

    //Shuffles deck
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
