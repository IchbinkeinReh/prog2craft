package view;

import game.Actor;
import game.Prog2CraftGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardL {
	
		public static void input(GameContainer gc, int delta, Prog2CraftGame game) { 
		Input input = gc.getInput();

			if (input.isKeyDown(Input.KEY_ESCAPE)) {
				gc.exit();
			}
			if (input.isKeyDown(Input.KEY_UP)) {
				Camera.setY(Camera.getY() + 20 * Camera.getZ() * 1.0f);
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				Camera.setY(Camera.getY() - 20 * Camera.getZ() * 1.0f);
			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				Camera.setX(Camera.getX() + 20 * Camera.getZ() * 1.0f); 
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				Camera.setX(Camera.getX() - 20 * Camera.getZ() * 1.0f);
			}
			if (input.isKeyDown(Input.KEY_MINUS)) {
				Camera.setZ(Camera.getZ() * 0.99f );
			}
			if (input.isKeyDown(Input.KEY_EQUALS)) {
				Camera.setZ(Camera.getZ() * 1.01f );
			}
			
		/*	if (input.isMousePressed(0)) {
				Camera.getX() * Camera.getZ() input.getMouseX() 
			}*/ //TODO: impl feldberechnung
			
			
			
			if (input.isKeyDown(Input.KEY_0)) {
				Actor actor = game.getMap().getField(0, 0).getActor();
				game.getPlayer(0).unselect(actor);
			}
			if (input.isKeyDown(Input.KEY_1)) {
				Actor actor = game.getMap().getField(0, 0).getActor();
				game.getPlayer(0).select(actor);
			}
			if (input.isKeyDown(Input.KEY_2)) {
				Actor actor = game.getMap().getField(0, 0).getActor();
				actor.setLeben(actor.getLeben()-1);
			}
			if (input.isKeyDown(Input.KEY_3)) {
				Actor actor = game.getMap().getField(0, 0).getActor();
				actor.setLeben(actor.getLeben()+1);
			}
		
	}


}
