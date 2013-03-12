package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	private int state;
	
	Animation bucky, movingUp, movingDown, movingLeft, movingRight;
	Image worldMap;
	boolean quit = false;
	int[] duration = {200,200};
	float buckyPositionX = 0;
	float buckyPositionY = 0;
	float shiftX = buckyPositionX + 320;
	float shiftY = buckyPositionY + 160;
	
	public Play(int state){
		this.state = state;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//Initialize! Yeah
		worldMap = new Image("res/world.png");
		Image[] walkUp 		= {new Image("res/buckysBack.png"), new Image("res/buckysBack.png")};
		Image[] walkDown 	= {new Image("res/buckysFront.png"), new Image("res/buckysFront.png")};
		Image[] walkLeft 	= {new Image("res/buckysLeft.png"), new Image("res/buckysLeft.png")};
		Image[] walkRight 	= {new Image("res/buckysRight.png"), new Image("res/buckysRight.png")};
		
		movingUp 	= new Animation(walkUp, duration, false);
		movingDown 	= new Animation(walkDown, duration, false);
		movingLeft 	= new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);
		
		bucky = movingDown;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//Actually Draws things on the screen
		worldMap.draw(buckyPositionX, buckyPositionY);
		bucky.draw(shiftX, shiftY);
		g.drawString("Buckys X: " + buckyPositionX+"\nBuckys Y: "+ buckyPositionY, 400, 20);
		
		if(quit == true){
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Main Menue (M)", 250, 150);
			g.drawString("Quit (Q)", 250, 200);
			if(quit == false){
				g.clear();
			}
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//For Da Changes
		Input input = gc.getInput();
		if(!quit){
			if(input.isKeyDown(Input.KEY_UP)){
				bucky = movingUp;
				if(buckyPositionY + delta * .1f < 162)	buckyPositionY += delta * .1f; 
			}
			if(input.isKeyDown(Input.KEY_DOWN)){
				bucky = movingDown;
				if(buckyPositionY + delta * .1f > -600)  buckyPositionY -= delta * .1f; 
			}
			if(input.isKeyDown(Input.KEY_LEFT)){
				bucky = movingLeft;
				if(buckyPositionX + delta * .1f < 324) buckyPositionX += delta * .1f; 
			}
			if(input.isKeyDown(Input.KEY_RIGHT)){
				bucky = movingRight;
				if(buckyPositionX + delta * .1f > -840) buckyPositionX -= delta * .1f; 
			}
			if(input.isKeyDown(Input.KEY_ESCAPE)){
				quit = true;
			}
		}
		if(quit){
			if(input.isKeyDown(Input.KEY_R)){
				quit = false;
			}
			if(input.isKeyDown(Input.KEY_Q)){
				System.exit(0);
			}
			if(input.isKeyDown(Input.KEY_M)){
				quit=false;
				sbg.enterState(0);
			}
		}
		
	}
	
	public int getID(){
		return this.state;
	}
}
