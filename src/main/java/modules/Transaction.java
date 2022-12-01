package modules;

import java.text.DecimalFormat;

public class Transaction {

    public static final String EOL = System.lineSeparator();

    private final String itemID;
    private int quantity;
    private double price;
    private double totalPrice;


    public Transaction(String itemID, int quantity, double price, double totalPrice) {

        this.itemID = itemID;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;

    }
    
    @Override
    public String toString(){
        DecimalFormat format = new DecimalFormat("#0.00");
        String result = format.format(this.totalPrice);
        
        return this.itemID + ": "+ this.quantity +" item(s). "+ result  +" SEK";
    }
    
    

    public String getItemID() {
        return itemID;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return this.totalPrice;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setTotalPrice(double totalPrice) {
        if (quantity > 4) {
            this.price = (price * (quantity - 4) * 0.7);
        } else if (quantity < 4) {
            this.price = (price * quantity);
        }
    }

//* all these are not finished hihi just straight from facade to remind me what im doing after this
    public int getTotalTransactions () {
        return -1;
    } // this one shoul prob be in controler

    public double getProfit (String itemID){
        return 0.0;
    }//trans.itemHistoryProfit(itemID)

    public int getUnitsSold (String itemID){
        return -1;
    }

        //public String printItemTransactions (String itemID){
        //return }


}


