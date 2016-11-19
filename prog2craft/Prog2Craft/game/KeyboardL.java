package game;

import java.util.HashSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardL {

    private Camera cam;
    private Input input;
    private Prog2CraftGame game;

    public KeyboardL(Prog2CraftGame game, GameContainer gc) {
	this.cam = game.getCam();
	this.input = gc.getInput();
	this.game = game;
    }

    public void input(GameContainer gc, int delta) {

	// TODO Tastenbelegung dynamisch
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
	    cam.setZ(cam.getZ() * 0.99f);
	}
	if (input.isKeyDown(Input.KEY_EQUALS)) {
	    cam.setZ(cam.getZ() * 1.01f);
	}
	if (input.isKeyDown(Input.KEY_N)) {
	    cam.setZ(0.5f);
	}

	// Änderungen vorbereiten
	int AenderungLeben = 0;
	// Aktionen
	if (input.isKeyDown(Input.KEY_2)) {
	    AenderungLeben = -1;
	}
	if (input.isKeyDown(Input.KEY_3)) {
	    AenderungLeben = 1;
	}

	HashSet<Actor> selectedActors = game.getSelf().getSelected();

	for (Actor a : selectedActors) {
	    a.setLeben(a.getLeben() + AenderungLeben);
	}

    }

}
