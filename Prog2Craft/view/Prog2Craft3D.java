package view;

import java.awt.Font;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;

import factory.RenderFactory;
import game.Field;
import game.Prog2CraftGame;

public class Prog2Craft3D {

	  /** Game title */
	  public static final String GAME_TITLE = "My Game";
	 
	  /** Desired frame time */
	  private static final int FRAMERATE = 60;
	 
	  /** Exit the game */
	  private static boolean finished;
	 
	  /** Angle of rotating square */
	  @SuppressWarnings("unused")
	private static float angle;
	  
	  private static float x, y, z = 1.0f;
	  
	  public static boolean left;
	  public static boolean right;
	  public static boolean up;
	  public static boolean down;
	  
	  public static boolean zoomin;
	  public static boolean zoomout;
	 
	  public static int i;
	  
      static Font font;
      static UnicodeFont unicodeFont; 
	  
	  /**
	   * Application init
	   * @param args Commandline args
	   */
	  public static void main(String[] args) {
	    boolean fullscreen = (args.length == 1 && args[0].equals("-fullscreen"));
	    Keyboard.enableRepeatEvents(true);
	    fullscreen = true;
	    try {
	      init(fullscreen);
	      run();
	    } catch (Exception e) {
	      e.printStackTrace(System.err);
	      Sys.alert(GAME_TITLE, "An error occured and the game will exit.");
	    } finally {
	      cleanup();
	    }
	    System.exit(0);
	  }
	  
	  public static void renderGame(Prog2CraftGame game) {
	      for (Field[] out : game.getSpielfeld())
	    	  {
	    	  for (Field in : out)
	    	  	{
	    		  RenderFactory.renderField(in);
	    	  	}
	    	  }
	  }
	 
	  /**
	   * Initialise the game
	   * @throws Exception if init fails
	   */
	  private static void init(boolean fullscreen) throws Exception {
	    // Create a fullscreen window with 1:1 orthographic 2D projection (default)
	    Display.setTitle(GAME_TITLE);
	    Display.setFullscreen(fullscreen);
	 
	    // Enable vsync if we can (due to how OpenGL works, it cannot be guarenteed to always work)
	    Display.setVSyncEnabled(true);
	 
	    // Create default display of 640x480
	    Display.create();
	  }
	 
	  /**
	   * Runs the game (the "main loop")
	   */
	  private static void run() {
	 
		 Prog2CraftGame game = new Prog2CraftGame();
		 try {
			unicodeFont = new UnicodeFont("data/arial.ttf", 48, false, false);
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
	    while (!finished) {
	      // Always call Window.update(), all the time - it does some behind the
	      // scenes work, and also displays the rendered output
	      Display.update();
	 
	      // Check for close requests
	      if (Display.isCloseRequested()) {
		finished = true;
	      } 
	 
	      // The window is in the foreground, so we should play the game
	      else if (Display.isActive()) {
	        logic(game);
	        render(game);
	        Display.sync(FRAMERATE);
	      } 
	 
	      // The window is not in the foreground, so we can allow other stuff to run and
	      // infrequently update
	      else {
	        try {
	          Thread.sleep(100);
	        } catch (InterruptedException e) {
	        }
	        logic(game);
	 
		// Only bother rendering if the window is visible or dirty
	        if (Display.isVisible() || Display.isDirty()) {
	          render(game);
	        }
	      }
	    }
	  }
	 
	  /**
	   * Do any game-specific cleanup
	   */
	  private static void cleanup() {
	    // Close the window
	    Display.destroy();
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
					finished = true;
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

	  
	  /**
	   * Render the current frame
	   */
	  private static void render(Prog2CraftGame game) {
	 
			GL11.glShadeModel(GL11.GL_SMOOTH);
		    GL11.glMatrixMode(GL11.GL_PROJECTION);
		    GL11.glLoadIdentity();
		    GL11.glOrtho(0, Display.getDisplayMode().getWidth(), 0, Display.getDisplayMode().getHeight(), -1.0, 1.0);
		    GL11.glMatrixMode(GL11.GL_MODELVIEW);
		 
	    // clear the screen
	    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
	 
	    // center square according to screen size
	    GL11.glPushMatrix();
	 //   GL11.glTranslatef(Display.getDisplayMode().getWidth() / 2, Display.getDisplayMode().getHeight() / 2, 0.0f);
	    GL11.glTranslatef(x*5, y*5, 0.0f);
	    GL11.glScalef(z, z, z);
	      // rotate square according to angle
	//      GL11.glRotatef(angle, 0, 0, 1.0f);

	    renderGame(game);
	    
	    GL11.glEnable(GL11.GL_BLEND);
	    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_ALPHA);
	    unicodeFont.drawString(20.0f, 20.0f, "this is a test"); 
	
	    GL11.glPopMatrix();
	  }
	  
	
}
