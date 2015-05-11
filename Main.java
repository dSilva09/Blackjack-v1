import java.util.Scanner;

/**
 * Created by Dom on 4/27/2015.
 */
public class Main {

    public static Scanner keyboard;

    public static void main(String[] args) {

        new Deck();

        System.out.println("Zootjack");

        int choice = MainMenu();

        switch (choice) {
            case 1:
                PregameLobby();
                break;
            case 2:
                break;
            case 2007:
                //game.Zoot();
                break;
        }
    }

    //Methods----------------------------------------

    private static void PregameLobby(){
        int startingBalance = 100;

        //Until choice for begin game is chosen
        while (true) {
            int choice = PregameMenu();
            if(choice == 2){
                startingBalance = setBalance();
            }
            else{
                break;
            }
        }
        BeginGame(startingBalance);
    }

    private static void BeginGame(int startingBalance) {
        //Set player name
        String name = NamePrompt();

        //Create new game and run it
        Game game = new Game(name, startingBalance);

        game.Play();
    }

    //Prompts user for name and sets input as current player's name
    public static String NamePrompt(){
        keyboard = new Scanner(System.in);
        String name;

        System.out.print("Name: ");
        name = keyboard.next();

        return name;
    }

    //Pregame menu options
    private static int PregameMenu() {
        int choice = MenuSelections("Pregame Menu", 2);
        return choice;
    }

    //Option to add AI players
    private static int AddAIPlayers(){
        int choice = MenuSelections("How Many AI", 4);
        return choice;
    }

    //Custom initial balance setting (up to 10000)
    private static int setBalance(){
        int choice = MenuSelections("Balance", 10000);
        return choice;
    }

    //Prompts player with menu
    private static int MainMenu() {
        int choice = MenuSelections("Main", 2);
        return choice;
    }

    //Prints menus
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
            case "Balance":
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

    //General menu behavior
    public static int MenuSelections(String menuType, int numberOfChoices){
        //Prints appropriate menu
        printMenu(menuType);

        //Initialize scanner and choice variable
        keyboard = new Scanner(System.in);
        int choice;

        //While a valid choice has not been selected
        while (true) {
            System.out.print("\nSelection: ");
            choice = keyboard.nextInt();

            //Choice validation
            //If special case, ZOOT, break
            if (menuType.equals("Main") && choice == 2007) {
                break;
            }
            //If evaluates to true,is an option, break
            else if (choice >= 1 && choice <= numberOfChoices){
                break;
            }
            //otherwise, print error message
            else {
                System.out.println("Not an option. Try again.");
            }
        }
        return choice;
    }
}

