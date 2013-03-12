package tile;

import org.newdawn.slick.*;
public abstract class Tile implements Runnable{
	
	private static 			SpriteSheet 	tiles;
	private static 			boolean 		initialized = false;
	public 	static final 	int 			xSize = 16;
	public 	static final 	int 			ySize = 16;
	
	public static void init(){
		//Import of the spritesheet for tiles
		try{
			Image chipset = new Image("res/tile/tile01.png");
			tiles = new SpriteSheet(chipset, 16, 16);
		} catch(SlickException e){
			e.printStackTrace();
		}
	}
	public static boolean isInitialized(){
		return initialized;
	}
	public static Tile createTile(int id){
		switch(id){
		case 0: 	return new Water();
		case 1: 	return new Grass();
		default: 	return new Water();
		}
	}
	public SpriteSheet getTiles(){
		return tiles;
	}
	
	public abstract boolean isPassable();
	
	public abstract Image getSprite();
	
	public void run(){}
	

}
