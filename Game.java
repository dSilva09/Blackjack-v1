import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Dom on 4/27/2015.
 */
public class Game {

    //Field declarations----------------------------------------
    private Player mainPlayer;
    private Dealer dealer;
    private Deck deck;
    Scanner keyboard = new Scanner(System.in);

    //Properties----------------------------------------
    public Player getMainPlayer() {
        return mainPlayer;
    }

    public void setMainPlayer(Player mainPlayer) {
        this.mainPlayer = mainPlayer;
    }

    public Player getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }


    //Constructor----------------------------------------
    public Game(String playerName, int startingBalance) {
        this.mainPlayer = new Player(playerName, startingBalance);
        this.dealer = new Dealer();
        this.deck = new Deck();
    }

    //Methods----------------------------------------

    public void Play() {
        boolean keepPlaying = true;

        while (keepPlaying && mainPlayer.hasBalance()) {
            //Shuffle deck
            deck.Shuffle();

            //Returns mainPlayer balance
            System.out.println("Balance: " + mainPlayer.getBalance());
            //Set wager
            mainPlayer.Wager();

            //Deal cards
            Deal(mainPlayer, dealer);

            //Check is dealer has Blackjack, all players lose if true
            if (CheckForBlackjack(dealer)) {
                System.out.println("Dealer has Blackjack.");
                System.out.println("You lose :/");
                dealer.hasBlackjack = true;
                Lose(mainPlayer);
            }

            //Gameplay:
            //Player's Turn
            MainPlayersTurn();
            int mainPlayerHandSum = mainPlayer.getHandSum();

            if (!mainPlayer.isBust() && !mainPlayer.hasBlackjack) {
                //Dealer's turn
                DealersTurn();
                int dealerHandSum = dealer.getHandSum();

                //Evaluation (for players who haven't already busted)
                //For mainPlayer
                if (!dealer.isBust() && !mainPlayer.isBust()) {
                    if (mainPlayerHandSum == dealerHandSum) {
                        System.out.println("Push");
                    } else if (mainPlayerHandSum > dealerHandSum) {
                        Win(mainPlayer);
                    } else {
                        Lose(mainPlayer);
                    }
                } else {
                    Win(mainPlayer);
                }
            }
            if (mainPlayer.hasBalance()) {
                keepPlaying = PlayAgain();
            }
            else {
                System.out.println("You ran outta cash!");
                System.out.println("Maybe next time :/");
            }
        }
    }

    //Controls course of action for human players
    private void MainPlayersTurn() {

        if(CheckForBlackjack(mainPlayer)){
            System.out.println("You got Blackjack!");
            mainPlayer.hasBlackjack = true;
            Win(mainPlayer);
        }
        mainPlayer.setHandSum(CalcHandSum(mainPlayer, mainPlayer.getHand()));

        //Until bust or stay
        while (true) {

            System.out.println("Currently at: " + mainPlayer.getHandSum());
            int choice = mainPlayer.HitPrompt();
            //If "hit," return the sum and add another card to the mainPlayer's hand
            if (choice == 1) {
                mainPlayer.Hit(mainPlayer, deck);
                mainPlayer.setHandSum(CalcHandSum(mainPlayer, mainPlayer.getHand()));

                //Check to see if mainPlayer busts
                if (IsBust(mainPlayer)) {
                    System.out.println("You busted at " + mainPlayer.getHandSum());
                    Lose(mainPlayer);
                    mainPlayer.isBust = true;
                    break;
                }
            }
            //If "stay," return the sum and exit loop
            else {
                System.out.println("You stayed at " + mainPlayer.getHandSum());
                mainPlayer.setHandSum(mainPlayer.getHandSum());
                break;
            }
        }
    }

    //Controls the behavior for the Dealer AI
    private void DealersTurn() {

        dealer.setHandSum(CalcHandSum(dealer, dealer.hand));
        //Hits while hand is less than 17
        while (dealer.getHandSum() < 17) {
            dealer.Hit(dealer, deck);
            dealer.setHandSum(CalcHandSum(dealer, dealer.hand));
        }
        if (IsBust(dealer)) {
            System.out.println("Dealer busts at " + dealer.getHandSum());
            dealer.isBust = true;
        }
        else {
            System.out.println("Dealer stays at " + dealer.getHandSum());
            dealer.setHandSum(dealer.getHandSum());
        }
    }

    private void Deal(Player player, Dealer dealer) {

        //Deals cards 1 at a time to each mainPlayer
        for (int i = 0; i < 2; i++) {
            player.getHand().add(player.TakeTopCard(deck));
            dealer.getHand().add(dealer.TakeTopCard(deck));
        }
        dealer.ShowTopCard(dealer.getHand());
    }

    //Determines if mainPlayer busts or not
    public boolean IsBust(Player player) {
        if (CalcHandSum(player, player.getHand()) > 21) {
            return true;
        } else {
            return false;
        }
    }

    public boolean CheckForBlackjack(Player player) {

        if (CalcHandSum(player, player.getHand()) == 21 && player.getHand().contains(CardRank.ACE)) {
            return true;
        }
        else{
            return false;
        }
    }

    //public boolean CheckForAce(List<Card> hand){}

    private void Lose(Player player){
        player.setBalance(player.getBalance() - player.getWager());
        System.out.println();
        System.out.println(player.getName() + " lost :/");
        System.out.println(player.getName() + "'s" + " balance is now " + player.getBalance());
    }

    private void Win(Player player){
        player.setBalance(player.getBalance() + player.getWager() * 1.5);
        System.out.println();
        System.out.println(player.getName() + " wins!");
        System.out.println(player.getName() + "'s" + " balance is now " + player.getBalance());
    }

    private boolean PlayAgain() {
        int choice = Main.MenuSelections("Play Again", 2);
        if (choice == 1){
            ResetGame();
            return true;
        }
        else{
            return false;
        }
    }

    private void ResetGame(){
        this.deck = new Deck();
        mainPlayer.getHand().clear();
        mainPlayer.setWager(0);
        dealer.getHand().clear();
    }

    //Calculates sum of card values
    //Returns sum
    private int CalcHandSum(Player player, List<Card> hand) {
        int sum = 0;
        int aceCounter = 0;

        for (Card card : hand) {
            if (card.cardRank == CardRank.ACE) {
                aceCounter++;
            }
            sum += card.cardValue;
            if (sum > 21 && hand.contains(CardRank.ACE)) {
                sum -= 10;
                aceCounter--;
            }
        }
        return sum;
    }
}

//TODO Handle a "push" situation
//TODO Blackjack method
//TODO Exceptions
