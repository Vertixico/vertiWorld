package entities;

import java.util.Random;
import org.newdawn.slick.*;
import map.*;
import shared.*;

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
	
	public synchronized void run(){
		
		int r = new Random().nextInt(4);
		switch(r){
		case 0: this.move(x-1, y); break;
		case 1: this.move(x, y-1); break;
		case 2: this.move(x+1, y); break;
		case 3: this.move(x, y+1); break;
		}
		
	}
	
	public String getName(){
		return this.name;
	}
	public Image getSprite(){
		return sprite;
	}
	
	public synchronized void move(int x, int y){
			Field newField = myMap.getField(x, y).enter(this);
			if(newField != null){
				myField.leave(this);
				this.myField = newField;
				this.x = myField.getX();
				this.y = myField.getY();
			
			}
			
	}
	public synchronized int getX(){
		return this.x;
	}
	public synchronized int getY(){
		return this.y;
	}
	
	public String[] getInfo(){
		String[] a = {"This is a basic Verti"};
		return a;
	}
}
