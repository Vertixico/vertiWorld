package map;

import tile.*;
import org.newdawn.slick.*;
import entities.*;
import java.util.*;

public class Field {
	private int x;
	private int y;
	
	private Tile type;
	private List<Entity> visitor;
	
	public Field(int type, int x, int y){
		this.type = Tile.createTile(type);
		this.x = x;
		this.y = y;
		visitor = new ArrayList<Entity>();
		
	}
	
	public Tile getTile(){
		if(!Tile.isInitialized()) Tile.init();
		return this.type;
	}
	
	public void setType(int i){
		this.type = Tile.createTile(i);
	}
	
	public synchronized Field enter(Entity e){
		if(isPassable()){
			if(!visitor.contains(e)) visitor.add(e);
			return this;
		}
		return null;
	}
	public boolean isPassable(){
		return getTile().isPassable();
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	
}
