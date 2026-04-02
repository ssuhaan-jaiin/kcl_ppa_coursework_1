/*
 ____   ___  _   _ _   _ _____   ____  _   _ _______   __
|  _ \ / _ \| \ | | | | |_   _| |  _ \| | | |_   _\ \ / /
| | | | | | |  \| | | | | | |   | | | | | | | | |  \ V / 
| |_| | |_| | |\  | |_| | | |   | |_| | |_| | | |   | |  
|____/ \___/|_| \_|\___/  |_|   |____/ \___/  |_|   |_|  
  
*/
/**
 * The CommandWords class stores all valid command words that the
 * "Donut Duty" game recognises.
 *
 * It provides methods to check whether a given word is a valid
 * command and to print out the full list of available commands.
 *
 * These commands are used by the game to determine which
 * actions the player is allowed to perform.
 * 
 * @author Ssuhaan Jaiin
 * @version 2025.11.18
 * 
*/

//===============================================================================


public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
    "go","quit","help","look","take","drop","inventory", "back",
    "eat","talk","give","status","attack"
};

    /**
     * Constructor of the CommandWords Object
     */
    public CommandWords()
    {
        //No initialises needed
    }

//===============================================================================

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

//===============================================================================

    /**
     * Print all valid commands to the terminal window
     * Used when the player requests help or when the game displays this
     */
    public void showAll() 
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
