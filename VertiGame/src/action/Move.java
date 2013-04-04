package action;
import entities.*;
import map.Field;

public class Move extends Action {

	private Entity user;
	private Field target, start;
	
	public Move(Entity user, Field target){
		this.user = user;
		this.start = user.getField(); 
		this.target = target;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(start.isLeaveable()){
			if(target.isPassable()){
				start.leave(user);
				target.enter(user);
				user.setField(target);
			}
		}
	}

}
