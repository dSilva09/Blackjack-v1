import java.util.List;
import java.util.Scanner;

/**
 * Created by Dom on 4/27/2015.
 */
public class Game {

    //Fields ----------------------------------------
    private Player humanPlayer;
    private Dealer dealer;
    private Deck deck;
    Scanner keyboard = new Scanner(System.in);

    //Constructor----------------------------------------
    public Game(String playerName, int startingBalance) {
        this.humanPlayer = new Player(playerName, startingBalance);
        this.dealer = new Dealer();
        this.deck = new Deck();
    }

    //Public Methods ----------------------------------------

    /**\
     * Procedure used for a single round of Blackjack:
     * The deck is shuffled and cards are dealt to both the human player and the dealer.
     *
     * The dealer's hand is checked for Blackjack. Because the dealer automatically
     * wins in a Blackjack situation, the check is performed before a round begins
     * to prevent the human player continuing without reason.
     *
     * The human player runs through their turn.
     *
     * If the human player has not busted and has not gotten Blackjack,
     * then the dealer runs through its turn and the the two players hands are evaluated.
     *
     * If the main player still has a balance after the turn, then they have the option to play again or quit.
     * Else, they have run out of money and the game ends.
     */
    public void Play() {
        boolean keepPlaying = true;

        while (keepPlaying && humanPlayer.hasBalance()) {
            //Shuffle deck
            deck.Shuffle();

            //Print humanPlayer's balance
            System.out.println("Balance: " + humanPlayer.getBalance());

            //Prompt and set wager for round
            humanPlayer.Wager();

            //Deal cards
            Deal(humanPlayer, dealer);

            //Check if dealer has Blackjack, main player loses if true
            if (CheckForBlackjack(dealer)) {
                System.out.println("Dealer has Blackjack.");
                System.out.println("You lose :/");
                dealer.hasBlackjack = true;
                Lose(humanPlayer);
                break;//<--Wrong; ends game completely
            }

            //Gameplay:
            //Main Player's Turn
            HumanPlayersTurn();
            int mainPlayerHandSum = humanPlayer.getHandSum();

            //If the main player hasn't busted and has not gotten Blackjack
            if (!humanPlayer.isBust() && !humanPlayer.hasBlackjack) {
                //Dealer's turn
                DealersTurn();
                int dealerHandSum = dealer.getHandSum();

                //Evaluation (for players who haven't already busted)
                //For humanPlayer
                if (!dealer.isBust() && !humanPlayer.isBust()) {
                    if (mainPlayerHandSum == dealerHandSum) {
                        System.out.println("Push");
                    } else if (mainPlayerHandSum > dealerHandSum) {
                        Win(humanPlayer);
                    } else {
                        Lose(humanPlayer);
                    }
                } else {
                    Win(humanPlayer);
                }
            }
            if (humanPlayer.hasBalance()) {
                keepPlaying = PlayAgain();
            }
            else {
                System.out.println("You ran outta cash!");
                System.out.println("Maybe next time :/");
            }
        }
    }

    /**
     * Checks a player's hand for Blackjack.
     * @param player a human player or an AI dealer player
     * @return true if the player has Blackjack and false if they do not.
     */
    public boolean CheckForBlackjack(Player player) {

        if (CalcHandSum(player, player.getHand()) == 21 && player.hasAnAce(player.getHand())) {
            return true;
        }
        else{
            return false;
        }
    }

    //Private Methods ----------------------------------------

    /**
     * Procedure for the human player:
     * A check for Blackjack is done right away to prevent the round from continuing without reason.
     * The player wins if they have Blackjack
     *
     * Calculate the sum of the player's hand and set it to their current hand sum.
     *
     * While the player has not chosen to "Stay," "Hit".
     * If the player hits, check to see if they busted.
     */
    //Controls course of action for human players
    private void HumanPlayersTurn() {

        if(CheckForBlackjack(humanPlayer)){
            System.out.println("You got Blackjack!");
            humanPlayer.hasBlackjack = true;
            Win(humanPlayer);
        }
        humanPlayer.setHandSum(CalcHandSum(humanPlayer, humanPlayer.getHand()));

        //Until bust or stay
        while (true) {

            System.out.println("Currently at: " + humanPlayer.getHandSum());
            int choice = humanPlayer.HitPrompt();
            //If "hit," return the sum and add another card to the humanPlayer's hand
            if (choice == 1) {
                humanPlayer.Hit(humanPlayer, deck);
                humanPlayer.setHandSum(CalcHandSum(humanPlayer, humanPlayer.getHand()));

                //Check to see if humanPlayer busts
                if (IsBust(humanPlayer)) {
                    System.out.println("You busted at " + humanPlayer.getHandSum());
                    Lose(humanPlayer);
                    humanPlayer.isBust = true;
                    break;
                }
            }
            //If "stay," return the sum and exit loop
            else {
                System.out.println("You stayed at " + humanPlayer.getHandSum());
                humanPlayer.setHandSum(humanPlayer.getHandSum());
                break;
            }
        }
    }

    /**
     * Behavior for dealer:
     * Calculate the sum of the dealer's hand and set it to their current hand sum.
     *
     * While the dealer's hand has not summed to 17, keep hitting.
     *
     * Check if they bust after the hand sum has reached 17 or higher.
     * If it they have not, then set the dealer's hand sum.
     */
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

    /**
     * Deals cards to players one at a time starting with the human player.
     * @param player the human player
     * @param dealer the dealer
     */
    private void Deal(Player player, Dealer dealer) {

        //Adds the top card of the deck to each player's hand 1 at a time.
        for (int i = 0; i < 2; i++) {
            player.getHand().add(player.TakeTopCard(deck));
            dealer.getHand().add(dealer.TakeTopCard(deck));
        }
        //Displays the top card of the dealer's hand
        dealer.ShowTopCard(dealer.getHand());
    }

    /**
     * Checks if the sum of a player's hand is greater than 21.
     * @param player a human player or an AI dealer player
     * @return true if the player busts, else return false
     */
    private boolean IsBust(Player player) {
        if (CalcHandSum(player, player.getHand()) > 21) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * The appropriate course of action for a loss. Subtracts the human player's wager from their balance
     * and prints a message informing them that they have lost and what their current balance is.
     * @param player the human player
     */
    private void Lose(Player player){
        player.setBalance(player.getBalance() - player.getWager());
        System.out.println();
        System.out.println(player.getName() + " lost :/");
        System.out.println(player.getName() + "'s" + " balance is now " + player.getBalance());
    }

    /**
     * The appropriate course of action for a win. Adds the human player's wager to their balance and
     * prints a message informing them that have have won and what their current balance is.
     * @param player the human player
     */
    private void Win(Player player){
        player.setBalance(player.getBalance() + player.getWager() * 1.5);
        System.out.println();
        System.out.println(player.getName() + " wins!");
        System.out.println(player.getName() + "'s" + " balance is now " + player.getBalance());
    }

    /**
     * Prompts human player to play again or quit.
     * Calls the HandleInput method and passes in the correct name of the prompt list
     * and the number of selections that will be prompted.
     * Reset game if they choose to play again.
     * @return true if "Play again", else return false
     */
    private boolean PlayAgain() {
        int choice = Main.HandleInput("Play Again", 2);
        if (choice == 1){
            ResetGame();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Resets the initial game settings:
     * Deck
     * Human player's hand
     * Human player's wager
     * Dealer's hand
     */
    private void ResetGame(){
        this.deck = new Deck();
        humanPlayer.getHand().clear();
        humanPlayer.setWager(0);
        dealer.getHand().clear();
    }

    /**
     * Calculates the sum of a player's hand. Because an Ace can have two values (11 by default and 1 in a special case),
     * an ace counter and an additional check for the hand sum is added.
     * Checks to see if the player has an ace an increments a counter if they do.
     * Checks to see if the player has an ace with a sum of over 21; subtracts 10 from the hand sum (equivalent to setting
     * the value of an Ace to 1) and decrements the Ace counter by 1.
     * @param player a human player or an AI dealer player
     * @param hand the player's hand
     * @return the sum of the player's hand
     */
    private int CalcHandSum(Player player, List<Card> hand) {
        int sum = 0;
        int aceCounter = 0;

        for (Card card : hand) {
            if (card.cardRank == CardRank.ACE) {
                aceCounter++;
            }
            sum += card.cardValue;
            if (sum > 21 && card.cardRank == CardRank.ACE) {
                sum -= 10;
                aceCounter--;
            }
        }
        return sum;
    }
}
//TODO Fix Lose situation when dealer gets Blackjack from the start
//TODO How can the CheckCardType be used in the ShopTopCard method? (Can't override)
//TODO A player's hand must be reset after an Ace value changes to prevent problems during evaluation.
//TODO Exceptions
