package action;

/*
 * Default type of action. Nothing will happen, except a tick will pass for that entity.
 */
public class Idle extends Action {

	@Override
	public void execute() {
		return;
	}

}
