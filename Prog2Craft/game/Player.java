package game;

import static game.Field.FIELDSIZE;

import java.util.HashSet;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import view.Camera;

public class Player {
 
	private HashSet<Actor> selected = new HashSet<Actor>();
	private Image health;
	private Image gruen;

	public Player() throws SlickException {
		health = new Image("data/health.png");
		gruen = new Image("data/gruen.png");
	}
	
	public void render() {
		for (Actor act : selected) {
			float localX = act.getField().getX() * FIELDSIZE * Camera.getZ() + Camera.getX();
			float localY = act.getField().getY() * FIELDSIZE * Camera.getZ() + Camera.getY();
			health.draw(localX ,localY
			,health.getWidth() * Camera.getZ()
			,health.getHeight() * Camera.getZ() );
			for (int i = 1; i <= act.getLeben(); i++) {
				gruen.draw(
						 localX + 50 + i
						,localY
						,health.getWidth() * Camera.getZ()
						,health.getHeight() * Camera.getZ() );
			}
		}
	}
	
	public void select(Actor actor) {
		selected.add(actor);
	}
	
	public void unselect(Actor actor) {
		selected.remove(actor);
	}
}
