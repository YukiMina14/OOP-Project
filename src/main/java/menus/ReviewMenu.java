package menus;

import controllers.ReviewController;
import utilities.ListSaver;
import utilities.UserInput;

public class ReviewMenu {

    private static final String EOL = System.lineSeparator();
    private final UserInput userInput;
    private ReviewController reviewController;

    public  ReviewMenu(ListSaver listSaver){
        userInput        = new UserInput();
        reviewController = new ReviewController(listSaver);
    }

    public void reviewMenu() {
        String input;

        do {
            System.out.print(
                    EOL +
                            "Review Options Menu: " +
                            EOL +
                            "0. Return to Main Menu" + EOL +
                            "1. Create review for an Item." + EOL +
                            "2. Print a specific review of an Item." + EOL +
                            "3. Print all reviews of an Item." + EOL +
                            "4. Print mean grade of an Item." + EOL +
                            "5. Print all comments of an Item." + EOL +
                            "6. Print all registered reviews." + EOL +
                            "7. Print item(s) with most reviews." + EOL +
                            "8. Print item(s) with least reviews." + EOL +
                            "9. Print item(s) with best mean review grade." + EOL +
                            "10. Print item(s) with worst mean review grade." + EOL +
                            EOL +
                            "Type an option number: "
            );

            input = userInput.readString();
            switch ( input ) {
                case "0":                                                   continue;
                case "1":  createReview();                                  break;
                case "2":  printSpecificReview();                           break;
                case "3":  printAllReviews();                               break;
                case "4":  printMeanGrade();                                break;
                case "5":  printAllCommentsOfItem();                        break;
                case "6":  printAllRegisteredReviews();                     break;
                case "7":  printMostReviewed();                             break;
                case "8":  printLeastReviewed();                            break;
                case "9":  printBestItem();                                 break;
                case "10": printWorstItem();                                break;

                default:
                    System.out.println("Invalid option. Please type in a valid option from 0-10.");
                    break;
            }
        }while (! input.equals("0"));
    }



    /*****************METHODS*******************/
    ///Creates a review///-
    private void createReview(){
        System.out.print(   "Enter the ID of the item you ant to review: ");
        String  itemID = userInput.readString();            //userinput gets item id from user

        System.out.print(   "From 1 to 5 how would you like to rate the item: ");
        int     Rating = userInput.readInt();               //userinput gets the rating from the user

        System.out.println( "Add a comment?  (Optional)");
        String  Comment = userInput.readStringCanEmpty();           //gets the comment from the user

        System.out.println( reviewController.createReview(itemID, Rating, Comment) );
        return;
    }

    ///printes a Specific review of an item ///-
    private void printSpecificReview(){
        System.out.print(   "Enter item's ID: ");
        String  itemID = userInput.readString();     //userinput gets item id from user

        if(!reviewController.hasReviews(itemID)) {
            System.out.println("This item Doesnt have any reviews");
            return;
        }       // checks to see if item has reviews

        System.out.print(   "Enter what review you want from 1 to " + reviewController.getReviewAmount(itemID) + " : ");
        int     index = userInput.readInt();        //gets what review the user wants

        System.out.println( reviewController.printSpecificReview(itemID, index));
    }

    ///prints all reviews for all items///-
    private void printAllReviews(){
        System.out.println( reviewController.printAllReviews() );
    }

    ///prints mean grade of Item///-
    private void printMeanGrade(){
        System.out.print(   "Enter an item ID: ");
        String  itemID      = userInput.readString();                   //userinput gets item id from user
        double  meanGrade   = reviewController.getMeanGrade(itemID);    //gets the mean grade from reviewcontroler

        if(meanGrade <= 0.0) {              // getMeanGrade returns -1.0 if item id doesnt exist
            System.out.println( "Item does not exist or does not have reviews.");
        }else {
            System.out.println( "Mean Grade of item " + itemID + " is equal to " + meanGrade);
        }
        return;
    }

    ///prints all comments for a certain item ///-
    private void printAllCommentsOfItem(){
        System.out.print( "Enter an item ID: ");
        String  itemID    = userInput.readString();         //userinput gets item id from user

        if(reviewController.hasReviews(itemID)){            // checks if item had reviews
            System.out.println( "all comments for item " + itemID + ": ");

            for(String comment : reviewController.getAllComments(itemID)){
                System.out.println(comment);
            }
        }else{
            System.out.println("No reviews for item yet.");
        }
        return;
    }

    ///print all registered reviews for all items ///-
    private void printAllRegisteredReviews(){
        System.out.println(reviewController.printAllReviews());
    }

    ///Print item with most reviews ///-
    private void printMostReviewed(){
        System.out.println( reviewController.printMostReviewedItem() );
    }

    ///print item with the least reviews ///-
    private void printLeastReviewed(){
        System.out.println( reviewController.printLeastReviewedItem() );
    }

    ///print the item with the best mean grade///-
    private void printBestItem(){
        System.out.println( reviewController.printBestMeanGrade() );
    }

    ///print the item with the worst mean grade///-
    private void printWorstItem(){
        System.out.println( reviewController.printWorstMeanGrade() );
    }

}






