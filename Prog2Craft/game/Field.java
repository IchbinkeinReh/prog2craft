package game;

import static game.Field.FIELDSIZE;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import enums.FieldType;



public class Field {
	
	public static int FIELDSIZE = 100;
	private int x, y;
	private FieldType type;
	private Image img;

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
	  switch(type)
			  { //TODO: change x,y,z
			  case LEER:
				  leer.draw(x*FIELDSIZE*z+x, y*FIELDSIZE*z+y, leer.getWidth()*z, leer.getHeight()*z); break;
			  case FELSIG:
				  felsig.draw(x*FIELDSIZE*z+x, y*FIELDSIZE*z+y, felsig.getWidth()*z, felsig.getHeight()*z); break;
			  case GEBIRGE:
				  gebirge.draw(x*FIELDSIZE*z+x, y*FIELDSIZE*z+y, gebirge.getWidth()*z, gebirge.getHeight()*z); break;
			  case MEER:
				  meer.draw(x*FIELDSIZE*z+x, y*FIELDSIZE*z+y, meer.getWidth()*z, meer.getHeight()*z); break;
			  }
	}
	
}
