package map;

import java.util.*;
import java.util.concurrent.*;
import entities.*;

import tile.*;
import org.newdawn.slick.*;

public class Map {
	Graphics g;
	
	public int xDim;
	public int yDim;
	
	public final int screenDimX = 30;
	public final int screenDimY = 30;
	
	public static int offsetX = 0;
	public static int offsetY = 0;
	
	Field[][] field;
	
	List<Field> activeFields;
	List<Entity> entities;

	public Map(int x, int y){
		xDim = Math.max(0,  x);
		yDim = Math.max(0, 	y);
		
		activeFields = new ArrayList<Field>();
		entities = new ArrayList<Entity>();
		
		if(!Tile.isInitialized()) Tile.init();
		//Filling the field with a border of water and some grass
		createFields();
		addActive(20,20);
		addEntity(new Entity(getField(20,20), "Dummi", this));
		addEntity(new Entity(getField(21,20), "Dummi", this));
		addEntity(new Entity(getField(22,20), "Dummi", this));
		addEntity(new Entity(getField(23,20), "Dummi", this));
	}
	
	private void createFields(){
		field = new Field[xDim][yDim];
		for(int i=0; i<xDim; i++){
			for(int j=0; j<yDim; j++){
				if(i==0 || i==xDim-1 || j==0 || j==yDim-1){
					field[i][j] = new Field(0, i, j);
				}else{
					field[i][j] = new Field(1, i, j);
				}
			}
			
		}
	}
	/*
	 * Preidicates
	 */
	private boolean isInView(int x, int y){
		return (x >= offsetX) && (x < offsetX + screenDimX) && (y >= offsetY) && (y < offsetY + screenDimY); 
	}
	/*
	 * Mapping functions between Pixel and Coordinates
	 */
	public static int mapToPx(int xCoord){
		return xCoord * 16;
	}
	public static int mapToCoord(int xPx){
		return (int) (xPx / 16);
	}

	public static int realX(int px){
		return mapToCoord(px) + offsetX;
	}
	public static int realY(int px){
		return mapToCoord(px) + offsetX;
	}
	
	public void drawMap(Graphics g){
		for(int i=0; i<screenDimX; i++){
			for(int j=0; j<screenDimY; j++){
				try{
				g.drawImage(field[i+offsetX][j+offsetY].getTile().getSprite(),mapToPx(i),mapToPx(j));
				}catch(IndexOutOfBoundsException e){
				
				}
			}
		}
		
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			if(isInView(e.getX(), e.getY()))
					g.drawImage(e.getSprite(), mapToPx(e.getX() - offsetX) , mapToPx(e.getY() - offsetY));
		}
	}
	
	/*
	 * Functions to manipulate Fields or insert them in the active list.
	 */
	public Field getField(int x, int y){
		return field[x][y];
	}

	public void addActive(int x, int y){
		addActive(getField(x,y));
	}
	public void addActive(Field f){
		if(!activeFields.contains(f))	activeFields.add(f);
	}
	public void removeActive(Field f){
		activeFields.remove(f);
	}
	public void createEntity(int x, int y){
		addEntity(new Entity(getField(x,y), "Dummi", this));
	}
	
	
	public void toggleField(int xPx, int yPx){
		toggleField(xPx, yPx, 0);
	}
	
	public void toggleField(int xPx, int yPx, int type){
		int x = mapToCoord(xPx) + offsetX;
		int y = mapToCoord(yPx) + offsetY;
		
		try{
			field[x][y].setType(type);
		}catch(ArrayIndexOutOfBoundsException e){}
	}
	
	/*
	 * Functions to manipulate the list of entities that are existing.
	 */
	public void addEntity(Entity e){
		if(!entities.contains(e)) entities.add(e);
	}
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	public int countEntity(){
		if(this.entities != null){
			return this.entities.size() + 1;
		}
		return 0;
	}
	
	/*
	 * Running the action
	 */
	public void runFields(){
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Iterator<Field> it = activeFields.iterator();
		while(it.hasNext()){
			executor.execute(it.next().getTile());
		}
		executor.shutdown();
		try{
			executor.awaitTermination(5, TimeUnit.SECONDS);
		}catch(InterruptedException e){}
		
	}
	
	public void runEntities(){
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			executor.execute(it.next());
		}
		executor.shutdown();
		try{
			executor.awaitTermination(5, TimeUnit.SECONDS);
		}catch(InterruptedException e){}
		
	}
}
