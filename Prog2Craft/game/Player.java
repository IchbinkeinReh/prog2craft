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
	private Image rot;

	public Player() throws SlickException {
		health = new Image("data/health.png");
		gruen = new Image("data/gruen.png");
		rot = new Image("data/rot.png");
	}
	
	public void render() {
		for (Actor act : selected) {
			float localX = act.getField().getX() * FIELDSIZE * Camera.getZ() + Camera.getX();
			float localY = act.getField().getY() * FIELDSIZE * Camera.getZ() + Camera.getY();
			int offset = (100-health.getWidth())/2;
			health.draw(localX + (offset-1) * Camera.getZ(),localY + 2 * Camera.getZ()
			,health.getWidth() * Camera.getZ()
			,health.getHeight() * Camera.getZ() );
			int balkenbreite = (health.getWidth()-2);
			float aktleben = act.getLeben();
			int maxleben = act.getType().getLeben();
			int pixel = (int)( ( aktleben / maxleben ) * balkenbreite );
			gruen.draw(localX + offset * Camera.getZ()
			,localY + 3 * Camera.getZ()
			,pixel * Camera.getZ()
			,gruen.getHeight() * Camera.getZ());
			rot.draw(localX + (offset + pixel) * Camera.getZ()
			,localY + 3 * Camera.getZ()
			,(balkenbreite - pixel) * Camera.getZ()
			,rot.getHeight() * Camera.getZ());

		}
	}
	
	public void select(Actor actor) {
		selected.add(actor);
	}
	
	public void unselect(Actor actor) {
		selected.remove(actor);
	}
}
