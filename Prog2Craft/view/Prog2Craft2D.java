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
	  private KeyboardL keyL; 
	  public static int breite, hoehe;
	  
	  public void main(String[] args) throws SlickException{
		game = new Prog2CraftGame();
	    AppGameContainer app = new AppGameContainer( new Prog2Craft2D() );
	    app.setDisplayMode(game.getConfig().getBreite(), game.getConfig().getHoehe(), game.getConfig().isFullscreen());
	    breite = app.getWidth();
	    hoehe = app.getHeight(); 
	    app.start();

	  }

	@Override
	public void render(GameContainer gc, Graphics g) {
	  game.render(g, game.getCam());
	}

	@Override
	public void init(GameContainer gc) throws SlickException {	
		 
		 Field f1 = game.getMap().getField(0, 0);
		 f1.setActor(new Actor(f1, ActorType.INFANTARIE));
		 Field f2 = game.getMap().getField(1, 1);
		 f2.setActor(new Actor(f2, ActorType.INFANTARIE));
		 
		 gc.getInput().addMouseListener(new MouseL(game, gc));
		 keyL = new KeyboardL(game, gc);
		 gc.setTargetFrameRate(60);
		 gc.setMultiSample(4);
		 gc.setVSync(true);
		 gc.getGraphics().setAntiAlias(true);
			
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		keyL.input(gc, delta, game);
	}
	  
	
}
