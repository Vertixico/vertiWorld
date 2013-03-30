package entities;

import java.util.Random;
import org.newdawn.slick.*;
import map.*;
import shared.*;
/*
 * Manages Entities that can be displayed on the map, actively walk around or do stuff, 
 * interact with tiles and with each other. Entities are visibly placed on the map.
 *  
 */
public class Entity implements Runnable, InformationAccess{
	private int x;
	private int y;
	private String name = "Verti";
	private Field myField;
	private Map myMap;
	
	private Image sprite;
	
	public Entity(Field f, String name, Map m){
		myField = f;
		myMap = m;
		x = f.getX();
		y = f.getY();
		this.name = name;
		try{
			sprite = new Image("res/verti01.png");
		}catch(SlickException e){}
	}
	
	/*
	 * (non-Javadoc)
	 * @see shared.InformationAccess#getInfo()
	 */
	public String[] getInfo(){
		String[] a = {"This is a basic Verti"};
		return a;
	}
	
	/*
	 * (non-Javadoc)
	 * @see shared.InformationAccess#getName()
	 */
	public String getName(){
		return this.name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see shared.InformationAccess#getSprite()
	 */
	public Image getSprite(){
		return sprite;
	}
	
	/*
	 * @returns The current x coordinate of the Entity
	 */
	public synchronized int getX(){
		return this.x;
	}
	/*
	 * @returns The current y coordinate of the Entity
	 */
	public synchronized int getY(){
		return this.y;
	}
	/*
	 * Let the entity move to the designated coordinates.
	 * The method then checks if the corresponding field is acessable and then
	 * updates the according references.
	 */
	public synchronized void move(int x, int y){
			Field newField = myMap.getField(x, y).enter(this);
	}
	
	public synchronized Field getField(){
		return myMap.getField(x, y);
	}
	public synchronized void setField(Field field){
		this.myField = field;
		this.x = myField.getX();
		this.y = myField.getY();
	}
	public synchronized void run(){
		
		int r = new Random().nextInt(4);
		switch(r){
		case 0: this.move(x-1, y); break;
		case 1: this.move(x, y-1); break;
		case 2: this.move(x+1, y); break;
		case 3: this.move(x, y+1); break;
		}
		
	}
	
	
}
