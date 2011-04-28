package view2D;

import org.newdawn.slick.*;

import view.Camera;
import view.MouseL;

import game.Prog2CraftGame;

public class Prog2Craft2D extends BasicGame{


	  public Prog2Craft2D() {
			super("Prog2Craft");
		}
	  
	  private Prog2CraftGame game;
	
	  
	  public static void main(String[] args) throws SlickException{
		 
	    AppGameContainer app = new AppGameContainer( new Prog2Craft2D() );
	    //app.setDisplayMode(1920, 1080, true);
	    app.setDisplayMode(800, 600, false);
	    app.start();

	  }

	@Override
	public void render(GameContainer gc, Graphics g) {
	  game.render();
	}

	@Override
	public void init(GameContainer gc) throws SlickException {	
		 try {
			  game = new Prog2CraftGame();
			 
			  }
			  catch (SlickException e) {
			  }  
		 gc.getInput().addMouseListener(new MouseL());
			
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		  Input input = gc.getInput();
		
						if (input.isKeyDown(Input.KEY_ESCAPE)) {
							gc.exit();
						}
						if (input.isKeyDown(Input.KEY_UP)) {
							Camera.setY(Camera.getY() + 2 * Camera.getZ() * 1.0f);
						}
						if (input.isKeyDown(Input.KEY_DOWN)) {
							Camera.setY(Camera.getY() - 2 * Camera.getZ() * 1.0f);
						}
						if (input.isKeyDown(Input.KEY_LEFT)) {
							Camera.setX(Camera.getX() + 2 * Camera.getZ() * 1.0f);
						}
						if (input.isKeyDown(Input.KEY_RIGHT)) {
							Camera.setX(Camera.getX() - 2 * Camera.getZ() * 1.0f);
						}
	}
	  
	
}
