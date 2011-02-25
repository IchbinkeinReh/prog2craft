package game;

import enums.FieldType;



public class Field {
	
	public int x, y;
	public FieldType type;

	public Field(FieldType type, int x, int y) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	
	
}
