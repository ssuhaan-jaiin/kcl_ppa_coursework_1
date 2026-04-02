import java.util.Scanner;
/*
 ____   ___  _   _ _   _ _____   ____  _   _ _______   __
|  _ \ / _ \| \ | | | | |_   _| |  _ \| | | |_   _\ \ / /
| | | | | | |  \| | | | | | |   | | | | | | | | |  \ V / 
| |_| | |_| | |\  | |_| | | |   | |_| | |_| | | |   | |  
|____/ \___/|_| \_|\___/  |_|   |____/ \___/  |_|   |_|  
  
 */
/**
 * 
 * The Parser class is responsible for reading and interpreting user input
 * in the "Donut Duty" text-based adventure game.
 *
 * This class reads a full line of text from the terminal, breaks it
 * into up to three words, and attempts to convert those words into a
 * Command object
 * 
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Ssuhaan Jaiin
 * @version 2025.11.19
 */

//===============================================================================

    public class Parser 
    {
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Creates a new Parser that reads commands from the terminal window.
     */
    public Parser() 
    {
        //Initiates a scanner and commandwords object
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    //===============================================================================
    
    /**
     * Reads the next line of input from the player and converts it into a Command.
     *
     * The parser accepts up to three words:
     * word1 - the command word (move, take, attack)
     * word2 - an optional second word (direction or item)
     * word3 - an optional third word used in some cases
     * 
     * Any additional words typed by the player are ignored.
     * 
     * @return a Command object representing the player's input
     */
    
    
    public Command getCommand() 
    {
        //Obtain the input words and store them in inputLine
        String inputLine;   
        String word1 = null;
        String word2 = null;
        String word3 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();  // get second word
                if(tokenizer.hasNext()) {
                    word3 = tokenizer.next(); //get third word
                }
                // note: we just ignore the rest of the input line.
            }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(word1)) {
            return new Command(word1, word2,word3);
        }
        else {
            return new Command(null, word2, word3); 
        }
    }

    
    //===============================================================================
    
    /**
     * Print out a list of valid command words which the player can use.
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
