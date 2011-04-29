package view;

import org.newdawn.slick.*;

import enums.ActorType;


import game.Actor;
import game.Field;
import game.Prog2CraftGame;

public class Prog2Craft2D extends BasicGame{


	  public Prog2Craft2D() {
			super("Prog2Craft");
		}
	  
	  private Prog2CraftGame game;
	  public static int breite, hoehe;
	  
	  public static void main(String[] args) throws SlickException{
		 
	    AppGameContainer app = new AppGameContainer( new Prog2Craft2D() );
	    //app.setDisplayMode(1920, 1080, true);
	    //app.setDisplayMode(800, 600, false);
	    app.setDisplayMode(1024, 600, false);
	    breite = app.getWidth();
	    hoehe = app.getHeight();
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
		 game.addPlayer();
		 Field f1 = game.getMap().getField(0, 0);
		 f1.setActor(new Actor(f1, ActorType.INFANTARIE));
		 game.getPlayer(0).select(f1.getActor());
		 
		 gc.getInput().addMouseListener(new MouseL());
		 gc.setTargetFrameRate(60);
		 gc.setMultiSample(4);
		 gc.setVSync(true);
			
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		KeyboardL.input(gc, delta, game);
	}
	  
	
}
