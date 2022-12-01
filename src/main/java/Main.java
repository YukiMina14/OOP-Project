
import menus.MainMenu;

public class Main {
    public static void main(String[] args) {
        /** MainController mainController = new MainController(); **/    // makes a new MainController object, it controls everything
        //MainController.getMainController().run();

        MainMenu mainMenu  = new MainMenu();
        mainMenu.mainMenu();
    }
}