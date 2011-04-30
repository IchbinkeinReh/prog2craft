package view;

import game.Field;
import game.Prog2CraftGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public class MouseL implements MouseListener {

	private Prog2CraftGame game;
	private Input input;
	private int x,y;
	private Camera cam;

	private Field getMouseField(Input input, Prog2CraftGame game) {
		int x = (int)(  ((-cam.getX() + input.getMouseX()) / cam.getZ()) / 100 );
		int y = (int)(  ((-cam.getY() + input.getMouseY()) / cam.getZ()) / 100 );
		return game.getMap().getField(x, y);
	}
	
	public MouseL(Prog2CraftGame game, GameContainer gc) {
		this.game = game;
		this.input = gc.getInput();
		this.cam = game.getCam();
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
		game.getPlayer(0).deselectall();
		if (getMouseField(input, game).getActor() != null)
		game.getPlayer(0).select(getMouseField(input, game).getActor());
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		x = arg0;
		y = arg1;
		
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		if (arg0 > 0) 	 cam.setZ( cam.getZ() * 1.05f ) ; 
		else 			 cam.setZ( cam.getZ() * 0.95f );
	}

}
