/*
 ____   ___  _   _ _   _ _____   ____  _   _ _______   __
|  _ \ / _ \| \ | | | | |_   _| |  _ \| | | |_   _\ \ / /
| | | | | | |  \| | | | | | |   | | | | | | | | |  \ V / 
| |_| | |_| | |\  | |_| | | |   | |_| | |_| | | |   | |  
|____/ \___/|_| \_|\___/  |_|   |____/ \___/  |_|   |_|  
  
 */

import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room – represents a single location in the world of Donut Duty.
 *
 * A Room is a distinct area the player can explore. Each room can:
 *  - Have multiple exits
 *  - Contain items the player can collect
 *  - Contain a single NPC that may be friendly or hostile
 *  - Be locked and require a specific item to unlock
 *
 * Rooms form the connected world map that the player navigates 

 *
 * @author  Ssuhaan Jaiin 
 * @version 2025.11.19
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;      // Stores exits of this room.
    private ArrayList<Item> items;           // Creates an ArrayList of items.
    private NPC npc;                        // Associate NPC for the room
    private boolean locked;                // If the room is locked
    private Item requiredKey;             // The key to open the room
    
    
    
    /**
     * Creates a new room with the given description.
     * Rooms start unlocked with no exits, no items, and no NPCs.
     *
     * @param description A short description (String)
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        npc = null;
        boolean locked = false;   // all rooms unlocked by default
        Item requiredKey = null;  // the item required to unlock the room

        
    }

    //===============================================================================

    /**
     * Defines an exit from this room to another.
     *
     * @param direction Direction of the exit.
     * @param neighbor  The room that lies in that direction.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    //===============================================================================

    
    /** 
    * Assign an NPC to the room.
    */

    public void setNPC(NPC npc){
        this.npc = npc;
    }
    
    
    //===============================================================================

    
    /**
     * @return The NPC is currently in the room or null if empty
     */
    public NPC getNPC(){
        return npc;
    }
    
    
    //===============================================================================


    /**
     * Locks this room and assigns the key item to unlock it
     * @param key The item needed to unlock
     */
    public void lockRoom(Item key)
    {
        locked = true;
        requiredKey = key;
    }
    
    
    //===============================================================================

    
    
    /**
     * Unlocks the room, no key needed afterwards
     */
    public void unlockRoom()
    {
        locked = false;
    }
    
    
    /**
     * @return true if the room is locked
     */
    public boolean isLocked(){
        return locked;
    }
    
    
    //===============================================================================

    
    
    /**
     * @return They key item is required to open the room
     */
    public Item getRequiredKey()
    {
        return requiredKey;
    }
    
    
    //===============================================================================

    
    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    
    //===============================================================================

    
    /**
     * Return a description of the room in a string form:
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        
    //Description string
    String text_description = "You are " + description + ".\n" + getExitString() + "\n" + getItemString();
    if (npc != null)
    {
        text_description += "\nYou see [" + npc.getName() + "] here.";
    }
    return text_description;
    }

    
    //===============================================================================


    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    //===============================================================================

    
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    //===============================================================================

    
    
    /**
     * @return a string of all items in the room
     */
    public String getItemString() {
        if (items.isEmpty())
        {
            return "[ No items here 🍩 ]";
        }
        else
        {
            String itemString = "Items : ";
            for (Item item : items)
            {
                itemString =  itemString + "[" + item.getItemName() + "]" + " ";
            }
            return itemString;
        }
    }
    
    //===============================================================================

    
    
    /**
     * Adds an item to the room
     * @param item the item to add
     */
    public void addItem(Item item)
    {
        items.add(item);
    }
    
    //===============================================================================

    
    
    /**
     * Get an item by name without removing it
     * @param itemName Name of the item
     * @return The item if it exists, null otherwise
     */
    public Item getItem(String itemName) {
    for (Item item : items) {
        if (item.getItemName().equals(itemName)) {
            return item;
        }
    }
    return null;
    }

    
    //===============================================================================

    
    /**
     * Removes an item by the name and returns it
     * @param itemName Name of the item to remove
     * @return The item removed, or null (if not found)
     */
    public Item removeItem(String itemName) {
        for (Item item : items)
        {
            if (item.getItemName().equals(itemName))
            {
                items.remove(item); //removes the named item
                return item; //returns the named item
            }
            
        }
        return null; //returns null if nothing matches
        
    }
    
    
}
    
