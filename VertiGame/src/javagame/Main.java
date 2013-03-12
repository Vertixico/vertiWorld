package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import entities.Entity;
import map.*;

public class Main extends BasicGameState{
	private int state;
	private Map world;
	private int tick, day, month, year, speed;
	private int mouseStat;
	private boolean pause = false;
	
	public Main(int state){
		this.state = state;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//Initialize! Yeah
		world = new Map(35,35);
		mouseStat = 0;
		
		tick = 0;
		day = 1;
		month =1;
		year = 1;
		speed = 5;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//Actually Draws things on the screen
		world.drawMap(g);
		g.drawString("Year "+ year+" Month "+month+" Day "+day, 32, 16);
		
		int d = 30*16 +5;
		int e = 15;
		g.drawString("Placing...:", 30*16 +5, e*1);
		g.drawString("W: Water", 	30*16 +5, e*2);
		g.drawString("G: Grass", 	30*16 +5, e*3);
		g.drawString("V: Vertis", 	30*16 +5, e*4);
		
		if(!pause){
			g.drawString("Space for Pause", 30*16 +5, e*20);
		}else{
			g.drawString("<<Paused>>", 30*16 +5, e*20);
		}
		
		g.drawString("U: Faster", 30*16 +5, e*22);
		g.drawString("I: Slower", 30*16 +5, e*23);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//For Da Changes
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP)){
			if(world.offsetY > 0) world.offsetY--;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			if(world.offsetY < world.yDim-world.screenDimY) world.offsetY++;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			if(world.offsetX > 0) world.offsetX--;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			if(world.offsetX < world.xDim-world.screenDimX) world.offsetX++;
		}
		
		/*
		 * Menue. Changing state for Clicking
		 */
		
		if(input.isKeyDown(Input.KEY_W)){	//Create Water
			System.out.println("Now placing Water");
			mouseStat = 0;
		}
		if(input.isKeyDown(Input.KEY_G)){	//Create Grass
			System.out.println("Now placing Grass");
			mouseStat = 1;
		}
		if(input.isKeyDown(Input.KEY_V)){	//Create Verti
			System.out.println("Now placing vertis");
			mouseStat = 5;
		}
		
		if(input.isKeyDown(Input.KEY_U)){	//Faster
			if(speed < 9){
				speed ++;
				System.out.println("Going Faster");
			}
		}
		if(input.isKeyDown(Input.KEY_I)){	//Slower
			if(speed > 0){
				speed --;
				System.out.println("Going Faster");
			}
		}
		/*
		 * Actions at MouseClick. MouseClick Left depends on the status of the menue
		 */
		if(input.isMouseButtonDown(0)){
			switch(mouseStat){
			case 0:
				world.toggleField(input.getMouseX(), input.getMouseY());
				break;
			case 1:
				world.toggleField(input.getMouseX(), input.getMouseY(), 1);
			//	world.addActive(world.realX(input.getMouseX()), world.realY(input.getMouseY()));
				break;
			case 5:
				world.createEntity(world.realX(input.getMouseX()), world.realY(input.getMouseY()));
			}
			
			
		}
		
		
		//Toggeling Pause
		if(input.isKeyDown(Input.KEY_SPACE)){
			pause = !pause;
		}
		
		if(!pause){
			world.runFields();
			world.runEntities();
			
			
			tick ++;
			if(tick % 24 == 0){
				day ++;
			}
			if(day >= 30){
				day = 1;
				month ++;
			}
			if(month >= 12){
				month = 1;
				year ++;
			}
		}
		try{
			int fast = 100 - (10 * speed);
			Thread.sleep(fast);
		}catch(InterruptedException e){}
	}
	
	public int getID(){
		return this.state;
	}
}
