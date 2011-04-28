package game;

import org.newdawn.slick.SlickException;

public class Prog2CraftGame {

	private Map map;
	
	public Prog2CraftGame() throws SlickException {
		
	 setMap(Map.parse("MMMMMMMMMMMMMMMMMMMMMM",
				 	  "MMMMMMMMMMMMMMMMMMMMMM",
					  "MMGGGGGGGGGGGGGGGGGGMM",
					  "MMGGGGGFFFFFFFFGGGGGMM",
					  "MMGGFFFFFFLLFFFFFFGGMM",
					  "MMGGFFLLLLLLLLLLFFGGMM",
					  "MMGFFLLLLLLLLLLLLFFGMM",
					  "MMGFFLLLLLLLLLLLLFFGMM",
					  "MMGGFFLLLLLLLLLLFFGGMM",
					  "MMGGFFFFFFLLFFFFFFGGMM",
					  "MMGGGGGFFFFFFFFGGGGGMM",
					  "MMGGGGGGGGGGGGGGGGGGMM",
				 	  "MMMMMMMMMMMMMMMMMMMMMM",
				 	  "MMMMMMMMMMMMMMMMMMMMMM" )); 
		//setMap (Map.parse("M"));

	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Map getMap() {
		return map;
	}
	
	public Field[][] getSpielfeld() {
		return map.getSpielfeld();
	}
	
	public int getBreite() {
		return map.getSpielfeld()[0].length;
	}
	
	public int getHoehe() {
		return map.getSpielfeld().length;
	}

	public void render() {
		map.render();
	}
	
}
