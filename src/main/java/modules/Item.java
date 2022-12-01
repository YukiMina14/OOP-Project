package modules;

import java.text.DecimalFormat;

public class Item {

    public static final String EOL = System.lineSeparator();

    private final String itemID;
    private String name;
    private double price;

    public Item(String itemID, String name, double price) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
    }

    public String getItemId() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
        

    @Override
    public String toString() {      // toString() is a standard method of the Object class that's why we override it
        DecimalFormat formatter = new DecimalFormat("######0.00");
        String printPrice = formatter.format(this.price);

        return this.itemID + ": " + this.name + ". " + printPrice + " SEK";
    }


}