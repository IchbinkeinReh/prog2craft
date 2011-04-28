package game;

import org.newdawn.slick.SlickException;

import factory.FieldFactory;

public class Map {
	private Field[][] spielfeld;
	
	public Map (Field[][] spielfeld)
	{
		this.setSpielfeld(spielfeld);
	}
	
	
	public static Map parse(String... mapDescription) throws SlickException {
		int hoehe = mapDescription.length;
		int laenge = mapDescription[0].length();
		Field[][] map = new Field[laenge][hoehe];
		int x = 0;
		int y = 0;
		for (String act : mapDescription)
		{
			
			if (laenge != act.length())
				throw new IllegalArgumentException("0x000017");
			x = 0;
			for (char token : act.toCharArray())
			{
			map[x][y] = FieldFactory.create(token,x,y);
			x++;
			}
		y++;
		}
	return new Map(map);
	}


	public void setSpielfeld(Field[][] spielfeld) {
		this.spielfeld = spielfeld;
	}


	public Field[][] getSpielfeld() {
		return spielfeld;
	}
	
	public void render() {
	    for (Field[] out : spielfeld)
	  	  {
	  	  for (Field in : out)
	  	  	{
	  		  in.render();
	  	  	}
	  	  }
	}
	
}
