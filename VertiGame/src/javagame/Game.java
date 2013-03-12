package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

	public static final String gamename = "VertiGame";
	public static final int main = 0;
	

	
	public Game(String gamename){
		super(gamename);
		this.addState(new Main(main));
	
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(main).init(gc, this);		//Initiaize the two states
		this.enterState(main);					//Whats the first screen
	}
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(30 * 16+200, 30 * 16, false); 			// x and y and fullscreen
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}

}
