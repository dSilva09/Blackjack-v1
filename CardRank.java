/**
 * Created by Dom on 4/27/2015.
 */

/**
 * The 13 ranks and their corresponding default values (for Blackjack) of a standard deck of cards.
 */
public enum CardRank {

        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10),
        ACE(11);

    private int cardValue;
    private CardRank cardRank;

    public CardRank getCardRank() {
        return cardRank;
    }

    public int getCardValue() {
        return cardValue;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    private CardRank(int value){
        this.cardValue = value;
    }
}
