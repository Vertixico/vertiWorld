package tile;

import org.newdawn.slick.Image;

public class Water extends Tile {

	@Override
	public Image getSprite() {
		return getTiles().getSprite(0, 0);
	}
	
	public boolean isPassable(){
		return false;
	}
	
	public void run(){
		
	}
}
