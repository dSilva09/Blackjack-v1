/**
 * Created by Dom on 4/27/2015.
 */
public class Card {

    //Field declarations----------------------------------------
    Suit suit;
    int cardValue;
    CardRank cardRank;
    //private boolean faceUp;

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
    //Parameters: card value, card suit
    public Card(CardRank cardRank, int cardValue, Suit suit){

        this.suit = suit;
        this.cardRank = cardRank;
        this.cardValue = cardValue;
        //faceUp = false;
    }

}
