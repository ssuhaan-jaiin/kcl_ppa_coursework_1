import java.util.ArrayList;
/*
 ____   ___  _   _ _   _ _____   ____  _   _ _______   __
|  _ \ / _ \| \ | | | | |_   _| |  _ \| | | |_   _\ \ / /
| | | | | | |  \| | | | | | |   | | | | | | | | |  \ V / 
| |_| | |_| | |\  | |_| | | |   | |_| | |_| | | |   | |  
|____/ \___/|_| \_|\___/  |_|   |____/ \___/  |_|   |_|  
  
 */
/**
 * Class Player represents the user-controlled character in the 
 * Donut Duty adventure game.
 *
 * The Player stores:
 * - The room they are currently in.
 * - An inventory with a weight limit.
 * - Current and maximum health.
 * - A history of previously visited rooms (for the "back" command).
 * - Their alive/dead state.
 *
 * This class is responsible for inventory management, travel tracking,
 * health handling, and determining when the player dies.

 * author  Ssuhaan Jaiin
 * version 2025.11.19
 */

public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private ArrayList<Item> inventory; //items list
    private int maxInventoryWeight; //inventory weight
    private int health;             //current player health
    private int maxHealth;          //max player health
    private ArrayList<Room> previousRooms; //rooms the player has been in
    private boolean isAlive; //if player is alive
    
    
    
    
    /**
     * Creates a new Player, assigning them to a starting room and 
     * initializing inventory, health, and tracking systems.
     *
     * @param startingRoom the room where the player begins the game
     */
    public Player(Room startingRoom)
    {
        this.currentRoom = startingRoom;
        this.inventory = new ArrayList<>();
        this.maxInventoryWeight = 30;
        this.maxHealth = 100;
        this.health = 100;
        this.previousRooms = new ArrayList<>();
        this.isAlive = true;

    }
    
    
    //===============================================================================

    
    /** 
     * @return true if the player is alive 
     */
    public boolean isAlive()
    {
        return isAlive;
    }
    
    
    //===============================================================================
    
    
     /**
     * Checks if the player owns a specific item.
     *
     * @param itemName the name of the item
     * @return true if the item is in the inventory
     */
    public boolean hasItem(String itemName){
        for (Item item : inventory) {
            if ( item.getItemName().equals(itemName)){
                return true;
            }
        }
        return false;
    }
    
    
    //===============================================================================


    
    /**
     * @return the current room the player is in
     */
    public Room getCurrentRoom()
    {
        // returns the current room of the player
        return currentRoom;
    }
    
    
    //===============================================================================

    
    /**
     * Moves the player to a new room.
     * The previous room is stored so the player can return.
     *
     * @param room the room to move the player into
     */
    public void setCurrentRoom(Room room)
    {
        if (currentRoom !=null)
        {
            previousRooms.add(currentRoom);
        }
        // sets the current room of the player
        this.currentRoom = room;
        
        
    }
    
    
    //===============================================================================

    
    
    /**
     * Moves the player back to the most recently visited room.
     *
     * @return true if a previous room exists, false otherwise
     */
    public boolean goBack()
    {
        if (!previousRooms.isEmpty())
        {
        currentRoom = previousRooms.remove(previousRooms.size()-1);
        return true;
        }
        
        else
        {
            return false;
        }
        
    }
    
    
    //===============================================================================

    
    
    /**
     * Calculates the total weight of all items in the inventory.
     *
     * @return total weight of carried items
     */
    public int getInventoryWeight(){
        int totalWeight = 0;
        for (Item item : inventory)
        {
            totalWeight+= item.getItemWeight();
        }
        return totalWeight;
    }
    
    
    //===============================================================================

    
    /**
     * Attempts to add an item to the player’s inventory.
     * Checks if doing so would exceed weight capacity.
     *
     * @param item the item to add
     * @return true if added, false if inventory is full
     */
    public boolean addItem(Item item) {
    if (item == null) 
    {
        System.out.println("Can't take a null value");
        return false;
    }
    if (getInventoryWeight() + item.getItemWeight() > maxInventoryWeight) 
    {
        System.out.println("You cannot carry " + item.getItemName() + ". It exceeds the inventory space!");
        return false;  // failed to add
    } 
    else 
    {
        inventory.add(item);  // adds item to inventory
        System.out.println("You picked up: " + item.getItemName());
        System.out.println(item.getItemDescription());
        return true;  // successfully added
    }
    }
    
    
    //===============================================================================

    
    /**
     * Removes an item from the player’s inventory.
     *
     * @param itemName the name of the item to remove
     * @return the removed item, or null if not found
     */
    public Item removeItem(String itemName) {
    for (Item item : inventory) {
        if (item.getItemName().equalsIgnoreCase(itemName)) {
            inventory.remove(item);
            return item; // return immediately
        }
    }
    return null;
    }
    
    
    //===============================================================================

    
    
    /**
     * Displays all items the player is carrying.
     */
    public void showInventory(){
        if (inventory.isEmpty())
        {
            System.out.println("Your inventory is empty");
        
        }
        else
        {
            System.out.println("==== Inventory ====");
            int itemCount = 1;
            for (Item item : inventory)
            {
                System.out.println(itemCount + "." + item.getItemName().toUpperCase() + " : " + item.getItemDescription() + " : ( Weight -> " + item.getItemWeight() + ")");
                itemCount++;
            }
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Total Weight : " + getInventoryWeight() + " / " + maxInventoryWeight);
        }
        
    }
    
    
    //===============================================================================

    
    
    
    /** 
     * @return the player’s current health 
     */
    public int getHealth()
    {
        return health;
    }
    
    //===============================================================================

    
    
    /** 
     * @return the maximum possible health 
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }
    
    
    //===============================================================================
   
    
    
    /**
     * Heals the player by a certain amount.
     *
     * @param healAmount the amount to restore
     */
    public void healPlayer(int healAmount)
    {
        health += healAmount; //increases health of player
        if (health > maxHealth)
        {
            health = maxHealth; //ensures that the player health doesn't exceed the max health
        }
    }
    
    //===============================================================================
    
    
    /**
     * Damages the player by a certain amount.
     *
     * @param damageAmount the amount to subtract from health
     */
    public void damagePlayer(int damageAmount)
    {
        health-=damageAmount;
        if(health<=0)
        {
            health = 0;
            playerDeath();
        }
        
    }
    
    //===============================================================================
        
    
    /**
     * Executes the death process when health reaches 0.
     */
    public void playerDeath()
    {
        System.out.println("GAME OVER! : You lost all your health");
        isAlive = false; // mark player as dead
    }
    
    //===============================================================================
    
    
    /**
     * Retrieves an item from the inventory without removing it.
     *
     * @param itemName the name of the item to search for
     * @return the item, or null if not found
     */
    
    public Item getItemFromInventory(String itemName) 
    {
    for (Item item : inventory) {
        if (item.getItemName().equalsIgnoreCase(itemName)) {
            return item;
        }
    }
    return null; 
    }

        

    
}