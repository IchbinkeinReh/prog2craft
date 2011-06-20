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
		 Field f1 = game.getMap().getField(5, 8);
		 new Actor(f1, owner, ActorType.PANZER);
		 Field f2 = game.getMap().getField(5, 9);
		 new Actor(f2, owner, ActorType.PANZER);
		 Field f3 = game.getMap().getField(5, 10);
		 new Actor(f3, owner, ActorType.PANZER);
		 

		 Field f4 = game.getMap().getField(6, 8);
		 new Actor(f4, owner, ActorType.LAUFPANZER);
		 Field f5 = game.getMap().getField(6, 9);
		 new Actor(f5, owner, ActorType.CHUCKNORRIS);
		 Field f6 = game.getMap().getField(6, 10);
		 new Actor(f6, owner, ActorType.LAUFPANZER);
		 

		 Field f7 = game.getMap().getField(7, 8);
		 new Actor(f7, owner, ActorType.INFANTARIE);
		 Field f8 = game.getMap().getField(7, 9);
		 new Actor(f8, owner, ActorType.INFANTARIE);
		 Field f9 = game.getMap().getField(7, 10);
		 new Actor(f9, owner, ActorType.INFANTARIE);
		 
		 gc.getInput().addMouseListener(new MouseL(game, gc, mode));
		 keyL = new KeyboardL(game, gc, mode);
		 gc.setTargetFrameRate(120);
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
