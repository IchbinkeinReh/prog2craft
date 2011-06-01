package view;

import org.newdawn.slick.*;

import enums.ActorType;
import enums.Modus;


import game.Actor;
import game.Config;
import game.Field;
import game.KeyboardL;
import game.Mode;
import game.MouseL;
import game.Player;
import game.Prog2CraftGame;

public class Prog2Craft2D extends BasicGame{


	  public Prog2Craft2D() {
			super("Prog2Craft");
		}
	  
	  private Prog2CraftGame game;
	  private KeyboardL keyL; 
	  private Mode mode = new Mode(Modus.NOTHING);
	  
	  private int moveTime = 333;
	  private int actTime;
	  
	  
	  public static void main (String[] args) throws SlickException{
		Config conf = new Config(); 
	    AppGameContainer app = new AppGameContainer( new Prog2Craft2D() );
	    int breite = conf.getBreite();
	    int hoehe = conf.getHoehe();
	    app.setDisplayMode(breite, hoehe, conf.isFullscreen());
	    app.start();
	  }

	@Override
	public void render(GameContainer gc, Graphics g) {
	  game.render(g, game.getCam(), gc);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {	
		 game = new Prog2CraftGame(gc, mode);
		 Player owner = game.getPlayer(0);
		 Field f1 = game.getMap().getField(6, 7);
		 f1.setActor(new Actor(f1, owner, ActorType.INFANTARIE));
		 Field f2 = game.getMap().getField(7, 7);
		 f2.setActor(new Actor(f2, owner, ActorType.INFANTARIE));
		 
		 gc.getInput().addMouseListener(new MouseL(game, gc, mode));
		 keyL = new KeyboardL(game, gc, mode);
		 gc.setTargetFrameRate(60);
		 gc.setMultiSample(4);
		 gc.setVSync(true);
		 gc.getGraphics().setAntiAlias(true);
			
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		actTime += delta;
		keyL.input(gc, delta, game);
		if (actTime > moveTime) {
			game.logic(game);
			actTime = 0;
		}
	}
	  
	
}
