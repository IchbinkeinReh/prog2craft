package game;

import enums.FieldType;



public class Field {
	
	private int x, y;
	private FieldType type;

	public Field(FieldType type, int x, int y) {
		this.setX(x);
		this.setY(y);
		this.setType(type);
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
	
}
