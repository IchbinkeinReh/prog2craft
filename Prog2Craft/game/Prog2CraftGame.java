package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

public class Prog2CraftGame {

	private Map map;
	private List<Player> spieler = new ArrayList<Player>();
	
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
		spieler.get(0).render();
	}
	
	public void addPlayer() throws SlickException {
		spieler.add(new Player());
	}
	
	public Player getPlayer(int i) {
		return spieler.get(i);
	}
}
