package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import view.Camera;

public class Prog2CraftGame {

	private Map map;
	private List<Player> spieler = new ArrayList<Player>();
	private Camera cam;
	private Mode mode;
	
	public Prog2CraftGame(GameContainer gc, Mode mode) throws SlickException {
		
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
	 this.cam = new Camera(gc);
	 this.mode = mode;
	 addPlayer();
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
		spieler.add(new Player(mode));
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

	public void render(Graphics g, Camera cam, GameContainer gc) {
		map.render(g,cam);
		spieler.get(0).render(g,cam,gc);
	}

}
