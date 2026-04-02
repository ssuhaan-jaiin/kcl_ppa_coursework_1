import java.util.ArrayList;
import java.util.Random;

/*
 ____   ___  _   _ _   _ _____   ____  _   _ _______   __
|  _ \ / _ \| \ | | | | |_   _| |  _ \| | | |_   _\ \ / /
| | | | | | |  \| | | | | | |   | | | | | | | | |  \ V / 
| |_| | |_| | |\  | |_| | | |   | |_| | |_| | | |   | |  
|____/ \___/|_| \_|\___/  |_|   |____/ \___/  |_|   |_|  
  
 */
//== quit n npc
/**
 * This is the main controller class for a text-based adventure game called
 * "Donut Duty"
 * 
 * This class is responsible for initialising all rooms, items, and NPCs,
 * linking rooms together, handiling the user's input and processing commands 
 * with the help of the game loop
 * 
 * The game ends when the player either 
 * -> defeats the final boss and delivers all golden donuts to oldManKrusty
 * -> dies via starvation or in combat
 * -> chooses to quit
 * 
 * Overview : 
 * - Explore POIs including bakeries, alleys and dungeons
 * - Collect items. weapons and quest objects
 * - Deliver golden donuts (quest items) to oldManKrusty to win the game
 * 
 * @author Ssuhaan Jaiin
 * @version 2025.11.19
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private boolean finished;

//=================== ROOMS =======================================
    Room fresh_n_sweet_bakery, police_station,icing_avenue, sugar_lab, 
    evidence_room, crumbway_alley, dough_dungeon, glazed_lair, abandoned_bakery,
    teleport_room, startRoom;
    
//================== PATROL ROUTE ====================================
    private ArrayList<Room> cookiePatrolRoute; 

    
    /**
     * Creates a new Game instance by initialising all rooms, items and NPCs
     * and setting up the partser and the player
     */
    public Game() 
    {
        
        createRoomsAndItems(); //Method to initialise all rooms, items & NPCs
        parser = new Parser(); 
        player = new Player(startRoom); //Creates a new player and sets startRoom

}
    
    
//===============================================================================

    
    /**
     * Creates all rooms, assigns exits, generates items, places NPCs and sets location
     * -> (This method has no return value and is run once during the game's setup)
     */
    private void createRoomsAndItems()
    {
        
        // Create room instances with descriptions
        fresh_n_sweet_bakery = new Room("outside the Fresh & Sweet Bakery");
        police_station = new Room("inside the Police Station");
        icing_avenue = new Room("on Icing Avenue");
        sugar_lab = new Room("inside Dr. Sprinklefold's Sugar Lab");
        evidence_room = new Room("inside the Police Station's Evidence Room");
        crumbway_alley = new Room("in Crumbway Alley");
        dough_dungeon = new Room("in the Dough Dungeon");
        glazed_lair = new Room("in the Glazed Lair (final boss room)");
        abandoned_bakery = new Room("in the abandoned bakery of old man krusty");
        teleport_room = new Room("inside a strange portal chamber");
        
        
         //================ ITEMS ==========================================
        Item donut, croissant, sugarDonut, chocolateDonut; // basic consumables    
        Item reactorCube;                                 // quest item
        Item specialKey;                                 // key for glazed lair
        Item goldenDonut;                               // win-condition items
        Item baseballBat, gun, pocketKnife, bazooka;   // weapons
        Item string, rock, handcuffs, cobweb;         // useless items
        
        // Consumables (basic food)
        donut = new Item("donut", "A delicious donut. Restores some health.", 2, false, 0, true, 20);
        croissant = new Item("croissant", "A buttery croissant. Restores a little health.", 2, false, 0, true, 15);
        chocolateDonut = new Item("chocolateDonut", "A chocolate donut. Restores some health.", 3, false, 0, true, 20);
        sugarDonut = new Item("sugarDonut", "A sugar donut. Restores some health.", 3, false, 0, true, 20);
    
        // Quest / Special items
        reactorCube = new Item("reactorCube", "A mysterious cube for Dr. Sprinkleford.", 3);
        specialKey = new Item("specialKey", "Key needed to access the Glazed Lair.", 1);
    
        // Golden donuts (part of win condition)
        goldenDonut = new Item("goldenDonut", "A shiny golden donut.", 5);
    
        // Weapons
        baseballBat = new Item("baseballBat", "A sturdy baseball bat. Good for melee attacks.", 6, true, 20, false, 0);
        gun = new Item("gun", "A small handgun. Deals moderate damage.", 4, true, 50, false, 0);
        pocketKnife = new Item("pocketKnife", "A small pocket knife. Deals light damage.", 1, true, 10, false, 0);
        bazooka = new Item("bazooka", "A heavy bazooka dropped by Sgt Cookie.", 15, true, 100, false, 0);
    
        // Useless items
        string = new Item("string", "a piece of string.", 1);
        rock = new Item("rock", "A small, not very useful rock.", 2);
        handcuffs = new Item("handcuffs", "Old handcuffs. Maybe useful in a puzzle?", 3);
        cobweb = new Item("cobweb", "Just a dusty cobweb.", 1);
        
        
        
        
        
        //=================== NPCS ========================================
        NPC sgtCookie, drSprinkleford, ricoSugarhands ,oldManKrusty;
        
        //Sgt Cookie
        sgtCookie = new NPC("sgtCookie", "A tough police officer patrolling the streets.", 50, false, "Stay out of my way detective!", 20); 
        sgtCookie.getInventory().add(bazooka);
        
        //Dr Sprinkleford
        drSprinkleford = new NPC("drSprinkleford", "A sweet scientist who will give you the special key if you give him the reactor cube.", 150, false, "Bring me the reactor cube and I will give you the special key.",100);
        drSprinkleford.getInventory().add(specialKey);
        
        //Rico Sugarhands
        ricoSugarhands = new NPC("ricoSugarhands", "The dastardly donut thief! Defeat him to save Old Man Krusty's bakery.", 200, true, "You'll never catch me, detective!",25);
        ricoSugarhands.getInventory().add(goldenDonut);
        
        //Old Man Krusty
        oldManKrusty = new NPC("oldManKrusty", "The bakery owner. He needs your help to save his bakery.", 100, false, "Please save my bakery, detective!",0);
        
        
        //=================================================================
        //INITIALISING THE ROOM EXITS AND ADDING ITEMS, NPCS
        
        // Fresh & Sweet Bakery 
        fresh_n_sweet_bakery.setExit("south", icing_avenue);
        fresh_n_sweet_bakery.addItem(sugarDonut);
        fresh_n_sweet_bakery.addItem(croissant);
        fresh_n_sweet_bakery.addItem(chocolateDonut);
        
        // Police Station 
        police_station.setExit("east", icing_avenue);
        police_station.setExit("south", evidence_room);
        police_station.addItem(handcuffs);
        police_station.addItem(gun);
        police_station.setNPC(sgtCookie);

        
        
        // Icing Avenue (middle row center)
        icing_avenue.setExit("west", police_station);
        icing_avenue.setExit("east", sugar_lab);
        icing_avenue.setExit("north", fresh_n_sweet_bakery);
        icing_avenue.setExit("south", crumbway_alley);
        icing_avenue.addItem(donut);
        
        // Sugar Lab 
        sugar_lab.setExit("west", icing_avenue);
        sugar_lab.setExit("south", dough_dungeon);
        sugar_lab.addItem(chocolateDonut);
        sugar_lab.setNPC(drSprinkleford);
        sugar_lab.setExit("north", teleport_room);
        
        // Evidence Room 
        evidence_room.setExit("north", police_station);
        evidence_room.addItem(pocketKnife);
        evidence_room.addItem(goldenDonut);
        evidence_room.addItem(reactorCube);
        evidence_room.addItem(donut);
        evidence_room.addItem(sugarDonut);


        // Crumbway Alley 
        crumbway_alley.setExit("north", icing_avenue);
        crumbway_alley.setExit("south", glazed_lair);
        crumbway_alley.addItem(goldenDonut);
        crumbway_alley.addItem(baseballBat);
        crumbway_alley.addItem(sugarDonut);
        crumbway_alley.addItem(string);
        
        
        // Dough Dungeon 
        dough_dungeon.setExit("north", sugar_lab);
        dough_dungeon.setExit("south", abandoned_bakery);
        dough_dungeon.addItem(rock);
        dough_dungeon.addItem(cobweb);
        dough_dungeon.addItem(croissant);


        
        // Glazed Lair 
        glazed_lair.setExit("north", crumbway_alley);
        glazed_lair.setNPC(ricoSugarhands);
        glazed_lair.lockRoom(specialKey);
        glazed_lair.addItem(donut);
        glazed_lair.addItem(chocolateDonut);



        
        
        // Abandoned Bakery 
        abandoned_bakery.setExit("north", dough_dungeon);
        abandoned_bakery.addItem(string);
        abandoned_bakery.addItem(cobweb);
        abandoned_bakery.addItem(donut);
        abandoned_bakery.setNPC(oldManKrusty);

        // Teleport Room
        teleport_room.setExit("south",sugar_lab);

        //Set the start of the game to "Icing Avenue"
        startRoom = icing_avenue;  
        
        
        // ======= Challenge Task - Make Sgt Cookie patrol ===========
        cookiePatrolRoute = new ArrayList<>();
        cookiePatrolRoute.add(police_station);
        cookiePatrolRoute.add(icing_avenue);
        cookiePatrolRoute.add(evidence_room);
        sgtCookie.enableMovement();

        
}
    
    
//===============================================================================

    
    
    /**
     * Begins the main game loop
     * Commands are repeatedly read and processed until the player either
     * - quits
     * - dies
     * - finishes
     */
    public void play() 
    {            
        //Method to print the game's welcome message (called only once)
        printWelcome();
        
        //Main command loop : repeated execution until "finished" == true
        while (!finished && player.isAlive()) {
            Command command = parser.getCommand();
            finished = processCommand(command);

        }
        
        System.out.println("Your Donut Duty is complete... 🍩🏆 Farewell!!");

}
    
//===============================================================================

    
    /**
     * Prints the opening introduction text which includes basic instructions and
     * the description of the player's room
     * 
     * -> (This method has no return value and is run once during the game's start)
     */
    private void printWelcome()
    {
    System.out.println();
    System.out.println("=== 🍩 Welcome to Donut Duty! 🍩 ===");
    System.out.println();

    System.out.println("Explore the bakery, streets, and hidden locations to recover the stolen Golden Donuts and stop Rico Sugarhands before he ruins Old Man Crusty’s bakery for good.");
    System.out.println("Find the three missing Golden Donuts and get them to Old Man Krusty to win the game!!");

    System.out.println();

    System.out.println("=== How to play: ===");
    System.out.println("- go <direction>    = move north, south, east, or west");
    System.out.println("- back              = go to previously visited room");
    System.out.println("- look                = inspect your surroundings");
    System.out.println("- status              = return the health and stats of the player");

    System.out.println("- take <item>         = pick up an item");
    System.out.println("- drop <item>         = drop an item from your inventory");
    System.out.println("- eat <item>          = eat an item from your inventory");
    System.out.println("- inventory           = check what you're carrying");
    System.out.println("- talk <npc>          = speak to characters");
    System.out.println("- attack <npc> <weapon> = engage an enemy");
    System.out.println("- give <item> <npc>   = give an item to an npc");

    System.out.println("- help                = show the help menu");
    System.out.println("- quit                = exit the game");
    System.out.println();

    System.out.println("Good luck, Detective Crumb!");
    System.out.println();
    
    System.out.println(player.getCurrentRoom().getLongDescription());
}

    
    //===============================================================================

    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");

            return false;

        }
        else {

            return true;  // signal that we want to quit
        }
}
    
    //===============================================================================

    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        
        boolean wantToQuit = false;
        if(command.isUnknown()) {
            System.out.println("Command not found...");
            return false;
        }
        
        // When command word is help
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        
        // When command word is go
        else if (commandWord.equals("go")) {
            goRoom(command);
            moveNPCs();
        }
        
        // When command word is quit
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
            
        }
        
        // When command word is take
        else if (commandWord.equals("take"))
        {
            takeItem(command);
        }
        
        // When command word is inventory
        else if (commandWord.equals("inventory"))
        {
            player.showInventory();
        }
        
        // When command word is drop
        else if (commandWord.equals("drop"))
        {
            dropItem(command);
        }
        
        // When command word is status
        else if (commandWord.equals("status"))
        {
            printStatus();
        }
        
        // When command word is back
        else if (commandWord.equals("back"))
        {
            goBack();
        }
        
        // When command word is look
        else if (commandWord.equals("look"))
        {
            System.out.println(player.getCurrentRoom().getLongDescription());

        }
        
        // When command word is eat
        else if (commandWord.equals("eat"))
        {
            eatItem(command);
        }
        
        // When command word is talk
        else if (commandWord.equals("talk"))
        {
            talkToNPC(command);
        }
        
        // When command word is give
        else if (commandWord.equals("give"))
        {
            giveItemToNPC(command);
        }
        
        // When command word is attack
        else if (commandWord.equals("attack"))
        {
            attackNPC(command);
        }
        
        // else command not recognised.
        return wantToQuit;
}
    
//===============================================================================

    
    /**
     * Prints the help screen containing a short explanation and the full list of 
     * recognised command words
     */
    private void printHelp() 
    {
    System.out.println();
    System.out.println("===  Need Help, Detective Crumb?  ===");
    System.out.println();

    System.out.println("You are Detective Crumb, investigating the chaos in Donut City.");
    System.out.println("Explore bakeries, labs, alleys, and corners to gather items,");
    System.out.println("outsmart enemies, and eventually take down Rico Sugarhands.");
    System.out.println();

    System.out.println("=== Available Commands: ===");
    parser.showCommands();
    System.out.println();

    System.out.println("Use these commands to navigate, interact, and survive in Donut City.");
    System.out.println("If you're ever unsure, just type 'help' again!");
    System.out.println();
}
    
//===============================================================================

    
    /** 
     * Try to go in to one of the directions. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
{
    if (!command.hasSecondWord()) {
        System.out.println("Go where?");
        return;
    }

    String direction = command.getSecondWord();
    Room nextRoom = player.getCurrentRoom().getExit(direction);

    // No exit
    if (nextRoom == null) {
        System.out.println("There is no door!");
        return;
    }

    // ===== LOCKED ROOM CHECK =====
    if (nextRoom.isLocked()) {
        Item key = nextRoom.getRequiredKey();
        
        if (player.hasItem(key.getItemName())) {
            System.out.println("You used the " + key.getItemName() + " to unlock the room!");
            nextRoom.unlockRoom();
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        } else {
            System.out.println("The door is locked. You need " + key.getItemName() + " to enter.");
        }

        return; 
    }

    // ===== TELEPORT ROOM CHECK =====
    else if (nextRoom == teleport_room) {

        // Refered to arra
        ArrayList<Room> allRooms = new ArrayList<>();
        allRooms.add(fresh_n_sweet_bakery);
        allRooms.add(police_station);
        allRooms.add(icing_avenue);
        allRooms.add(sugar_lab);
        allRooms.add(evidence_room);
        allRooms.add(crumbway_alley);
        allRooms.add(dough_dungeon);
        allRooms.add(glazed_lair);
        allRooms.add(abandoned_bakery);

        ArrayList<Room> unlockedRooms = new ArrayList<>();
        for (Room room : allRooms) {
            if (!room.isLocked() && room != teleport_room) {
                unlockedRooms.add(room);
            }
        }

        // Choosing a random room. 
        Random random = new Random();
        int randomIndex = random.nextInt(unlockedRooms.size());
        Room randomRoom = unlockedRooms.get(randomIndex);

        System.out.println("You entered the portal in the teleport room!! You have been teleported");
        player.setCurrentRoom(randomRoom);
        System.out.println(player.getCurrentRoom().getLongDescription());
        return;
    }

    // ===== NORMAL MOVEMENT =====
    else {
        int damagePerMove = 5;
        
        player.setCurrentRoom(nextRoom);
        player.damagePlayer(damagePerMove);
        System.out.println("You took " + damagePerMove + " damage while moving.");
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println("Current Health: " + player.getHealth());
        


    }
}

    
//===============================================================================
//====================    Command Implementations   =============================
//===============================================================================

    /**
     * Attempts to take an item from the current room and place it into 
     * the player's inventory, respecting weight limits.
     *
     * @param command The command specifying which item to take.
     */
    private void takeItem(Command command) 
        { 
            if (!command.hasSecondWord()) {
            System.out.println("Take What?");
            return;
        }

        String itemName = command.getSecondWord();

        // Get item from the room without removing it yet
        Item item = player.getCurrentRoom().getItem(itemName); // you need a getItem method in Room

        if (item == null) {
            System.out.println("There is no such item there");
            return;
        }

        // Try to add it to player's inventory
        boolean added = player.addItem(item);

        if (added) {
            // Only remove from the room if successfully added
            player.getCurrentRoom().removeItem(itemName);
        }  
}

//===============================================================================
    
    /**
     * Drops an item from the player's inventory into the current room.
     *
     * @param command The command specifying which item to drop.
     */
    private void dropItem(Command command) 
    {
        //If command has only one word
        if (!command.hasSecondWord())
        {
            System.out.println("drop what?");
        }
        
        //Get the second command word
        String itemName = command.getSecondWord();
        
        //Remove item from the room
        Item droppedItem = player.removeItem(itemName);
        
        //Checks if the item exists
        if (droppedItem == null)
        {
            System.out.println("You don't have that item");
        }
        else
        {
            //Adding the item to room the player is in
            player.getCurrentRoom().addItem(droppedItem);
            System.out.println("You dropped: " + itemName);


            
        }
        
}

    
//===============================================================================
    
    /**
     * Displays the player's current status including health, inventory
     * weight, and current room.
     */
    private void printStatus() 
    {
    //String of player stats
    System.out.println("=== PLAYER STATUS ===");
    System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
    System.out.println("Inventory Weight: " + player.getInventoryWeight() + "/" + 30);
    System.out.println("Current Room: " + player.getCurrentRoom().getShortDescription());

}

    
    //===============================================================================
    
    /**
     * Challenge Task
     * Moves the player back to their previous room, if possible.
     */
    private void goBack() 
    {
        //Return condition
        if (player.goBack())
        {
            System.out.println("You returned to : " + player.getCurrentRoom().getLongDescription());
        }
        else
        {
            System.out.println("You can't return any further back");
        }   
        
}

    //===============================================================================
    
    /**
     * Attempts to consume an item in the player's inventory. Consumable items
     * restore health and are removed after use.
     *
     * @param command The command specifying which item to eat.
     */
    private void eatItem(Command command) 
    {
        // If command has one word
       if (!command.hasSecondWord())
        {
            System.out.println("Eat what?");
            return;
        }
        
        //Obtain item namae and get it from the inventory
        String itemName = command.getSecondWord();
        Item itemToEat = player.getItemFromInventory(itemName);
        
        //Check if item exists in inventory
        if (itemToEat == null)
        {

            System.out.println("You don't have that item in your inventory");
        }
        
        //Check if item is consumable
        else if (!itemToEat.isConsumable())
        {

            System.out.println("You cannot eat the " + itemToEat.getItemName());
        }
        else
        {
        //Main code to eat an item
        player.healPlayer(itemToEat.getHealthPoints());
        player.removeItem(itemToEat.getItemName());
        System.out.println("You ate the " + itemToEat.getItemName() + " and recovered " + itemToEat.getHealthPoints() + " health points "); 
        System.out.println("Your current health is : " + player.getHealth() + " points");
        } 
}

    //===============================================================================
    
    /**
     * Initiates dialogue with an NPC in the current room. If the NPC is hostile,
     * they attack instead of speaking.
     *
     * @param command The command specifying which NPC to talk to.
     */
    private void talkToNPC(Command command) 
    {
        //Check if command has a second word
        if (!command.hasSecondWord())
        {

            System.out.println("Talk to whom?");
        }
        
        
        //Get the npc name and check if the NPC in the room
        String npcName = command.getSecondWord();
        NPC npc = player.getCurrentRoom().getNPC();
        
        //Checks if NPC is null
        if (npc == null)
        {

            System.out.println("There is no one here to talk to");
            return;
        }
        
        //Check if NPC name is the same
        if (!npc.getName().equals(npcName))
        {

            System.out.println("There is no " + npcName + " here to talk to");
            return;
        }
        
        //Check if NPC is hostile
        if (npc.isHostile()) 
        {
            System.out.println(npcName + " attacks you!");
            npc.attack(player);
            return;
            
        }
        

        
        
        //Get the dialogue
        System.out.println(npc.getDialogue());
        
}

    
//===============================================================================
    
    /**
     * Gives an item to an NPC. Handles special quest behaviour such as 
     * giving the reactor cube (quest item) to Dr. Sprinkleford or delivering golden donuts 
     * to Old Man Krusty to win the game.
     *
     * @param command The command specifying the item and NPC.
     */
    private void giveItemToNPC(Command command) 
    {
        //Ensure that the command is valid
        if (!command.hasSecondWord() || !command.hasThirdWord()) {
        System.out.println("Usage: give <item> <npc>");
        return; 
    }

    
    //Get second and third word of command along with NPC name
    String itemName = command.getSecondWord();
    String npcName = command.getThirdWord();
    NPC npc = player.getCurrentRoom().getNPC();
    
    //Ensures NPC is matched
    if (npc == null || !npc.getName().equals(npcName)) {
        System.out.println("There is no " + npcName + " here to give an item to.");
        return;
    }

    // Check if player has the item
    Item itemToGive = player.getItemFromInventory(itemName);
    if (itemToGive == null) {
        System.out.println("You don't have " + itemName + " in your inventory.");
        return;
    }

    // ----  Dr. Sprinkleford  Case ----
    if (npc.getName().equals("drSprinkleford") && itemToGive.getItemName().equals("reactorCube")) {
        player.removeItem(itemName); // Remove reactor cube from inventory
        Item specialKey = new Item("specialKey", "The key to the Glazed Lair", 0);
        player.addItem(specialKey);
        System.out.println("Dr. Sprinkleford takes the reactor cube and gives you the special key!");
        return;
    }

    // ---- Old Man Krusty Case ----
    if (npc.getName().equals("oldManKrusty") && itemToGive.getItemName().equals("goldenDonut")) 
    {
    player.removeItem(itemName);
    npc.getInventory().add(itemToGive);
    
    // Count golden donuts manually
    int goldenDonutsGiven = 0;
    for (Item item : npc.getInventory()) {
        if (item.getItemName().equals("goldenDonut")) {
            goldenDonutsGiven++;
        }
    }
    
    int remaining = 3 - goldenDonutsGiven; // how many he still needs
    if (remaining > 0) 
    {
        System.out.println("OldManKrusty: \"Thank you! I only need " + remaining + " more golden donut(s)\"");
    } 
    else 
    {
        System.out.println("OldManKrusty: \"You did it! Thank you for bringing me 3 golden donuts and saving my bakery! \"");
        System.out.println("CONGRATULATIONS! YOU WIN THE GAME!");
        finished = true;    
    }

    return; 
    }
    
    // ---- Default behavior for other items ----
    player.removeItem(itemName);
    npc.getInventory().add(itemToGive);
    System.out.println("You gave " + itemName + " to " + npcName + ".");
}

    
//===============================================================================
    
    
    /**
     * Initiates combat with an NPC using a specified weapon. NPCs attack first
     * if they are hostile. Handles NPC death, item drops, and combat feedback.
     *
     * @param command The command specifying the NPC and weapon.
     */
    private void attackNPC(Command command) 
    {
    
    // Check command format
    if (!command.hasSecondWord() || !command.hasThirdWord()) {
        System.out.println("Usage: attack <npc> <weapon>");
        return;
    }

    String npcName = command.getSecondWord();
    String weaponName = command.getThirdWord();
    
    // Find NPC in the current room
    NPC npc = player.getCurrentRoom().getNPC();
    if (npc == null || !npc.getName().equals(npcName)) {
        System.out.println("There is no " + npcName + " here to attack.");
        return;
    }
    
    // Get weapon from player's inventory
    Item weapon = player.getItemFromInventory(weaponName);
    if (weapon == null || !weapon.isWeapon()) {
        System.out.println("You don't have a usable weapon named '" + weaponName + "'!");
        return;
    }
    
    
    // Make NPC hostile if not already
    if (!npc.isHostile()) {
        System.out.println(npcName + " is now hostile. Prepare for battle!");
        npc.turnHostile();
        return; // End turn, NPC will attack next time
    }

    

    // NPC attacks player first if alive and hostile
    if (npc.isAlive() && npc.isHostile()) {
        npc.attack(player);
    }

    // Player attacks NPC
    npc.damageNPC(weapon.getItemCombatPoints());
    System.out.println("You attacked " + npcName + " with your " + weapon.getItemName() +
                       " and dealt " + weapon.getItemCombatPoints() + " damage.");

    // Check if NPC died
    if (!npc.isAlive()) {
        System.out.println(npcName + " has been defeated!");
        // Drop NPC's inventory into the room
        for (Item item : npc.getInventory()) {
            System.out.println(npcName + " dropped " + item.getItemName());
            player.getCurrentRoom().addItem(item);
        }
        npc.getInventory().clear();
        player.getCurrentRoom().setNPC(null);
        } 
    
    else 
    {
    System.out.println(npcName + " has " + npc.getHealth() + " health remaining.");
    }
}

/**
 * Make the NPCs move in game. (Only works for sgtCookie as of now)
 */
private void moveNPCs() {
    // Only Sgt Cookie for now
    Room currentLocation = null;
    NPC sgtcookie = null;

    // Find Sgt Cookie and his room
    for (Room room : cookiePatrolRoute) {
        NPC npc = room.getNPC();
        if (npc != null && npc.getName().equals("sgtCookie")) {
            currentLocation = room;
            sgtcookie = npc;
            break;
        }
    }

    if (sgtcookie == null) 
    {
        return; // not found or dead
    }

    // Random next room
    Random random = new Random();
    int cookieNextRoomIndex = random.nextInt(cookiePatrolRoute.size());
    Room nextRoom = cookiePatrolRoute.get(cookieNextRoomIndex);

    // Move only if different
    if (nextRoom != currentLocation) {
        currentLocation.setNPC(null);
        nextRoom.setNPC(sgtcookie);
    }

    // If he moved into player's room → he attacks immediately
    if (player.getCurrentRoom() == nextRoom) {
        System.out.println(" Sgt Cookie marched into the room!!");
        sgtcookie.turnHostile();
        sgtcookie.attack(player);
    }
}



}


