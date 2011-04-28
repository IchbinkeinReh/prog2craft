package view2D;

import static game.Field.FIELDSIZE;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.Field;
import game.Prog2CraftGame;

public class Render2D {

	 
	
    private Render2D() throws SlickException {
    	

    	
    }
    
	public void renderGame(GameContainer gc) {
	    for (Field[] out : game.getSpielfeld())
  	  {
  	  for (Field in : out)
  	  	{
//////////////renderfield
  	  	}
  	  }
	}
	
}
