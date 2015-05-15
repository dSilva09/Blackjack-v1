import java.util.Scanner;

/**
 * Created by Dom on 4/27/2015.
 */

/**
 * Primary method for Blackjack game.
 * The initial game options are listed upon startup and allow the player to:
 * Begin a game
 * Set an initial balance
 * Quit
 */
public class Main {

    public static Scanner keyboard;

    public static void main(String[] args) {

        System.out.println("Welcome to Blackjack");

        int choice = StartMainMenu();

        switch (choice) {
            case 1:
                PregameLobby();
                break;
            case 2:
                break;
        }
    }

    //Public Methods ----------------------------------------

    /**
     * Prints a menu of a corresponding menu type.
     * To avoid scattered menu methods, all of the menus used
     * in the game were consolidated to one method and processed
     * using a switch statement
     * 6 menus total
     * @param menuType a string that describes the menu type to be called
     */
    public static void printMenu(String menuType) {

        switch(menuType){
            case "Main":
                System.out.println("\nChoose an option: ");
                System.out.println("1) Start a new game");
                System.out.println("2) Quit");
                break;
            case "Pregame Menu":
                System.out.println("1) Begin game");
                System.out.println("2) Set a custom starting balance?");
                break;
            case "How Many AI":
                System.out.println("How many AI players would you like to add? (Up to 4)");
                break;
            case "Starting Balance":
                System.out.println("Custom starting balance (Up to $10,000)");
                break;
            case "Hit":
                System.out.println("1) Hit");
                System.out.println("2) Stay");
                break;
            case "Play Again":
                System.out.println("1) Play again?");
                System.out.println("2) Quit");
        }
    }

    /**
     * Handles an users input for every type of menu.
     * To avoid repetitive input selection blocks, the logic used to
     * check if user input is valid was consolidated to a single method.
     * 'menuType' is used to print the correct menu while 'numberOfChoices'
     * is used to bound a choice to a valid selection.
     * A while loop is used to allow a user to keep inputting until a
     * valid choice is selected. An error message is printed if an invalid choice is made.
     * @param menuType a string containing the menu type
     * @param numberOfChoices the number of choices that correspond to a particular menu selection
     * @return the user selected choice
     */
    public static int HandleInput(String menuType, int numberOfChoices){
        //Prints appropriate menu
        printMenu(menuType);

        //Initialize scanner and choice variable
        keyboard = new Scanner(System.in);
        int choice;

        //While a valid choice has not been selected
        while (true) {
            System.out.print("\nSelection: ");
            choice = keyboard.nextInt();

            //If evaluates to true,is an option, break
            if (choice >= 1 && choice <= numberOfChoices){
                break;
            }
            //otherwise, print error message
            else {
                System.out.println("Not an option. Try again.");
            }
        }
        return choice;
    }

    /**
     * Prompts the user for a name.
     * @return the string a user has inputted
     */
    public static String NamePrompt(){
        keyboard = new Scanner(System.in);
        String name;

        System.out.print("Name: ");
        name = keyboard.next();

        return name;
    }

    //Private Methods ----------------------------------------

    /**
     * Sets the default starting balance and prompts the user to:
     * Begin game
     * Set a custom balance
     */
    private static void PregameLobby(){
        int startingBalance = 100;

        //Until choice for begin game is chosen
        while (true) {
            int choice = StartPregameMenu();
            if(choice == 2){
                startingBalance = SetInitialBalance();
            }
            else{
                break;
            }
        }
        BeginGame(startingBalance);
    }

    /**
     * Starts a new game.
     * The user is prompted for a name and a new game is constructed using the
     * name and starting balance of the user. The play method runs the game.
     * @param startingBalance the balance a human player will start with
     */
    private static void BeginGame(int startingBalance) {
        //Set player name
        String name = NamePrompt();

        //Create new game and run it
        Game game = new Game(name, startingBalance);

        game.Play();
    }

    /**
     * For the following 3 methods:
     * Used to print and prompt the user of the appropriate choices.
     * StartPregameMenu:
     * Prompts user to begin or set initial balance.
     *
     * SetInitialBalance:
     * User defined initial balance setting (up to $10000).
     *
     * StartMainMenu:
     * Prompts user to begin game or quit.
     * @return the user's choice
     */
    private static int StartPregameMenu() {
        int choice = HandleInput("Pregame Menu", 2);
        return choice;
    }

    private static int SetInitialBalance(){
        int choice = HandleInput("Starting Balance", 10000);
        return choice;
    }

    private static int StartMainMenu() {
        int choice = HandleInput("Main", 2);
        return choice;
    }
}

