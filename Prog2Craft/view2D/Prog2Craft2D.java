package view2D;

import org.newdawn.slick.*;

import game.Field;
import game.Prog2CraftGame;

public class Prog2Craft2D extends BasicGame{


	  public Prog2Craft2D() {
			super("Prog2Craft");
		}
	  
	  private Prog2CraftGame game = new Prog2CraftGame();
	  private Render2D render;
	  
      
	  public static void main(String[] args) throws SlickException{
		
	    AppGameContainer app = new AppGameContainer( new Prog2Craft2D() );
	    //app.setDisplayMode(1920, 1080, true);
	    app.setDisplayMode(1024, 800, false);
	    app.start();

	  }

	@Override
	public void render(GameContainer gc, Graphics g) {
	  render.renderGame(gc);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {	
		 gc.getInput().addMouseListener(new MouseL());
			
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		  Input input = gc.getInput();
		
						if (input.isKeyDown(Input.KEY_ESCAPE)) {
							gc.exit();
						}
						if (input.isKeyDown(Input.KEY_UP)) {
							y += 2*z*1.0f; 
						}
						if (input.isKeyDown(Input.KEY_DOWN)) {
							y -= 2*z*1.0f;
						}
						if (input.isKeyDown(Input.KEY_LEFT)) {
							x += 2*z*1.0f;
						}
						if (input.isKeyDown(Input.KEY_RIGHT)) {
							x -= 2*z*1.0f;
						}
	}
	  
	
}
