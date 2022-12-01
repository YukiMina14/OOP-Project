package menus;

import utilities.ListSaver;
import utilities.UserInput;

public class MainMenu {

    public static final String EOL = System.lineSeparator(); //line Separator

    private final UserInput userInput; //final because we only want 1 private user input in main menu.
    public  final ListSaver listSaver;  //it needs to be final because nobody should be able to change it


    public MainMenu(){     //constructor of MainMenu
        this.userInput = new UserInput(); //creating UserInput object
        this.listSaver = new ListSaver(); //Instantiating ListSaver
    }

    public void mainMenu() {
        String input; //Using string to prevent the program from crashing if the user accidentally presses a character (e.g two,@,£...) instead of a literal number (int).

        do {
            System.out.print(
                    "Main Menu: Please choose among the options below." + EOL +
                            EOL +
                            "0. Close system." + EOL +
                            "1. Open Item options." + EOL +
                            "2. Open Review options." + EOL +
                            "3. Open Transaction History options." + EOL +
                            "4. Open Employee menu" + EOL +
                            EOL +
                            "Type an option number: ");

            input = userInput.readString();
            switch (input) {
                case "0":                                                                           continue; //skips this loop and continues with the next loop

                case "1": new ItemMenu(listSaver).itemMenu();                                       break; //break; stops the switch statement from checking the other cases bellow

                case "2": new ReviewMenu(listSaver).reviewMenu();                                   break;

                case "3": new TransactionHistoryMenu(listSaver).transactionsHistoryOptions();       break;

                case "4": new EmployeeMenu(listSaver).employeeMenu();

                default:
                    System.out.println("Invalid option... Please type in another option from 0-3"); //Should say 0-4, but we disable the employee menu because we didn't finnish it in time.
            }

        } while (!input.equals("0")); //exits/closes the program if 0 is inputted from the user.

    }//end of mainMenu()

}//end of MainMenu class


