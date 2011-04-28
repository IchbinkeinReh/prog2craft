package view;

import org.newdawn.slick.*;

import game.Field;
import game.Prog2CraftGame;

public class Prog2Craft2D extends BasicGame{
  
	  public static float x = 0.0f , y = 0.0f , z = 1.0f;
	  
	  private Image leer;
	  private Image felsig;
	  private Image gebirge;
	  private Image meer;
	  
	  private Prog2CraftGame game = new Prog2CraftGame();
	  
	  static int FIELDSIZE = 100;
	  
	  public Prog2Craft2D() {
			super("Prog2Craft");
		}
      
	  public static void main(String[] args) throws SlickException{
		
	    AppGameContainer app = new AppGameContainer( new Prog2Craft2D() );
	    //app.setDisplayMode(1920, 1080, true);
	    app.setDisplayMode(1024, 800, false);
	    app.start();

	  }

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
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

	@Override
	public void init(GameContainer gc) throws SlickException {
	      leer = new Image("data/wald.jpg");
	      felsig = new Image("data/felsig.jpg");
	      gebirge = new Image("data/gebirgig.jpg");
	      meer = new Image("data/meer.jpg");
	
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
