package game;

import static game.Field.FIELDSIZE;

import java.util.HashSet;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import enums.Modus;



public class Player {
 
	private HashSet<Actor> selected = new HashSet<Actor>();
	private Image health;
	private Image gruen;
	private Image rot;
	private Rectangle selectrahmen;
	private Mode mode;

	public Player(Mode mode) throws SlickException {
		health = new Image("data/health.png");
		gruen = new Image("data/gruen.png");
		rot = new Image("data/rot.png");
		selectrahmen = new Rectangle(0, 0, 0, 0);
		this.mode = mode;
	}
	
	public void render(Graphics g, Camera cam, GameContainer gc) {
		if (mode.getModus() == Modus.SELECTING) {
			selectrahmen.setBounds(mode.getX(), mode.getY(), gc.getInput().getMouseX()-mode.getX(), gc.getInput().getMouseY()-mode.getY());
			g.setColor(Color.green);
			g.draw(selectrahmen);
		}
		else {
			for (Actor act : selected) {
				float localX = act.getField().getX() * FIELDSIZE * cam.getZ() + cam.getX();
				float localY = act.getField().getY() * FIELDSIZE * cam.getZ() + cam.getY();
				int offset = (100-health.getWidth())/2;
				health.draw(localX + (offset-1) * cam.getZ(),localY + 2 * cam.getZ()
				,health.getWidth() * cam.getZ()
				,health.getHeight() * cam.getZ() );
				int balkenbreite = (health.getWidth()-2);
				float aktleben = act.getLeben();
				int maxleben = act.getType().getLeben();
				int pixel = (int)( ( aktleben / maxleben ) * balkenbreite );
				gruen.draw(localX + offset * cam.getZ()
				,localY + 3 * cam.getZ()
				,pixel * cam.getZ()
				,gruen.getHeight() * cam.getZ());
				rot.draw(localX + (offset + pixel) * cam.getZ()
				,localY + 3 * cam.getZ()
				,(balkenbreite - pixel) * cam.getZ()
				,rot.getHeight() * cam.getZ());
			}
		}	
	}
	
	public void select(Actor actor) {
		if (actor != null)
			selected.add(actor);
	}
	
	public void unselect(Actor actor) {
		selected.remove(actor);
	}
	
	public void deselectall() {
		selected.clear();
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Mode getMode() {
		return mode;
	}
}
