import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cramsden on 8/5/15.
 */
public class Console {
    private Library library;
    private PrintStream printStream;
    private BufferedReader reader;
    private Map<String, Command> menuItems;
    private boolean isSelectionValid = false;
    private Menu menu;

    public Console(Library library, PrintStream printStream, BufferedReader reader, Map<String, Command> menuItems) {
        this.library = library;
        this.printStream = printStream;
        this.reader = reader;
        this.menuItems = menuItems;

        menu = new Menu(printStream, menuItems);
    }

    private void openLibrary() {
        printStream.println(library.open());
    }


    public void runLibrary() {
        openLibrary();
        //generateMenu();
        menu.print();

        String userInput;

//        while (!isSelectionValid) {
            userInput =  getUserInput();
            executeUserInput(userInput);
//        }
    }




    public String getUserInput() {

        printStream.println("Please Select a Number from the Menu:");
        String userInput = "";

        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            printStream.println("Could not read user's input.");
        }

        return userInput;


    }

    private void executeUserInput(String userInput) {


        Command command = menuItems.get(userInput);
        if (command == null) {
            printStream.println("That is an invalid selection!");
            isSelectionValid = false;
        } else {
            command.execute();
            isSelectionValid = true;
        }


    }
}
