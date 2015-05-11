import javax.swing.*;
import java.util.*;

/**
 * Created by Dom on 4/27/2015.
 */
public class Player {
    public Scanner keyboard;

    //Field declarations----------------------------------------
    protected String name;
    protected List<Card> hand;
    //boolean hasBlackjack;
    protected int wager;
    protected double balance;
    protected int handSum;
    protected boolean isBust = false;
    protected boolean hasBalance = true;
    protected boolean hasBlackjack = false;

    //Constructor----------------------------------------

    //Overload 1
    //Parameters: player name, player balance
    public Player(String name, int balance) {
        hand = new ArrayList<Card>();
        this.name = name;
        this.balance = balance;
    }

    //Overload 2 - For AI
    //Parameters: starting balance
    public Player(int startingBalance) {
        hand = new ArrayList<Card>();
        this.balance = startingBalance;
    }

    //Overload 3 = For dealer
    public Player() {
    }

    //Properties----------------------------------------

    public boolean hasBalance() {
        if (getBalance() == 0) {
            return hasBalance  = false;
        }
        else{
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

    public void setName(String name) {
        this.name = name;
    }

    public int getHandSum() {
        return handSum;
    }

    public void setHandSum(int handSum) {
        this.handSum = handSum;
    }

    //Methods----------------------------------------

    //Prompts user for wager amount
    //Returns wager
    public void Wager() {
        keyboard = new Scanner(System.in);

        while(true) {
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

    //Prompts player for hit or stay
    //Returns choice
    public int HitPrompt() {
        int choice = Main.MenuSelections("Hit", 2);
        return choice;
    }

    /*Calls method
    public void Hit (Player player, Deck deck) {
        player.ReceiveCards(deck);
    }*/

    //Adds cards to a player's hand
    public void Hit(Player player, Deck deck) {
        player.getHand().add(TakeTopCard(deck));
    }

    //Removes top card
    //Returns top card
    public Card TakeTopCard(Deck deck) {

        Card topCard = deck.RemoveTopCard();
        if (topCard.getCardValue() >= 10 && topCard.getCardRank() != CardRank.TEN){
            System.out.println("You received a " + topCard.getCardRank() + " of " + topCard.getSuit());
        }
        else {
            System.out.println("You received a " + topCard.getCardValue() + " of " + topCard.getSuit());
        }
        return topCard;
    }
}