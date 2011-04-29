package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import view.Camera;
import enums.ActorType;

public class Actor {

	private ActorType type;
	private Field field;
	private Image img;
	private int leben;
	
	public Actor(Field field, ActorType type) throws SlickException{
	this.field = field;
	this.type = type;
	this.leben = type.getLeben();
	field.setActor(this);
	img = new Image(type.getStr());
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
	
	public void render(float localX, float localY) {
		img.draw(localX 
				,localY
				,img.getWidth() * Camera.getZ()
				,img.getHeight() * Camera.getZ() );
	}

	public void setLeben(int leben) {
		this.leben = leben;
	}

	public int getLeben() {
		return leben;
	}
	
	
	
}
