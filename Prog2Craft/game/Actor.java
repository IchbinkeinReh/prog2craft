package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import enums.ActorType;

public class Actor {

	private ActorType type;
	private Field field;
	private Image img;
	private int leben;

	@SuppressWarnings("unused")
	private int x, y; //TODO: prepare for bewegung!
	private Field target; // unnötig? Angriffsziel?
	
	public Actor(Field field, ActorType type) throws SlickException{
		this.field = field;
		this.type = type;
		this.leben = type.getLeben();
		field.setActor(this);
		img = type.getImage();
	}
	
	public void logic() {
		// if (field != target) <--> Equals => prüft auf X Y Gleichheit
		if (!field.Equals(target))
			move();
	}

	@SuppressWarnings("unused")
	public void move() {
		//TODO warum eigenes move, wenn setField?
		int fX = field.getX();
		int tX = target.getX();
		int fY = field.getY();
		int tY = target.getX();
	
	}

	public void setType(ActorType type) {
		this.type = type;
	}

	public ActorType getType() {
		return type;
	}
	
	public boolean setField(Field field) {
		if (field.getActor() != null) {
			return false;
		}
		else
		{
			this.field = field;
			field.setActor(this);
			return true;
		}
	}
	
	public Field getField() {
		return field;
	}
	
	public void render(float localX, float localY, Graphics g, Camera cam) {
		img.draw(localX 
				,localY
				,img.getWidth() * cam.getZ()
				,img.getHeight() * cam.getZ() );
	}

	public void setLeben(int leben) {
		// nicht negatives / mehr als max Leben
		// sonst Anzeige- und Logikfehler!!
		if(leben>=0 && leben<=type.getLeben()){
			this.leben = leben;
		}
	}

	public int getLeben() {
		return leben;
	}	
}