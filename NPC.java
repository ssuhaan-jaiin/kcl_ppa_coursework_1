import java.util.ArrayList;
import java.util.Set;
import java.util.Random;
import java.util.List;
/*
 ____   ___  _   _ _   _ _____   ____  _   _ _______   __
|  _ \ / _ \| \ | | | | |_   _| |  _ \| | | |_   _\ \ / /
| | | | | | |  \| | | | | | |   | | | | | | | | |  \ V / 
| |_| | |_| | |\  | |_| | | |   | |_| | |_| | | |   | |  
|____/ \___/|_| \_|\___/  |_|   |____/ \___/  |_|   |_|  

*/

/**
 * The NPC class represents a non-player character in Donut Duty.
 * NPCs may be hostile, and can interact with
 * the player through dialogue or combat.
 *
 * Responsibilities of this class:
 * - Store NPC attributes
 * - Track whether the NPC is alive or dead.
 * - Allow NPCs to carry items in an inventory.
 * - Handle combat actions 
 * - Provide access to NPC information methods.

 * @author  Ssuhaan Jaiin
 * @version 2025.11.18
 */
public class NPC
{
    private String name;
    private String description;
    private int health;
    private boolean isHostile;
    private ArrayList<Item> inventory;
    private String dialogue;
    private boolean isAlive;
    private int combatPower;
    private String combatMessage;
    private boolean canMove;

    /**
     * 
     * Constructor for creating an NPC with dialogue, stats and behaviour settings.
     * 
     * @param name          The NPC's name.
     * @param description   Short description of the NPC.
     * @param health        Starting health of the NPC.
     * @param isHostile     Whether the NPC starts hostile.
     * @param dialogue      Default dialogue the NPC speaks.
     * @param combatPower   Damage the NPC deals to the player.
     * 
     */
    public NPC(String name, String description, int health, boolean isHostile, String dialogue,int combatPower)
    {
        this.name = name;
        this.description = description;
        this.health = health;
        this.isHostile = isHostile;
        this.inventory = new ArrayList<>();
        this.dialogue = dialogue;
        this.isAlive = true;
        this.combatPower = combatPower;
        this.combatMessage = combatMessage;
        canMove = false;
    }

//===================================================================================

    /**
     * @return The name of the NPC.
     */
    public String getName() {
        return name;
    }

//===================================================================================
    
   /**
     * @return A short description of the NPC.
     */
    public String getDescription() {
        return description;
    }

//===================================================================================

    /**
     * @return Current health of the NPC.
     */
    public int getHealth() {
        return health;
    }

//=================================================================================== 
    
    /**
     * Makes the NPC become hostile (used after certain triggers).
     */
    public void turnHostile(){
        isHostile = true;
    }
    
//===================================================================================    
    
    /**
     * @return True if the NPC has health above 0.
     */
    public boolean isAlive() {
        return health > 0;
    }

//===================================================================================    
    
    /**
     * @return True if the NPC is hostile toward the player.
     */
    public boolean isHostile() {
        return isHostile;
    }
    
//===================================================================================
    
    /**
     * @return The amount of damage the NPC deals to the player.
     */
    public int getCombatPower(){
        return combatPower;
    }

    
//===================================================================================
    

    /**
     * NPC attacks the player, dealing its combatPower as damage.
     * @param player The player being attacked.
     */
    public void attack(Player player) {
    if (!isAlive) {
        return;
    }

    System.out.println(name + " attacks you!");

    player.damagePlayer(combatPower);

    System.out.println("You take " + combatPower + " damage.");
    System.out.println("Your remaining health: " + player.getHealth());
}


//===================================================================================


    /**
     * Damages the NPC and handles its death condition.
     * @param damage Amount of damage dealt.
     */
    public void damageNPC(int damage) {
    health -= damage;
    if (health <= 0) {
        health = 0;
        isAlive = false;   // IMPORTANT
    }
}
    
    
//===================================================================================

    /**
     * @return A list of items carried by the NPC.
     */
    public ArrayList<Item> getInventory()
    {
        return inventory;
    }
    

//===================================================================================
    

    /**
     * @return The dialogue spoken by the NPC when talked to.
     */
    
    public String getDialogue() {
        return dialogue;
    }

//==================================================================================
    
    /**
     * Enables movement of an NPC
     */
    public void enableMovement()
    {
        canMove = true;
    }
    
    
//==================================================================================

    /**
     * @return True if an NPC can move
     */
    public boolean canMove()
    {
        return canMove;
    }
    
    
}
