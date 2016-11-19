package factory;

import org.newdawn.slick.SlickException;

import enums.FieldType;
import game.Field;

public class FieldFactory {
	
	public static Field create (char c, int x, int y) throws SlickException {
		switch(c)
		{
		case 'L':
			return new Field(FieldType.LEER, x, y);
		case 'F':
			return new Field(FieldType.FELSIG, x, y);
		case 'G':
			return new Field(FieldType.GEBIRGE, x, y);
		case 'M':
			return new Field(FieldType.MEER, x, y);
		default:
			throw new IllegalArgumentException("0x000018");
		}
	}
}
