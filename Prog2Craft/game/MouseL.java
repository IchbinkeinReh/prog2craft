package game;

import static enums.Modus.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public class MouseL implements MouseListener {

	private Prog2CraftGame game;
	private Input input;
	private Camera cam;
	private Mode mode;
	
	private Field getMouseField(int xxx, int yyy) {
		int x = (int)(  ((-cam.getX() + xxx) / cam.getZ()) / 100 );
		int y = (int)(  ((-cam.getY() + yyy) / cam.getZ()) / 100 );
		try { return game.getMap().getField(x, y); } catch (IllegalArgumentException e) { return null; }
	}
	
	//TODO unused printMouseMapCoord
	@SuppressWarnings("unused")
	private void printMouseMapCoord() {
		int x = (int)(  ((-cam.getX() + input.getMouseX()) / cam.getZ()) / 100 );
		int y = (int)(  ((-cam.getY() + input.getMouseY()) / cam.getZ()) / 100 );
		System.out.println("( " + x + ", " + y + " )");
	}
	
	private void select(int x1, int y1, int x2, int y2) {
		int minX = Math.min(x1, x2);
		int minY = Math.min(y1, y2);
		int maxX = Math.max(x1, x2);
		int maxY = Math.max(y1, y2);
		
		for (int x = minX; x < maxX; x += 100 * cam.getZ())
		{
			for(int y = minY; y < maxY; y += 100 * cam.getZ())
			{
			Field f = getMouseField(x,y); 
			if (f != null && f.getActor() != null)
				 game.getPlayer(0).select(f.getActor()); 
			}
		}
	}
	
	public MouseL(Prog2CraftGame game, GameContainer gc, Mode mode) {
		this.game = game;
		this.input = gc.getInput();
		this.cam = game.getCam();
		this.mode = mode;
	}

	@Override
	public void inputEnded() {
		
		
	}

	@Override
	public void inputStarted() {
		
		
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input arg0) {
	
		
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		Field f = getMouseField(arg1, arg2);
		switch (arg0) {
			case 0:
				game.getPlayer(0).deselectall();
				if (f != null && f.getActor() != null)
				game.getPlayer(0).select(f.getActor());
				mode.setModus(SELECTING);
				mode.setX(arg1);
				mode.setY(arg2);
				break;
			case 1:
				for (Actor actor : game.getPlayer(0).getSelected())
				{
					actor.setTarget(f);
				}
				break;
		}
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		switch (arg0) {
			case 0:
				mode.setModus(NOTHING);
				select(mode.getX(), mode.getY(), arg1, arg2);
				break;
			case 1:
				//bewegung
				break;
		}
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		if (arg0 > 0) 	 cam.setZ( cam.getZ() * 1.05f ) ; 
		else 			 cam.setZ( cam.getZ() * 0.95f );
	}

}
