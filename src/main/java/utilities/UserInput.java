package utilities;

import java.util.Scanner;

public class UserInput {
    public static final String EOL = System.lineSeparator();
    private static final Scanner scanner = new Scanner(System.in);






    public int readInt() {
        try{
            int input = scanner.nextInt(); //The program is going to try to do this
            scanner.nextLine();
            return input;
        } catch (Exception e){ //If it fails to do the above method, it executes the catch. We do this to avoid error message if the program crashes.
            System.out.println("Please only enter a literal number");
            scanner.nextLine();
            return readInt();
        }
    }


    public double readDouble() {
        try {            //Doing a try catch method again because the program will crash if the user inputs something that isn't recognized as a double, e.g a letter A.
            double input = scanner.nextDouble();
            scanner.nextLine();
            return input;
        } catch (Exception e){
            System.out.println("Please only enter a literal number ");
            scanner.nextLine();
            return readDouble(); // What should it return?
        }
    }


    public String readString() { //Doesn't need to have a try-catch method. Because there's nothing to be inputted that can't be recognized as a "non string input".
       String input = scanner.nextLine();
       if( !input.isBlank() ){
           return input;
       }
       System.out.println("Please make sure this field isn't empty");
       return readString();
    }

    public String readStringCanEmpty(){
        return scanner.nextLine();
    }


}



