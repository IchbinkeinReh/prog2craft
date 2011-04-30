package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import view.Camera;

public class Prog2CraftGame {

	private Map map;
	private List<Player> spieler = new ArrayList<Player>();
	private Camera cam;
	private Config conf;
	
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
	 this.cam = new Camera();
	 addPlayer();
	 this.setConfig(new Config());
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
	
	public void addPlayer() throws SlickException {
		spieler.add(new Player());
	}
	
	public Player getPlayer(int i) {
		return spieler.get(i);
	}

	public void setCam(Camera cam) {
		this.cam = cam;
	}

	public Camera getCam() {
		return cam;
	}

	public void setConfig(Config conf) {
		this.conf = conf;
	}

	public Config getConfig() {
		return conf;
	}

	public void render(Graphics g, Camera cam) {
		map.render(g,cam);
		spieler.get(0).render(g,cam);
	}

}
