package action;

import items.*;
import entities.*;
import map.Field;
import map.Map;

/*
 * This class enables the occurring interactions for entities. 
 * An Action can be addressed with a name and executed providing a user, a target and an item
 *  for the interaction. Not nescessarily all three parts are needed, this will change the constructor.  
 */
public abstract class Action {

	private static Map activeMap;
	private static boolean initialized = false;
	
	/*
	 * Process only needs to be performed once at the start of the game.
	 * This givs the Actions access to the Map, to get fields etc.
	 */
	public static void init(Map m){
		if(!isInitialized()){
			activeMap = m;
		}
	}
	private static boolean isInitialized(){
		return initialized;
	}
	public static Map getMap(){
		return activeMap;
	}
	/*
	 * This method will do the actual execution of an action. This includes testing
	 * if it is possible, creating and destroying objects and reassigning references - like
	 * items at another place or similiar things.
	 * <p>
	 * Each type of actions needs to implement this method on its own. This is, where the magic happns.
	 */
	public abstract void execute();
	
	private static Action createAction(String name, Entity user, Entity target, Field field, Item item){
		
		
		switch(name){
		
		case "MOVE": System.out.println("Test"); return new Idle();
		
		default: return new Idle();
		
		}
	}
	/*
	 * Creates an Action that only has the user interacting with himself.
	 * <p>
	 * No, not like that you pervert!
	 * @param The name of the action, The user that is executing the action
	 * @returns The created Action, that can be executed
	 */
	public static Action createAction(String name, Entity user){
		return createAction(name, user, null, null, null);
	}
	/*
	 * Creates an Action that only has the user interacting with another target entity.
	 * 
	 * @param The name of the action, The user that is executing the action, the target
	 * @returns The created Action, that can be executed
	 */
	public static Action createAction(String name, Entity user, Entity target){
		return createAction(name, user, target, null, null);
	}
	/*
	 * Creates an Action that only has the user interacting with another target field.
	 * 
	 * @param The name of the action, The user that is executing the action, the target field
	 * @returns The created Action, that can be executed
	 */
	public static Action createAction(String name, Entity user, Field field){
		return createAction(name, user, null, field, null);
	}
	/*
	 * Creates an Action that has the user interacting with another target entity using an item.
	 * 
	 * @param The name of the action, The user that is executing the action, the target entity, the used item
	 * @returns The created Action, that can be executed
	 */
	public static Action createAction(String name, Entity user, Entity target, Item item){
		return createAction(name, user, target, null, item);
	}
	/*
	 * Creates an Action that has the user interacting with another target field using an item.
	 * 
	 * @param The name of the action, The user that is executing the action, the target field, the used item
	 * @returns The created Action, that can be executed
	 */
	public static Action createAction(String name, Entity user, Field field, Item item){
		return createAction(name, user, null, field, item);
	}
}
