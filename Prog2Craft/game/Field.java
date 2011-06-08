package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


//import enums.Field;
// Woher kommt das?
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
	
	public void render(Graphics g, Camera cam) {
		float localX = x * FIELDSIZE * cam.getZ() + cam.getX();
		float localY = y * FIELDSIZE * cam.getZ() + cam.getY();
		img.draw(localX
				,localY
				,img.getWidth() * cam.getZ()
				,img.getHeight() * cam.getZ() );
		if (actor != null) actor.render(localX,localY,g,cam);
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Actor getActor() {
		return actor;
	}

	public void resetActor(Actor actor){
		if(!actor.isAlive()){
			this.actor = null;
		}
	}
	
	public boolean Equals(Field target){
		return (this.x == target.getX() && this.y == target.getY());
		// Feldergleichheit bei gleichen Koordinaten
	}
	
}
