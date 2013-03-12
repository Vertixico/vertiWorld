package javagame;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	private int state;
	
	Image playNow;
	Image exitGame;
	
	public Menu(int state){
		this.state = state;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//Initialize! Yeah
		playNow = new Image("res/playNow.png");
		exitGame = new Image("res/exitGame.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//Actually Draws things on the screen
		g.drawString("Welcome to BuckyLand!", 100, 50);
		g.drawImage(playNow, 100, 100);
		g.drawImage(exitGame, 100, 200);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//For Da Changes
		Input input = gc.getInput();
		
		//Fluffy bewegen
		if(input.isKeyDown(Input.KEY_UP)){			
			
		}
		if(input.isKeyDown(Input.KEY_DOWN)){		
			
		}
		if(input.isKeyDown(Input.KEY_LEFT)){		
			
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){	
			
		}
		
		int xPos = input.getMouseX();
		int yPos = input.getMouseY();
		
		if((xPos>100 && xPos<311) && (yPos>100 && yPos<151)){
			if(input.isMouseButtonDown(0)){
				sbg.enterState(1);
			}
		}
		if((xPos>100 && xPos<311) && (yPos>200 && yPos<251)){
			if(input.isMouseButtonDown(0)){
				System.exit(0);
			}
		}
	}
	
	public int getID(){
		return this.state;
	}
}
