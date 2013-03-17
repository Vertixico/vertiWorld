package items;

import org.newdawn.slick.*;
import map.*;
import shared.*;

public abstract class Item implements Runnable, InformationAccess{

	public abstract Image getSprite();
	
}
