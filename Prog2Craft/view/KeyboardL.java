package view;

import game.Actor;
import game.Prog2CraftGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardL {
	
		private Camera cam;
		private Input input;
		
		public KeyboardL(Prog2CraftGame game, GameContainer gc) {
		this.cam = game.getCam();
		this.input = gc.getInput();
		}

		public void input(GameContainer gc, int delta, Prog2CraftGame game) { 

			if (input.isKeyDown(Input.KEY_ESCAPE)) {
				gc.exit();
			}
			if (input.isKeyDown(Input.KEY_UP)) {
				cam.setY(cam.getY() + 10);
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				cam.setY(cam.getY() - 10);
			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				cam.setX(cam.getX() + 10); 
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				cam.setX(cam.getX() - 10);
			}
			if (input.isKeyDown(Input.KEY_MINUS)) {
				cam.setZ(cam.getZ() * 0.99f );
			}
			if (input.isKeyDown(Input.KEY_EQUALS)) {
				cam.setZ(cam.getZ() * 1.01f );
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
