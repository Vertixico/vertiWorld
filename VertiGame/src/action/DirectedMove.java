package action;
import entities.*;
import map.Field;

public class DirectedMove extends Action {

	private Entity user;
	private Field start;
	private Field target;
	private Move theMove;
	
	public DirectedMove(Entity user, int direction){
		/*
		 * Movement Directions:
		 *  7   0   1
		 *    \ | /
		 *  6 - x - 2
		 *    / | \
		 *  5   4   3
		 */
		this.start = user.getField();
		int x = start.getX();
		int y = start.getY();
		
		switch(direction){
		case 0:
			y = y+1;			break;
		case 1:
			y = y+1; x = x+1; 	break;
		case 2:
			x = x+1; 			break;
		case 3:
			x = x+1; y = y-1; 	break;
		case 4:
			y = y-1;			break;
		case 5:
			y = y-1; x = x-1;	break;
		case 6:
			x = x-1;			break;
		case 7:
			y = y-1; x = x-1;	break;
		default:
		}
		theMove= new Move(user, getMap().getField(x, y));
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
