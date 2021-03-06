import java.util.ArrayList;
/**
 *  This class is the main class of our Space Adventure 
 *  a very simple, text based adventure game.  Users 
 *  can walk around and explore the spaceship, get and use items. 
 * 
 *  
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player inventory;
    
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        inventory = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room bridge, southHallway, southCenterHallway, centerHallway, northCenterHallway, northHallway,
             security, labOne, labTwo, labThree, bathroom, medBay, quartersSouth, quartersNorth, lounge,
             messHall, electric, lifeSupport, engineOne, engineTwo, engineMain, storageOne, storageTwo,
             observation;
        
        Item keyCard, heavyPipe, wireBundle, milk, energyCrystal;
        
        // create the rooms
        
        /*
         * these are the central rooms
         */
        bridge = new Room("in the bridge", "There are a lot of blinking panels screaming errors at you. The massive window is intact, giving you a great view of empty space.");
        southHallway = new Room("in the hallway", "There isn't much to see here. The walls are made from a dark metal, there's a small potted plant of no significance in the a corner.");
        southCenterHallway = new Room("in the hallway", "It's a hallway with four doors leading in different directions.");
        centerHallway = new Room("in the hallway", "This hallway seems like all of the other hallways you've been in.");
        northCenterHallway = new Room("in the hallway", "There sure are a lot of hallways in this ship.");
        northHallway = new Room("in the hallway", "This is the northern most hallway, in it you see a tray of spilled food that likely came from the mess hall. It looks gross.");
        
        /*
         * these are the west wing rooms
         */
        security = new Room("in the security room", "There are a lot of panels and monitors that mean very little to you.");
        labOne = new Room("in the main lab", "This is the main lab. There's a lot of lab equipment, a decent amount of it looks broken.");
        labTwo = new Room("in the bio lab", "The bio lab is full of interesting plants and cultures of who-knows-what. There's also a fishtank in the corner.");
        labThree = new Room("in the mech lab", "There are bits of random machinery, what looks like the beginnings of a new coffee machine, or maybe a robot, and a white lab coat.");
        bathroom = new Room("in the 'facilities'", "The bathroom is in peak condition. The floors sparkle bright, the flourescent lights flicker gen the stall calls your name"); //I'm not sure what this room is called yet
        
        /*
         * these are the east wing rooms
         */
        medBay = new Room(" in the med bay", "");
        quartersSouth = new Room("in the crew sleeping quarters", "There are a bunch of bunks, and none of the beds are made. What a bunch of slobs.");
        quartersNorth = new Room("in the crew sleeping quarters", "Bunks line the east side of the room, all of the beds are well made and clean. No slobs here.");
        lounge = new Room("in the crew lounge", "");
        messHall = new Room("in the mess hall", "");
        
        //lower level rooms
        electric = new Room("in the electrical room", "");
        lifeSupport = new Room("in life support", "");
        engineOne = new Room("in the engine room", "");
        engineTwo = new Room("in the engine room", "");
        engineMain = new Room("at the main engine", "");
        
        //upper level rooms
        storageOne = new Room("in storage room", "");
        storageTwo = new Room("in storage room", "");
        observation = new Room("in observation room", "");
        
        
        //main level exits, all exits move south to north
        bridge.setExit("north", southHallway);
        
        southHallway.setExit("south", bridge);
        southHallway.setExit("east", medBay);
        southHallway.setExit("west", security);
        southHallway.setExit("north", southCenterHallway);
        southHallway.setExit("down", electric);
        
        southCenterHallway.setExit("south", southHallway);
        southCenterHallway.setExit("east", quartersSouth);
        southCenterHallway.setExit("west", labOne);
        southCenterHallway.setExit("north", centerHallway);
        
        centerHallway.setExit("south", southCenterHallway);
        centerHallway.setExit("east", quartersNorth);
        centerHallway.setExit("west", labTwo);
        centerHallway.setExit("north", northCenterHallway);
        centerHallway.setExit("up", observation);
        
        northCenterHallway.setExit("south",centerHallway);
        northCenterHallway.setExit("east", lounge);
        northCenterHallway.setExit("west", labThree);
        northCenterHallway.setExit("north", northHallway);
        
        northHallway.setExit("south", northCenterHallway);
        northHallway.setExit("east", messHall);
        northHallway.setExit("west", bathroom);
        northHallway.setExit("down", engineMain);
        
        medBay.setExit("west", southHallway);
        
        quartersSouth.setExit("west", southCenterHallway);
        quartersSouth.setExit("north", quartersNorth);
        
        quartersNorth.setExit("south", quartersSouth);
        quartersNorth.setExit("west", centerHallway);
        
        lounge.setExit("west", northCenterHallway);
        
        messHall.setExit("west", northHallway);
        
        security.setExit("east", southHallway);
        
        labOne.setExit("east", southCenterHallway);
        
        labTwo.setExit("east", centerHallway);
        
        labThree.setExit("east", northCenterHallway);
        
        bathroom.setExit("east", northHallway);
        
        //lower level exits
        electric.setExit("north", lifeSupport);
        
        lifeSupport.setExit("south", electric);
        lifeSupport.setExit("north", engineOne);
        
        engineOne.setExit("south", lifeSupport);
        engineOne.setExit("north", engineTwo);
        
        engineTwo.setExit("south", engineOne);
        engineTwo.setExit("north", engineMain);
        
        engineMain.setExit("south", engineTwo);
        
        //upper level exits
        observation.setExit("south", storageOne);
        observation.setExit("north", storageTwo);
        
        storageOne.setExit("north", observation);
        
        storageTwo.setExit("south", observation);
        
        //Creates new items with description and weight
        keyCard = new Item("keycard", "a keycard that will unlock a door", 0.5);
        wireBundle = new Item("wires", "a small bundle of wires", 1);
        heavyPipe = new Item("pipe", "a very heavy pipe, good for bashing things", 15);
        milk = new Item("milk", "some milk that might be bad", .4);
        energyCrystal = new Item("crystal", "an energy crystal that powers the engine", 5);
        

        currentRoom = quartersSouth;  // start game in sleeping quarters
        
        //adds items to a specified room
        quartersNorth.addItem("keycard", keyCard);
        labTwo.addItem("pipe", heavyPipe);
        labTwo.addItem("wires", wireBundle);
        messHall.addItem("milk", milk);
        storageOne.addItem("crystal", energyCrystal);
        
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("This is a Space Adventure");
        System.out.println("It is indeed an adventure in space!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case USE:
                use(command);
                break;
              
            case GET:
                get(command);
                break;
            
            case LOOK:
                lookRoom(command);
                break;     
            
            case INVENTORY:
                showInventory(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are on a space ship. Red lights are flashing");
        System.out.println("An alarm is sounding.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    private void showInventory(Command command) {
        if (inventory == null) {
            System.out.println("There's nothing here.");
        }
        else {
            System.out.println(inventory.getContents());
       }
    }
    
    /** 
     * Try to use an item. If there is a use for the item, remove it
     * from the inventory, otherwise print an error message.
     */
    private void use(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to use...
            System.out.println("Use what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        Item item = inventory.getItem(itemName);
        
        if (item == null){
            System.out.println("You can't use that.");
        }
        else {
            inventory.removeItem(itemName);
            System.out.println("You used " + itemName);
        }
    }
    
    /** 
     * Try to pick up item in room. If there is an item, add it
     * to the inventory, otherwise print an error message.
     */
    private void get(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("What item?");
            return;
        }
        String itemName = command.getSecondWord();
        
        Item item = currentRoom.getItem(itemName);
        
        if(item == null) {
            System.out.println("There is no item.");
        }
        else {
            currentRoom.removeItem(itemName);
            inventory.addItem(itemName, item);
            System.out.println("You added " + itemName + " to your inventory.");
        }        
    }
    
    private void lookRoom (Command command) {
        System.out.println(currentRoom.getLookDescription());
    }

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
}
