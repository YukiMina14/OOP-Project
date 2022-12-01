package controllers;

import modules.*;
import utilities.ListSaver;

import java.util.List;
import java.util.ArrayList;


public class ReviewController {
    private final String EOL = System.lineSeparator();
    private final ItemController itemController;



    /**List that saves reviews**/
    protected final List<Review> reviews;// = listSaver.reviewList;


    /**list getter**/
    public List<Review> getReviews() {
        return reviews;
    }

    /**Constructor**/
    public ReviewController(ListSaver listSaver){
        this.itemController = new ItemController(listSaver);
        this.reviews     = listSaver.getReviewList();
    }



    /******************** Methods ************************/
    //create review
    public String createReview(String itemID, int Grade, String Comment){
        if( ! itemController.IdExists(itemID) ){
            return "Item " + itemID + " was not registered yet.";
        }

        if(Grade < 0 || Grade > 5) {      // if grade is less than 0 or grade is over 5. (|| means or)
            return "Grade values must be between 1 and 5.";
        }

        if(Comment.isBlank()){ //tests if it's empty or only blank spaces
            reviews.add( new Review(itemID, Grade) );

        }else{
            reviews.add( new Review(itemID, Grade, Comment) );
        }


        return  "Your item review was registered successfully.";
    }

    //print a specific review
    public String printSpecificReview(String itemID, int index){
        if( ! itemController.IdExists(itemID) ) return "Item "+ itemID +" was not registered yet.";

        if( ! hasReviews(itemID) ) return "Item "+  itemController.getItem(itemID).getName() +" has not been reviewed yet.";

        int lastIndex = getReviewAmount(itemID);

        if(index < 1 || index > lastIndex) return "Invalid review number. Choose between 1 and "+ (lastIndex) +".";

        int i = 1;
        for(Review review : reviews){
            if(review.getItemID().equals(itemID)){
                if(i == index){
                    return review.toString();
                }else {
                    i++;
                }
            }
        }

        return "what?";
    }

    //print all reviews of an item
    public String printAllReviewsForItem(String itemID){
        if(! itemController.IdExists(itemID)) return "Item " + itemID + " was not registered yet.";

        StringBuilder result = new StringBuilder();

        result.append( "Review(s) for " ).append( itemController.getItem(itemID) ).append( EOL );

        if(! hasReviews(itemID)){
            result.append("The item "+ itemController.getItem(itemID).getName() +" has not been reviewed yet.");
        }else {
            for (Review review : reviews) {
                if (review.getItemID().equals(itemID)) result.append( review.toString() + EOL);
            }
        }

        return result.toString();
    }

    //print mean grade
    public double getMeanGrade(String itemID){
        int total = 0, count = 0;
        for(Review review : reviews){
            if( review.getItemID().equals(itemID) ){
                total += review.getRating();
                count++;
            }
        }

        return  (double) ( (int)( ( (double)total / (double) count) * 10 ) ) / 10 ; //truncate to 1 decimal
    }

    //print all comments of item
    public List<String> getAllComments(String itemID){
        List<String> tempList = new ArrayList<>();

        for(Review review : reviews){
            if(review.getItemID().equals(itemID) && ! review.getComment().isBlank()){
                tempList.add(review.getComment());
            }
        }

        return tempList;
    }

    //print all registered reviews
    public String printAllReviews(){
        if(itemController.getItems().isEmpty()) return "No items registered yet.";
        if(reviews.isEmpty())                   return "No items were reviewed yet.";

        List<String> ReviewedID = getAllReviewedItemID();

        StringBuilder result = new StringBuilder();

        result.append("All registered reviews:").append(EOL);
        result.append("------------------------------------").append(EOL);

        for ( String ID : ReviewedID ){
            result.append( printAllReviewsForItem(ID) );
            result.append("------------------------------------").append(EOL);
        }

        return result.toString();
    }

    //get the Most reviewed items
    public List<String> getMostReviewedItem(){
        List<String> IDList = getAllReviewedItemID();
        List<String> theTop  = new ArrayList<>();

        for(String ID : IDList){
            int ReviewNumber = getReviewAmount(ID);

            if(!theTop.isEmpty()) {
                int topReviewAmount = getReviewAmount(theTop.get(0));

                if(ReviewNumber >= topReviewAmount){
                    if(ReviewNumber > topReviewAmount) theTop.clear();
                    theTop.add(ID);
                }
            }else {
                theTop.add(ID);
            }

        }

        return theTop;
    }

    //print item with the most reviews
    public String printMostReviewedItem(){
        if(itemController.getItems().isEmpty()) return "No items registered yet.";
        if(reviews.isEmpty())                   return "No items were reviewed yet.";

        List<String> theTop = getMostReviewedItem();
        
        StringBuilder result = new StringBuilder();
        result.append("Most reviews: " + getReviewAmount( theTop.get(0) ) +" review(s) each."+ EOL);
        
        for (String ID : theTop){
            result.append( itemController.getItem(ID).toString() ).append(EOL);
        }
        
        return result.toString();
    }

    //get the Least reviewed item
    public List<String> getLeastReviewedItem(){
        List<String> IDList = getAllReviewedItemID();
        List<String> theBottom  = new ArrayList<>();

        for(String ID : IDList){
            int ReviewNumber = getReviewAmount(ID);

            if(!theBottom.isEmpty()) {
                int topReviewAmount = getReviewAmount(theBottom.get(0));

                if(ReviewNumber <= topReviewAmount){
                    if(ReviewNumber < topReviewAmount) theBottom.clear();
                    theBottom.add( 0 ,ID);
                }
            }else {
                theBottom.add(ID);
            }

        }

        return theBottom;
    }

    //print item with the least reviews
    public String printLeastReviewedItem(){
        if(itemController.getItems().isEmpty()) return "No items registered yet.";
        if(reviews.isEmpty())                   return "No items were reviewed yet.";

        List<String> theBottom = getLeastReviewedItem();

        StringBuilder result = new StringBuilder();
        result.append("Least reviews: " + getReviewAmount( theBottom.get(0) ) +" review(s) each."+ EOL);

        for (String ID : theBottom){
            result.append( itemController.getItem(ID).toString() ).append(EOL);
        }

        return result.toString();
    }

    //get best mean grade
    public List<String> getBestMeanGrade(){
        List<String> IDList = getAllReviewedItemID();
        List<String> bestMean  = new ArrayList<>();

        for(String ID : IDList){
            double ReviewMean = getMeanGrade(ID);

            if(!bestMean.isEmpty()) {
                double topReviewMean = getMeanGrade(bestMean.get(0));

                if(ReviewMean >= topReviewMean){
                    if(ReviewMean > topReviewMean) bestMean.clear();
                    bestMean.add(ID);
                }
            }else {
                bestMean.add(ID);
            }

        }

        return bestMean;
    }

    //get worst mean grade
    public List<String> getWorstMeanGrade(){
        List<String> IDList = getAllReviewedItemID();
        List<String> worstMean  = new ArrayList<>();

        for(String ID : IDList){
            double ReviewMean = getMeanGrade(ID);

            if(!worstMean.isEmpty()) {
                double bottomReviewMean = getMeanGrade(worstMean.get(0));

                if(ReviewMean <= bottomReviewMean){
                    if(ReviewMean < bottomReviewMean) worstMean.clear();
                    worstMean.add(ID);
                }
            }else {
                worstMean.add(ID);
            }

        }

        return worstMean;
    }



    //item with best mean grade
    public String printBestMeanGrade(){
        if(itemController.getItems().isEmpty()) return "No items registered yet.";
        if(reviews.isEmpty())                   return "No items were reviewed yet.";

        List<String> bestMean = getBestMeanGrade();

        StringBuilder result = new StringBuilder();
        result.append("Items with best mean reviews:").append(EOL);
        result.append("Grade: " + getMeanGrade( bestMean.get(0) ) ).append(EOL);

        for (String ID : bestMean){
            result.append( itemController.getItem(ID).toString() ).append(EOL);
        }

        return result.toString();
    }

    //print item with worst mean grade
    public String printWorstMeanGrade(){
        if(itemController.getItems().isEmpty()) return "No items registered yet.";
        if(reviews.isEmpty())                   return "No items were reviewed yet.";

        List<String> worstMean = getWorstMeanGrade();

        StringBuilder result = new StringBuilder();
        result.append("Items with worst mean reviews:").append(EOL);
        result.append("Grade: " + getMeanGrade( worstMean.get(0) ) ).append(EOL);

        for (String ID : worstMean){
            result.append( itemController.getItem(ID).toString() ).append(EOL);
        }

        return result.toString();
    }



    // returns the amount of reviews
    public int getReviewAmount(String itemID){
         int count = 0;
         for(Review review : reviews){
             if(review.getItemID().equals(itemID)) count++;
         }
         return count;
    }

    //returns list of strings, of all the id's that have reviews
    public List<String> getAllReviewedItemID(){
        List<String> tempList = new ArrayList<>();

        for(Review review : reviews){
            String ID = review.getItemID();
            if( ! tempList.contains( ID ) ) tempList.add( ID );
        }


        return tempList;
    }



    // checks if ID has a minimum of 1 review or more
    public Boolean hasReviews(String ID){
        for(Review review : reviews){
            if (review.getItemID().equals(ID)) return true;
        }
        return false;
    }


}


