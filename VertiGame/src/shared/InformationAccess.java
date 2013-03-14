package shared;

import org.newdawn.slick.*;

/*
 * The Interface Information represents methods for any object, 
 * whose information can be requested via the game.
 */
public interface InformationAccess{
	
	public Image getSprite();
	
	public String getName();
	
	public String[] getInfo();
}
