package game;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardL {
	
		private Camera cam;
		private Input input;
		
		public KeyboardL(Prog2CraftGame game, GameContainer gc, Mode mode) {
		this.cam = game.getCam();
		this.input = gc.getInput();
		}

		public void input(GameContainer gc, int delta, Prog2CraftGame game) { 

			if (input.isKeyDown(Input.KEY_ESCAPE)) {
				gc.exit();
			}
			if (input.isKeyDown(Input.KEY_UP)) {
				cam.setY(cam.getY() + 10);
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				cam.setY(cam.getY() - 10);
			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				cam.setX(cam.getX() + 10); 
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				cam.setX(cam.getX() - 10);
			}
			if (input.isKeyDown(Input.KEY_MINUS)) {
				cam.setZ(cam.getZ() * 0.99f );
			}
			if (input.isKeyDown(Input.KEY_EQUALS)) {
				cam.setZ(cam.getZ() * 1.01f );
			}
			// Normalisiere Ansicht
			if (input.isKeyDown(Input.KEY_N)) {
				cam.setZ(0.5f);
			}
			
			boolean res2 = false;
			
			// Aktionen
			if (input.isKeyPressed(Input.KEY_2)) {
				res2 = this.KeyEvent(game, Input.KEY_2);
				// debug
				System.out.println("Key2 pressed: "+res2);
			}
			if (input.isKeyPressed(Input.KEY_3)) {
				res2 = this.KeyEvent(game, Input.KEY_3);
				// debug
				System.out.println("Key2 pressed: "+res2);
			}
			
			/*
			if (input.isKeyPressed(Input.KEY_2)) {
				Actor actor = game.getMap().getField(0, 0).getActor();
				if(actor == null){
					// debug
					System.out.println("Actor is null..");
					return;
				}
				actor.setLeben(actor.getLeben()-1);
			}
			if (input.isKeyPressed(Input.KEY_3)) {
				Actor actor = game.getMap().getField(0, 0).getActor();
				if(actor == null){
					// debug
					System.out.println("Actor is null..");
					return;
				}
				actor.setLeben(actor.getLeben()+1);
			}
			*/
		
	}
		
	public boolean KeyEvent(Prog2CraftGame game, int key){

		/*
		 * Laufe ueber Spielfeld
		 * Teste fuer jedes Feld:
		 *   - steht Spieler auf dem Feld
		 *   - ist Spieler markiert
		 *   ja: springe raus
		 *   nein: gehe weiter
		 * Schaue ob ein markierter Spieler da ist
		 *  nein: --> return false
		 *  
		 **/
		
		// Spielfeld
		int x = game.getHoehe();
		int y = game.getBreite();
		
		// #Spieler
		int c = 0;
		
		// Änderungen vorbereiten
		int AenderungLeben = 0;
		if(key == Input.KEY_2){
			AenderungLeben = -1;
		}
		if(key == Input.KEY_3){
			AenderungLeben =  1;
		}
		
		for(int i=0; i<x; i++){
			for(int j=0; j<y; j++){
				Actor a = game.getMap().getField(i, j).getActor();
				//TODO Herausfinden, ob a markierter Actor ist!
				if(a!=null){
					a.setLeben(a.getLeben()+AenderungLeben);
					c++;
				}
			}
		}

		// debug
		if(c==0){
			System.out.println("Key pressed: no actor");
		}
		
		return !(c==0);
	}


}
