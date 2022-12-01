package controllers;

import modules.*;
import utilities.ListSaver;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TransactionController {
    private final String EOL = System.lineSeparator();
    private final ItemController itemController;

    private final List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public TransactionController(ListSaver listSaver) {
        this.itemController = new ItemController(listSaver);
        this.transactions = listSaver.getTransactionList();
    }

    //get the amount of money a single item made
    public double getProfitSum(String itemID) {
        if (!itemController.IdExists(itemID)) return 0.0;

        DecimalFormat format = new DecimalFormat("#######0.00");
        format.setRoundingMode(RoundingMode.DOWN);
        double total = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.getItemID().equals(itemID)) total += transaction.getTotalPrice();
        }
        return Double.parseDouble(format.format(total));
    }

    // gets the amount of units a certain item has sold
    public int getSaleVolume(String itemID) {
        if (!itemController.IdExists(itemID)) return 0;

        int volume = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getItemID().equals(itemID)) volume += transaction.getQuantity();
        }
        return volume;
    }

    //gets the number of transactions a certain item was involved in
    public int getNumberOfTransactions(String itemID) {
        if (!itemController.IdExists(itemID)) return 0;

        int total = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getItemID().equals(itemID)) total += 1;
        }
        return total;
    }

    //gets the total profit of all items
    public double getTotalProfit() {
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getTotalPrice();
        }
        DecimalFormat format = new DecimalFormat("#######0.00");
        format.setRoundingMode(RoundingMode.DOWN);

        return Double.parseDouble(format.format(total));
    }

    //gets total of items sold
    public int getTotalSales() {
        int total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getQuantity();
        }

        return total;
    }

    //gets total amount of transactions
    public int getTotalTransactions() {
        return transactions.size();
    }

    //returns string of all transactions of a certain item
    public String printItemTransactions(String itemID) {
        if (!itemController.IdExists(itemID)) return "Item " + itemID + " was not registered yet.";

        StringBuilder result = new StringBuilder();
        DecimalFormat format = new DecimalFormat("#######0.00");
        format.setRoundingMode(RoundingMode.DOWN);
        Item item = itemController.getItem(itemID);

        result.append("Transactions for item: " + item.getItemId() + ": " + item.getName() + ". " + format.format(item.getPrice()) + " SEK" + EOL);

        if (hasTransactions(itemID)) {
            for (Transaction transaction : transactions) {
                if (transaction.getItemID().equals(itemID)) result.append(transaction.toString() + EOL);
            }
        } else {
            result.append("No transactions have been registered for item " + item.getItemId() + " yet.");
        }

        return result.toString();
    }

    //returns string of all transactions of every item
    public String printAllTransactions() {
        StringBuilder result = new StringBuilder();
        DecimalFormat format = new DecimalFormat("#######0.00");
        format.setRoundingMode(RoundingMode.DOWN);

        result.append("All purchases made: " + EOL)
                .append("Total profit: " + format.format(getTotalProfit()) + " SEK" + EOL)
                .append("Total items sold: " + getTotalSales() + " units" + EOL)
                .append("Total purchases made: " + getTotalTransactions() + " transactions" + EOL)
                .append("------------------------------------" + EOL);

        for (Transaction transaction : transactions) {
            result.append(transaction.toString() + EOL);
        }

        result.append("------------------------------------" + EOL);
        return result.toString();
    }

    //returns string of item with the most profit
    public String printMostProfitableItem() {
        if (itemController.getItems().isEmpty()) return "No items registered yet.";
        if (transactions.isEmpty()) return "No items were bought yet.";

        List<String> theTop = getMostProfitableItem();

        StringBuilder result = new StringBuilder();
        DecimalFormat format = new DecimalFormat("#######0.00");  // creates decimal format # means empty if the number is to small 0 can be any number
        format.setRoundingMode(RoundingMode.DOWN);
        // prints to 2 decimals
        result.append("Most profitable items: " + EOL)
                .append("Total profit: " + format.format(getProfitSum(theTop.get(0))) + " SEK" + EOL);


        for (String ID : theTop) {
            result.append(itemController.getItem(ID).toString()).append(EOL);
        }

        return result.toString();
    }

    //returns list if item ID's of the most profitable item / items if they are tied
    public List<String> getMostProfitableItem() {
        List<String> IDList = getAllTransactionItemID();
        List<String> theTop = new ArrayList<>();

        for (String ID : IDList) {
            double transactionProfit = getProfitSum(ID);

            if (!theTop.isEmpty()) {
                double topProfit = getProfitSum(theTop.get(0));

                if (transactionProfit >= topProfit) {
                    if (transactionProfit > topProfit) theTop.clear();
                    theTop.add(ID);
                }
            } else {
                theTop.add(ID);
            }

        }

        return theTop;
    }


    //gets all ids of items that have transactions
    public List<String> getAllTransactionItemID() {
        List<String> tempList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            String ID = transaction.getItemID();
            if (!tempList.contains(ID)) tempList.add(ID);
        }


        return tempList;
    }

    //checks if item has transactions
    public boolean hasTransactions(String itemID) {
        for (Transaction transaction : transactions) {
            if (transaction.getItemID().equals(itemID)) return true;
        }
        return false;
    }

}