package view;

import java.awt.Font;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;

import game.Field;
import game.Prog2CraftGame;

public class Prog2Craft2D extends BasicGame{
  
	  private static float x, y, z = 1.0f;
	  public static boolean left;
	  public static boolean right;
	  public static boolean up;
	  public static boolean down;
	  public static boolean zoomin;
	  public static boolean zoomout;
	  
	  private Image leer;
	  private Image land;

	  
      static Font font;
      static UnicodeFont unicodeFont; 
	  
	  public Prog2Craft2D() {
			super("Prog2Craft");
		}
      
	  public static void main(String[] args) throws SlickException{
		
	    AppGameContainer app = new AppGameContainer( new Prog2Craft2D() );
	    app.setDisplayMode(800, 600, false);
	    app.start();

	  }
	  
	  
	  /**
	   * Do all calculations, handle input, etc.
	   */
	  private static void logic(Prog2CraftGame game) {
	    // Example input handler: we'll check for the ESC key and finish the game instantly when it's pressed
		while (Keyboard.next()) {
			
			switch (Keyboard.getEventKey())
				{
				case Keyboard.KEY_ESCAPE:
					
					break;
				case Keyboard.KEY_UP:
					if (Keyboard.getEventKeyState())
						up = true;
					else
						up = false;
					break;
				case Keyboard.KEY_DOWN:
					if (Keyboard.getEventKeyState())
						down = true;
					else
						down = false;
					break;
				case Keyboard.KEY_LEFT:
					if (Keyboard.getEventKeyState())
						left = true;
					else
						left = false;
					break;
				case Keyboard.KEY_RIGHT:
					if (Keyboard.getEventKeyState())
						right = true;
					else
						right = false;
					break;	
				case Keyboard.KEY_W:
					if (Keyboard.getEventKeyState())
						zoomin = true;
					else
						zoomin = false;
					break;	
				case Keyboard.KEY_S:
					if (Keyboard.getEventKeyState())
						zoomout = true;
					else
						zoomout = false;
					break;	
				}
			}
			 
	    // Rotate the square
		if (left) //  && x > game.getBreite()*100
				x -= 2*z*1.0f; 
		if (right && x < 0) 
				x += 2*z*1.0f; 
		if (up && y < 0)
				y += 2*z*1.0f; 
		if (down) //  && y > game.getHoehe()*100
				y -= 2*z*1.0f;
		if (zoomin && z < 1.5f)
				z += 0.01f;
		if (zoomout && z > 0.5f)
				z -= 0.01f;

	   // angle += 2.0f % 360;
	  }


	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		land.draw(0, 0);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
	      leer = new Image(this.getClass().getResource("data/wald.jpg").toString());
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	  
	
}
