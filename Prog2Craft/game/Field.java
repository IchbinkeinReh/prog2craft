package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import view.Camera;

import enums.FieldType;



public class Field {
	
	public static int FIELDSIZE = 100;
	private int x, y;
	private FieldType type;
	private Image img;
	private Actor actor;

	public Field(FieldType type, int x, int y) throws SlickException {
		this.setX(x);
		this.setY(y);
		this.setType(type);
		this.img = new Image(type.getPath());
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

	public FieldType getType() {
		return type;
	}

	public String toString() {
		return type.name();
	}
	
	public void render() {
		img.draw(x * FIELDSIZE * Camera.getZ() + Camera.getX() 
				,y * FIELDSIZE * Camera.getZ() + Camera.getY()
				,img.getWidth() * Camera.getZ()
				,img.getHeight() * Camera.getZ() );
		if (actor != null) actor.render();
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Actor getActor() {
		return actor;
	}
	
}
