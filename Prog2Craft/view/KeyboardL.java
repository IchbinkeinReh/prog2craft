package view;

import game.Actor;
import game.Field;
import game.Prog2CraftGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardL {
	
		private static Field getMouseField(Input input, Prog2CraftGame game) {
			int x = (int)(  ((-Camera.getX() + input.getMouseX()) / Camera.getZ()) / 100 );
			int y = (int)(  ((-Camera.getY() + input.getMouseY()) / Camera.getZ()) / 100 );
			return game.getMap().getField(x, y);
		}
	
		public static void input(GameContainer gc, int delta, Prog2CraftGame game) { 
		Input input = gc.getInput();

			if (input.isKeyDown(Input.KEY_ESCAPE)) {
				gc.exit();
			}
			if (input.isKeyDown(Input.KEY_UP)) {
				Camera.setY(Camera.getY() + 10);
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				Camera.setY(Camera.getY() - 10);
			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				Camera.setX(Camera.getX() + 10); 
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				Camera.setX(Camera.getX() - 10);
			}
			if (input.isKeyDown(Input.KEY_MINUS)) {
				Camera.setZ(Camera.getZ() * 0.99f );
			}
			if (input.isKeyDown(Input.KEY_EQUALS)) {
				Camera.setZ(Camera.getZ() * 1.01f );
			}
			
			if (input.isMousePressed(0)) {
				game.getPlayer(0).deselectall();
				if (getMouseField(input, game).getActor() != null)
				game.getPlayer(0).select(getMouseField(input, game).getActor());
			}
			
			
			
			if (input.isKeyPressed(Input.KEY_2)) {
				Actor actor = game.getMap().getField(0, 0).getActor();
				actor.setLeben(actor.getLeben()-1);
			}
			if (input.isKeyPressed(Input.KEY_3)) {
				Actor actor = game.getMap().getField(0, 0).getActor();
				actor.setLeben(actor.getLeben()+1);
			}
		
	}


}
