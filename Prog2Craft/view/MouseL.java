package view;

import game.Field;
import game.Prog2CraftGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public class MouseL implements MouseListener {

	private Prog2CraftGame game;
	private Input input;

	private static Field getMouseField(Input input, Prog2CraftGame game) {
		int x = (int)(  ((-Camera.getX() + input.getMouseX()) / Camera.getZ()) / 100 );
		int y = (int)(  ((-Camera.getY() + input.getMouseY()) / Camera.getZ()) / 100 );
		return game.getMap().getField(x, y);
	}
	
	public MouseL(Prog2CraftGame game, GameContainer gc) {
		this.game = game;
		this.input = gc.getInput();
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
		
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		if (arg0 > 0) 	 Camera.setZ( Camera.getZ() * 1.05f ) ; 
		else 			 Camera.setZ( Camera.getZ() * 0.95f );
	}

}
