package map;

import shared.InformationAccess;
import tile.*;
import org.newdawn.slick.*;

import entities.*;
import items.*;

import java.util.*;

public class Field implements InformationAccess{
	private int x;
	private int y;
	
	private Tile type;
	private List<Entity> visitor;
	private List<Item> item;
	
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
	public synchronized void leave(Entity e){
		visitor.remove(e);
	}
	

	public synchronized boolean hasVisitor(){
		return !visitor.isEmpty();
	}
	
	public synchronized Entity firstVisitor(){
		if(!visitor.isEmpty()){
			return visitor.get(0);
		}
		return null;
	}
	
	public void insert(Item i){
		item.add(i);
	}
	
	public Item take(Item i){
		if(item.remove(i))	return i;
		return null;
	}
	public Item getItemType(Item i){
		Iterator<Item> it = item.iterator();
		while(it.hasNext()){
			Item a = it.next();
			if(a.getClass() == i.getClass())	return a;
		}
		return null;
	}
	
	public synchronized Item getItem(){
		if(!item.isEmpty()){
			return item.get(0);
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

	@Override
	public Image getSprite() {
		return getTile().getSprite();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Feld";
	}

	@Override
	public String[] getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
