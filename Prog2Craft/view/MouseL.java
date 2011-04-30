package view;

import game.Field;
import game.Mode;
import game.Prog2CraftGame;
import static enums.Modus.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public class MouseL implements MouseListener {

	private Prog2CraftGame game;
	private Input input;
	private Camera cam;
	private Mode mode;

	private Field getMouseField(Input input, Prog2CraftGame game) {
		int x = (int)(  ((-cam.getX() + input.getMouseX()) / cam.getZ()) / 100 );
		int y = (int)(  ((-cam.getY() + input.getMouseY()) / cam.getZ()) / 100 );
		return game.getMap().getField(x, y);
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
		mode.setModus(SELECTING);
		mode.setX(arg1);
		mode.setY(arg2);
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		mode.setModus(NOTHING);
//		game.getPlayer(0).deselectall();
//		if (getMouseField(input, game).getActor() != null)
//		game.getPlayer(0).select(getMouseField(input, game).getActor());
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		if (arg0 > 0) 	 cam.setZ( cam.getZ() * 1.05f ) ; 
		else 			 cam.setZ( cam.getZ() * 0.95f );
	}

}
