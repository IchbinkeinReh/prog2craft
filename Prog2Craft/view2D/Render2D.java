package view2D;

import static game.Field.FIELDSIZE;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.Field;
import game.Prog2CraftGame;

public class Render2D {

	private Image leer;
	private Image felsig;
	private Image gebirge;
	private Image meer;
	 
	
    private Render2D() throws SlickException {
    	
        leer = new Image("data/wald.jpg");
        felsig = new Image("data/felsig.jpg");
        gebirge = new Image("data/gebirgig.jpg");
        meer = new Image("data/meer.jpg");
    	
    }
    
	public void renderGame(GameContainer gc) {
	    for (Field[] out : game.getSpielfeld())
  	  {
  	  for (Field in : out)
  	  	{
  		  switch(in.getType())
  		  {
  		  case LEER:
  			  leer.draw(in.getX()*FIELDSIZE*z+x, in.getY()*FIELDSIZE*z+y, leer.getWidth()*z, leer.getHeight()*z); break;
  		  case FELSIG:
  			  felsig.draw(in.getX()*FIELDSIZE*z+x, in.getY()*FIELDSIZE*z+y, felsig.getWidth()*z, felsig.getHeight()*z); break;
  		  case GEBIRGE:
  			  gebirge.draw(in.getX()*FIELDSIZE*z+x, in.getY()*FIELDSIZE*z+y, gebirge.getWidth()*z, gebirge.getHeight()*z); break;
  		  case MEER:
  			  meer.draw(in.getX()*FIELDSIZE*z+x, in.getY()*FIELDSIZE*z+y, meer.getWidth()*z, meer.getHeight()*z); break;
  		  }
  	  	}
  	  }
	}
	
}
