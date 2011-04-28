package view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardL {
	
		public static void input(GameContainer gc) {
		//System.out.println(arg0 + " " + arg1);  
		Input input = gc.getInput();
			
			if (input.isKeyDown(Input.KEY_ESCAPE)) {
				gc.exit();
			}
			if (input.isKeyDown(Input.KEY_UP)) {
				Camera.setY(Camera.getY() + 2 * Camera.getZ() * 1.0f);
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				Camera.setY(Camera.getY() - 2 * Camera.getZ() * 1.0f);
			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				Camera.setX(Camera.getX() + 2 * Camera.getZ() * 1.0f); 
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				Camera.setX(Camera.getX() - 2 * Camera.getZ() * 1.0f);
			}
			if (input.isKeyDown(Input.KEY_MINUS)) {
				Camera.setZ(Camera.getZ() * 0.99f );
			}
			if (input.isKeyDown(Input.KEY_EQUALS)) {
				Camera.setZ(Camera.getZ() * 1.01f );
			}
			
		
	}


}
