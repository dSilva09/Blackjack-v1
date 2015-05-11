import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dom on 4/27/2015.
 */
public class Dealer extends Player{

    //Field declarations----------------------------------------
    Card topCard;

    //Constructor----------------------------------------
    public Dealer(){
        this.hand = new ArrayList<Card>();
    }

    //Methods----------------------------------------
    public void ShowTopCard(List<Card> hand){
        Card topCard = hand.get(hand.size() - 1);
        if (topCard.getCardValue() >= 10 && topCard.getCardRank() != CardRank.TEN){
            System.out.println("\nDealer has a " + topCard.getCardRank() + " of " + topCard.getSuit() + " showing.");
        }
        else {
            System.out.println("\nDealer has a " + topCard.getCardValue() + " of " + topCard.getSuit() + " showing");
        }
    }

    public Card TakeTopCard(Deck deck){
        Card topCard = deck.RemoveTopCard();
        //Only print out the dealer's top card
        CheckForCardType(topCard);
        return topCard;
    }

    public void CheckForCardType(Card topCard){
        if(!hand.isEmpty()) {
            if (topCard.getCardValue() >= 10 && topCard.getCardRank() != CardRank.TEN) {
                System.out.println("Dealer received a " + topCard.getCardRank() + " of " + topCard.getSuit());
            } else {
                System.out.println("Dealer received a " + topCard.getCardValue() + " of " + topCard.getSuit());
            }
        }
    }
}
