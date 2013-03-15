package shared;

import java.util.List;
import items.*;

public interface ItemContainer {

	public void insertItem(Item item);
	
	public boolean hasItem(Item item);
	
	public boolean hasItemType(Item exampleItem);
	
	public Item getItem(Item desiredItem);
	
	public Item 		getItemLike(Item exampleItem);
	
	public List<Item> 	getAllItemsLike(Item exampleItem);
	
	public int countElements();
	
	public boolean isFull();
	
	public boolean isEmpty();
	
	public Item getFirst();
	
	public Item getLast();
	
	public Item getRandom();
	
}
