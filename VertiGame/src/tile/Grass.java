package tile;

import org.newdawn.slick.Image;

public class Grass extends Tile {
	private final boolean passable = true;
	private int state = 0;
	@Override
	public Image getSprite() {
		return getTiles().getSprite(1 + state, 0);
	}
	
	public boolean isPassable(){
		return true;
	}
	
	public void run(){
		double p = Math.random();
		if(state < 7){
			if(p > 0.5) state ++;
		} else {
			if(p > 0.5) state = 0;
		}
	}
}
