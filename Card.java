/**
 * Created by Dom on 4/27/2015.
 */
public class Card {

    //Fields ----------------------------------------
    Suit suit;
    int cardValue;
    CardRank cardRank;

    //Properties----------------------------------------
    public Suit getSuit() {
        return suit;
    }

    public int getCardValue() {
        return cardValue;
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    //Constructor----------------------------------------

    /**
     * Creates a card object.
     * @param cardRank a cards corresponding rank. Card ranks that contain numbers have a corresponding card value.
     *                 Cards that do not are known as face cards (Jack, Queen, King, Ace).
     * @param cardValue the numerical value that represents a cards rank.
     * @param suit an additional distinguishing feature of card (Club, Spade, Diamond, Heart). Does not influence outcome of game.
     */
    public Card(CardRank cardRank, int cardValue, Suit suit){
        this.suit = suit;
        this.cardRank = cardRank;
        this.cardValue = cardValue;
    }

}
