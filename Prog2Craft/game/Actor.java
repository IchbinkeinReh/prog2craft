package game;

import static game.Field.FIELDSIZE;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import view.Camera;
import enums.ActorType;

public class Actor {

	private ActorType type;
	private Field field;
	private Image img;
	
	public Actor(Field field, ActorType type) throws SlickException{
	this.field = field;
	this.type = type;
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
	
	public void render() {
		img.draw(field.getX() * FIELDSIZE * Camera.getZ() + Camera.getX() 
				,field.getY() * FIELDSIZE * Camera.getZ() + Camera.getY()
				,img.getWidth() * Camera.getZ()
				,img.getHeight() * Camera.getZ() );
	}
	
	
	
}
