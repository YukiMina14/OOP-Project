package menus;
import controllers.ItemController;
import controllers.TransactionController;
import utilities.ListSaver;
import utilities.UserInput;
public class TransactionHistoryMenu {

    private static final String EOL = System.lineSeparator();
    private final UserInput userInput;
    private TransactionController transactionController;
    private ItemController itemController;

    public TransactionHistoryMenu(ListSaver listSaver){
        transactionController   = new TransactionController(listSaver);
        itemController          = new ItemController(listSaver);
        userInput               = new UserInput();
    }


    public void transactionsHistoryOptions(){
        String input;

        do {
            System.out.print(
                    EOL +
                    "Transaction History options menu: "                    + EOL +
                    EOL +
                    "0. Return to Main Menu."                               + EOL +
                    "1. Print total profit from all item purchases."        + EOL +
                    "2. Print total units sold from all item purchases."    + EOL +
                    "3. Print the total number of item transactions made."  + EOL +
                    "4. Print all transactions made."                       + EOL +
                    "5. Print the total profit of a specific item."         + EOL +
                    "6. Print the number of units sold of a specific item." + EOL +
                    "7. Print all transactions of a specific item."         + EOL +
                    "8. Print item with highest profit."                    + EOL +
                    EOL +
                    "Type an option number: "
            );

            input = userInput.readString();
            switch ( input ) {
                case "0":                                   continue; //Goes back to main menu

                case "1": printTotalProfit();               break;

                case "2": printTotalUnits();                break;

                case "3": printTotalNumber();               break;

                case "4": printAllTransactions();           break;

                case "5": printSpecificProfit();            break;

                case "6": printSpecificSold();              break;

                case "7": printSpecificTransactions();      break;

                case "8": printHighestProfit();             break;

                default:
                    System.out.println("Invalid option. Please type in a valid option from 0-8.");
                    break;
            }
        }while (! input.equals("0"));
    }

    /************** METHODS ******************/

    // print total profit of all items
    private void printTotalProfit(){
        System.out.println("The total profit is " + transactionController.getTotalProfit() );
    }

    // print total units of all items
    private void printTotalUnits(){
        System.out.println("A total of units sold is " + transactionController.getTotalSales() + ".");
    }

    // print number of all the reviews that exist
    private void printTotalNumber(){
        System.out.println("A total of "+ transactionController.getTotalTransactions() +" Transactions is made.");
    }

    // prints all transaction from oldest to newest
    private void printAllTransactions(){
        System.out.println( transactionController.printAllTransactions() );
    }

    // print the profit of a specific review
    private void printSpecificProfit() {
        System.out.print("Please enter an item ID: ");
        String itemID = userInput.readString();         //userinput gets item id from user

        if (!itemController.IdExists(itemID)) {         // checks to see if item exists
            System.out.println("The item does not exist.");
        } else {
            System.out.println("The profit for item " + itemID + " is " + transactionController.getProfitSum(itemID));
        }
    }

    // prints the amount of sales of a specific item
    private void printSpecificSold(){
        System.out.print("Please enter an item ID: ");
        String itemID = userInput.readString();         //userinput gets item id from user

        if (itemController.IdExists(itemID)) {          //checks if id exists
            System.out.println("The item does not exist.");
        } else {
            System.out.println("The units sold for item " + itemID + " is " + transactionController.getSaleVolume(itemID) );
        }
    }

    //prints a specific transaction for an item
    private void printSpecificTransactions(){
        System.out.print("Please enter an item ID: ");
        String itemID = userInput.readString();             //userinput gets item id from user

        System.out.println( transactionController.printItemTransactions(itemID) );
    }

    //print the item with the highest profit
    private void printHighestProfit(){
        System.out.println( transactionController.printMostProfitableItem() );
    }

}
