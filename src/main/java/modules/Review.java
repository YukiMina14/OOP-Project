package modules;

public class Review {

    public static final String EOL = System.lineSeparator();

    private final String itemID;
    private final int rating;
    private String comment = "";


    public Review(String itemID, int rating){
        this.itemID = itemID;
        this.rating = rating;
    }
    
    public Review(String itemID, int rating, String comment){
        this.itemID = itemID;
        this.rating = rating;
        this.comment = comment;
    }


    /**Getter and setter**/
    public String getItemID() {
        return itemID;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        return "Grade: " + rating + '.' + comment;
    }

}
