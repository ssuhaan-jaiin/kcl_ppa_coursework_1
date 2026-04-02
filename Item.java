
/*
 ____   ___  _   _ _   _ _____   ____  _   _ _______   __
|  _ \ / _ \| \ | | | | |_   _| |  _ \| | | |_   _\ \ / /
| | | | | | |  \| | | | | | |   | | | | | | | | |  \ V / 
| |_| | |_| | |\  | |_| | | |   | |_| | |_| | | |   | |  
|____/ \___/|_| \_|\___/  |_|   |____/ \___/  |_|   |_|  
  
 */
/**
 * The Item class represents any object the player can encounter in Donut Duty.
 * 
 * Items may have different purposes, including:
 * - Basic items, that can be picked up and carried.
 * - Weapons, which provide combat damage.
 * - Consumables, which restore health when used.
 *
 * Responsibilities of this class:
 * - Store core item details 
 * - Store optional combat stats 
 * - Store optional consumable stats
 * - Provide access to properties through getter methods.
 *
 * @author  Ssuhaan Jaiin
 * @version 2025.11.18
 */

public class Item
{
    //Initializing the Standard Variables of the Item Class
    private String itemName;
    private String itemDescription;
    private int itemWeight;
    
    //Initializing the Combat Variables of the Item Class
    private boolean itemIsWeapon;
    private int itemCombatPoints;
    
    //Initializing the Consumable Variables of the Item Class
    private boolean itemIsConsumable;
    private int itemHealthPoints;

    
    /**
     * Constructor for a basic non-weapon, non-consumable item.
     *
     * @param itemName Name of the item.
     * @param itemDescription A short description of the item.
     * @param itemWeight Weight of the item.
     */
    
    public Item(String itemName, String itemDescription, int itemWeight) 
    {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;

    }
    

    
    /**
     * Constructor for items that may be weapons, consumables, or both.
     *
     * @param itemName Name of the item.
     * @param itemDescription A short description of the item.
     * @param itemWeight Weight of the item.
     * @param itemIsWeapon Whether this item is a weapon.
     * @param itemCombatPoints Damage it deals if used in combat.
     * @param itemIsConsumable Whether this item can be consumed.
     * @param itemHealthPoints Health restored by consuming this item.
     */
    public Item(String itemName, String itemDescription, int itemWeight, boolean itemIsWeapon, int itemCombatPoints,boolean itemIsConsumable, int itemHealthPoints )
    {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
        this.itemIsWeapon = itemIsWeapon;
        this.itemCombatPoints = itemCombatPoints;
        this.itemIsConsumable = itemIsConsumable;
        this.itemHealthPoints = itemHealthPoints;
    }
    
    
    //===============================================================================
    

    
    /**
     * @return The Name of the Item
     */
    public String getItemName()
    {
        return itemName;
    }
    
    
    //===============================================================================
    
    
     /**
     * @return The description of the item
     */
    public String getItemDescription()
    {
        return itemDescription;
    }
    

    //===============================================================================

    
    
     /**
     * @return The weight of the Item
     */
    public int getItemWeight()
    {
        return itemWeight;
    }
    
    
    //===============================================================================
    
    
    
    /**
     * @return true if the item is a weapon
     */
    public boolean isWeapon()
    {
        return itemIsWeapon;
    }
    
    
    //===============================================================================
    

    
     /**
     * @return true if the item is a consumable
     */
    public boolean isConsumable()
    {
        return itemIsConsumable;
    }
    
    
    
    //===============================================================================
    
    
     /**
     * @return combat points if item is a weapon
     */
    public int getItemCombatPoints()
    {
        return itemCombatPoints;
    }
    
    
    //===============================================================================
    
    
     /**
     * @return health points of the item
     */
    public int getHealthPoints()
    {
        return itemHealthPoints;
    }
    
    
    
    
}