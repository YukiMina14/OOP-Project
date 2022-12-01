package controllers;

import modules.Item;
import modules.Transaction;
import utilities.ListSaver;

import java.util.List;

public class ItemController {
    private final String EOL = System.lineSeparator();

    public ItemController(ListSaver listSaver){
        this.items = listSaver.getItemList();
        this.transactions = listSaver.getTransactionList();
    }

    private List<Item> items;// = listSaver.itemList; //We don't want other classes to be able to edit this. Because
    private List<Transaction> transactions;          //review menu and transaction history men still menu still need
                                                    //the info from item list, we create a getter method.
                                                    //however, they should not be able to change the item from
                                                    //outside the item class, so we don't create a setter method.


    public List<Item> getItems() { //getter method for list instead of variable
        return items;
    }

    public Item getItem(String Id){

        for(Item item : items){
            if(item.getItemId().equals(Id)) return item;
        }
        return null;
    }


    // get items from arraylist items and print them out one by one, print value instead of object reference, format correctly
    public String createItem(String itemId, String itemName, double price) {
        // Will check to see if the item ID is empty or the item name is empty or the price is <= 0
        if (itemId.isEmpty() || itemName.isEmpty() || price <= 0) {
            return "Invalid data for item.";
        }
            /* If that's all clear, loops through all the items in the arraylist to see if the item ID is the same
               as the ID you inputted into your method
            */
        for(Item item : items){
            if(item.getItemId().equals(itemId)){
                return "Two Items cannot have the same ID.";
            }
        }

            // Otherwise, create a new item and add to the ItemController arraylist.
        items.add( new Item(itemId, itemName, price) ); // creates a new item and ad it to a list of items called "items"
        return "Item " + itemId + " was registered successfully.";

    }

    
    //buys item
    public double buyItem(String itemId, int quantity ){
        if(!IdExists(itemId)) return -1.0;                      //checks if id exists

        Double totalPrice = 0.0;                                //double because we want it to have decimal numbers.
        Double itemPrice = getItem(itemId).getPrice();

        if(quantity <= 4){
            totalPrice += ( itemPrice * quantity);
        }else {
            totalPrice += ( itemPrice * 4);
            totalPrice += ( itemPrice * (quantity - 4) * 0.7);     //gives user a 30%discounts if they buy 4 items.
        }

        transactions.add(new Transaction(itemId, quantity, itemPrice, totalPrice));


        return  (double) ((int)((totalPrice) * 100 ))/ 100;
    }


    // Removes an item from arraylist
    public String RemoveItem(String itemId){
        if(itemId.isEmpty()) {
            return "Invalid data for item.";
        }else {

            for (Item index : items) { //for every Item index in our array of items // could also do it with a for loop but would be more text, this is easier to read called a for each loop sometimes also referred to as enhanced for loop.
                if (index.getItemId().equals(itemId)) {
                    items.remove(index);
                    return "Item "+ itemId +" was successfully removed.";
                }
            }
            return "Item " + itemId + " could not be removed.";
        }
    }


    // Checks if ID Exists in the arraylist items
    public boolean IdExists(String Id){
        for(Item item : items) {  //New Ref called item of type Item to the list items.
            if(item.getItemId().equals(Id)) {
                return true;
            }
        }
        return false;
    }

    //returns an item name
    public String getItemName(String Id){
        for(Item item : items){
            if(item.getItemId().equals(Id)) return item.getName();
        }


        return "no such item ID";
    }


    //Prints all items
    public String printAllItem() {
        if( items.isEmpty() ) return "No items registered yet.";

        StringBuilder result = new StringBuilder();//String builder is a java standard class, no need for us to make the class

        result.append("All registered items:" + EOL);

        for(Item item: items) { //it goes through a list of items through a loop
            result.append(item.toString() + EOL); //and makes a string of all the objects it finds "toString method".

        }

        return result.toString(); //returns the list of items it can find as a string.
    }


    //Print a specific Item.
    public String printAnSpecificItem(String Id){
        for(Item item : items){
            if (item.getItemId().equals(Id)) {
                return item.toString();
            }
        }
        return "Item " + Id +  " was not registered yet.";
    }


    // Updates an item name
    public String updateItemName(String Id, String newName){

        if (!IdExists(Id)) return "Item "+ Id +" was not registered yet.";

        if (newName.isBlank()) return "Invalid data for item.";

        // for each Item(index) in items
        for(Item index : items){ //for each loop
            if(index.getItemId().equals(Id)){
                index.setName(newName);
                return "Item " +Id+ " was updated successfully.";
                //the "\" before "'" is not needed, but it's good practice in case of situations like this (\").
                // It tells java you want to type special characters

            }
        }
        return "Item "+ Id +" was not registered yet.";
    }

    // Updates an item Price
    public String updateItemPrice(String Id, double newPrice){

        if(!IdExists(Id)) return "Item "+ Id +" was not registered yet.";

        if(newPrice < 0.01) return "Invalid data for item.";

        // for each Item(index) in items
        for(Item index : items){ //for each loop
            if(index.getItemId().equals(Id)){
                index.setPrice(newPrice);
                return "Item " +Id+ " was updated successfully.";
                //the "\" before "'" is not needed, but it's good practice in case of situations like this (\").
                // It tells java you want to type special characters

            }
        }
        return "Item "+ Id +" was not registered yet.";


    }




}// end of itemController
