/*
 ____   ___  _   _ _   _ _____   ____  _   _ _______   __
|  _ \ / _ \| \ | | | | |_   _| |  _ \| | | |_   _\ \ / /
| | | | | | |  \| | | | | | |   | | | | | | | | |  \ V / 
| |_| | |_| | |\  | |_| | | |   | |_| | |_| | | |   | |  
|____/ \___/|_| \_|\___/  |_|   |____/ \___/  |_|   |_|  
  
 */

/**
 * This class is part of the "Donut Duty" application. 
 * "Donut Duty", is an interactive text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command can consist of upto three strings: a command word, a second word 
 * (for example, if the command was "give key sam", and a third word for the recipient
 * then the two strings obviously are "give", "key" and "sam").
 * 
 * 
 * The way this is used is: 
 * Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 * If the command had two words, then the third word is <null>.
 * 
 * @author  Ssuhaan Jaiin 
 * @version 2025.11.19
 */


public class Command
{
    //The three words
    private String commandWord;
    private String secondWord;
    private String thirdWord;

    
    /**
     * Create a command object. First, second and third word must be supplied, 
     * but either one (or two or all three) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognised.
     * @param secondWord The second word of the command. Null if the command
     *                  was not recognised.
     * @param thirdWord The third word of the command. Null if the command
     *                  was not recognised
     */
    public Command(String firstWord, String secondWord, String thirdWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
    }

    //===============================================================================

    /**
     * Returns the main command word.
     * @return the command word, or null if the command was not understood
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    
    //===============================================================================

    
    /**
     * Returns the second word of the command.
     * @return the second word, or null if none was entered
     */
    public String getSecondWord()
    {
        return secondWord;
    }
    
    
    //===============================================================================

    
    /**
     * Returns the optional third word of the command.
     * @return the third word, or null if none was entered
     */
    public String getThirdWord()
    {
        return thirdWord;
    }

    //===============================================================================

    
    /**
     * Checks whether the command word was recognised.
     * @return true if the command is unknown, false otherwise
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    //===============================================================================

    
    /**
     * Checks whether the command contains a second word.
     * @return true if a second word exists
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    
    //===============================================================================

    
    /**
     * Checks whether the command contains a third word.
     * @return true if a third word exists
     */
    public boolean hasThirdWord()
    {
        return (thirdWord != null);
    }
}

