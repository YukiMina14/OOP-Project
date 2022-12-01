package menus;

import controllers.ItemController;

import modules.Item;
import utilities.ListSaver;
import utilities.UserInput;


public class ItemMenu {

    private static final String EOL = System.lineSeparator(); //makes it easier to make a line separator, also makes it more readable.

    private UserInput userInput;
    private ItemController itemController;

    ItemMenu(ListSaver listSaver){ //constructor for ItemMenu
        userInput = new UserInput(); //instantiating UserInput
        itemController = new ItemController(listSaver); // Instantiating ItemController
    }

    public void itemMenu() {
        String input;

        do {

            System.out.print(
                    EOL +
                            "Items Options Menu: " + EOL +
                            "0. Return to Main Menu." + EOL +
                            "1. Create an Item." + EOL +
                            "2. Remove an Item." + EOL +
                            "3. Print all registered Items." + EOL +
                            "4. Buy an Item." + EOL +
                            "5. Update an Item's name." + EOL +
                            "6. Update an Item's price." + EOL +
                            "7. Print a specific item." + EOL +
                            EOL +
                            "Type an option number: "
            );

            input = userInput.readString();
            switch ( input ) { // stylistic choice:  we like to have cases and break in 1 line, it is more readable for us
                case "0":                               continue; //skips this loop continues with the next

                case "1": CreateItem();                 break; //created a method to this further down (line 67).
                                                                // looks cleaner when everything is not clustered.

                case "2": RemoveItem();                 break; //Removes an item

                case "3": PrintAllItems();              break; //prints all items

                case "4": BuyItem();                    break; //Buys item

                case "5": UpdateItemName();             break; //updates an item's name.

                case "6": UpdateItemPrice();            break; //Updates an item's price.

                case "7": PrintASpecificItem();         break; //prints a specific item.

                default:
                    System.out.println("Going back to Main Menu... Please type in another option from 0-7");
                    break;
            }
        } while (!input.equals("0"));
    }


    /*****************METHODS*******************/

    ///Creates an Item///--
    private void CreateItem() {
        System.out.println("1. Creating an item:\n"
                + "Input item ID, name and price:");


        String itemID = userInput.readString(); //scanner reads the user input.
        String itemName = userInput.readString();
        double unitPrice = userInput.readDouble();

        System.out.println( this.itemController.createItem(itemID, itemName, unitPrice) );  //itemController.createItem returns a string with then gets printed out, for feedback :)



    }  //we make it private so that other classes can't interfere/use it.
    //we tailored this to the switch statement above.
    //if other classes want to create an item,
    //they can just use the ItemController ("The bare minimum of creating an item").

    ///Removes an Item///--
    private void RemoveItem() {

        //Not needed -> UserInput input = new UserInput();
        System.out.print("2.Remove an item" + EOL +
                "Enter an ID: ");
        String itemID = userInput.readString();  //Don't need to create a named object called input as in "private void CreateItem", -->
        //-> because we only use method once. Only need to remove an ID to remove the item.
        System.out.println(this.itemController.RemoveItem(itemID));  // same as in line 76 but removes an item
    }

    ///Print all registered Items.
    private void PrintAllItems(){
        System.out.println("3. Print all registered Items. " + EOL);

        if (itemController.getItems().isEmpty()) {
            System.out.println("No items registered yet");
            return;
        }

        System.out.println(this.itemController.printAllItem());
    }

    ///Print a specific item.
    private void PrintASpecificItem(){
        System.out.println("7. Print a specific Item");

        String ID = userInput.readString();
        if(!this.itemController.IdExists(ID)){
            System.out.println("Item was not registered yet.");
            return;
        }
        System.out.println(this.itemController.printAnSpecificItem(ID));
    }

    ///Update Item Name///--
    private void UpdateItemName() {
        System.out.print("5. Update an Item's name." + EOL +
                "Enter an ID: ");

        String ID = userInput.readString();
        if (!this.itemController.IdExists(ID)) {
            System.out.println("ID " + ID + "is not a valid ID.");
            return;         //Sends the user back to the item menu. In cases where the user forgets an ID  and want
            //to reprint all the IDs to check what the ID was, or else they will be stuck in a loop
            // until they type a valid ID. and you can get in a situation where there are no valid ID's :o
        }

        System.out.print("Enter a new Name: ");
        String name = userInput.readString();

        System.out.println( this.itemController.updateItemName(ID, name));
    }

    ///Update Item Price///--
    private void UpdateItemPrice() {
        System.out.print("6. Update an Item's price." + EOL +
                "Enter an ID: ");

        String ID = userInput.readString();
        if (!this.itemController.IdExists(ID)) {
            System.out.println("ID" + ID + "is not a valid ID.");
            return; //Sends the user back to the item menu. In cases where the user forgets an ID  and want
            //to reprint all the IDs to check what the ID was, or else they will be stuck in a loop
            // until they type a valid ID.

        }

        System.out.println("Enter a new Price: ");
        double price = userInput.readDouble();

        System.out.println(this.itemController.updateItemPrice(ID, price));

    }

    ///Buys Item///--
    private void BuyItem(){
        System.out.print(   "4. Buy an Item." + EOL +
                            "Enter an ID: "     );

        String itemID = userInput.readString();

        if(!itemController.IdExists(itemID)){
            System.out.println("ID does not exist.");
            return;
        }

        System.out.print("How many would you like?: ");
        int quantity = userInput.readInt();

        Item temp = itemController.getItem(itemID);
        System.out.println( "Bought " + quantity + " of item " + temp.getItemId() + "." + temp.getName() + " for a total of "
                            + itemController.buyItem(itemID, quantity) + " SEK.");

        return;
    }

}
