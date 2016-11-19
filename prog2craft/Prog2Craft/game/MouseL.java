package game;

import static enums.Modus.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Rectangle;

import enums.Modus;

public class MouseL implements MouseListener {

    private Prog2CraftGame game;
    private Camera cam;
    private Player self;
    private Rectangle selectrahmen = new Rectangle(0, 0, 0, 0);
    private int selectX, selectY;
    private int lastX, lastY;

    private Field getMouseField(int xxx, int yyy) {
	int x = (int) (((-cam.getX() + xxx) / cam.getZ()) / 100);
	int y = (int) (((-cam.getY() + yyy) / cam.getZ()) / 100);
	try {
	    return game.getMap().getField(x, y);
	} catch (IndexOutOfBoundsException e) {
	    return null;
	}
    }

    public MouseL(Prog2CraftGame game, GameContainer gc) {
	this.game = game;
	this.cam = game.getCam();
	this.self = game.getSelf();
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }

    @Override
    public boolean isAcceptingInput() {
	return true;
    }

    @Override
    public void setInput(Input arg0) {

    }

    @Override
    public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {

    }

    @Override
    public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {

    }

    @Override
    public void mouseMoved(int sx, int sy, int tx, int ty) {
	lastX = tx;
	lastY = ty;
    }

    @Override
    public void mousePressed(int mouseKey, int x, int y) {
	Field f = getMouseField(x, y);
	switch (mouseKey) {
	case 0:
	    self.deselectall();
	    self.setMode(SELECTING);
	    selectX = x;
	    selectY = y;
	    break;
	case 1:
	    if (f != null) {
		for (Actor actor : self.getSelected()) {
		    actor.setTarget(f);
		}
	    }
	    break;
	}
    }

    @Override
    public void mouseReleased(int mouseKey, int x2, int y2) {
	switch (mouseKey) {
	case 0:
	    int x1 = selectX;
	    int y1 = selectY;
	    int minX = Math.min(x1, x2);
	    int minY = Math.min(y1, y2);
	    int maxX = Math.max(x1, x2);
	    int maxY = Math.max(y1, y2);

	    for (int x = minX; x <= maxX; ++x) {
		for (int y = minY; y <= maxY; ++y) {
		    Field f2 = getMouseField(x, y);
		    if (f2 != null && f2.getActor() != null) {
			game.getSelf().select(f2.getActor());
		    }
		}
	    }

	    self.setMode(NOTHING);
	    break;
	case 1:
	    // bewegung
	    break;
	}
    }

    @Override
    public void mouseWheelMoved(int direction) {
	if (direction > 0)
	    cam.setZ(cam.getZ() * 1.05f);
	else
	    cam.setZ(cam.getZ() * 0.95f);
    }

    public void render(Graphics g, Camera cam, GameContainer gc) {
	if (self.getMode() == Modus.SELECTING) {
	    selectrahmen.setBounds(selectX, selectY, gc.getInput().getMouseX() - selectX, gc.getInput().getMouseY() - selectY);
	    g.setColor(Color.green);
	    g.draw(selectrahmen);
	}
    }

    public void logic() {
	if (lastX < 0.1f * Prog2CraftGame.conf.getBreite()) {
	    cam.setX(cam.getX() + 5);
	} else if (lastX > 0.9f * Prog2CraftGame.conf.getBreite()) {
	    cam.setX(cam.getX() - 5);
	}
	if (lastY < 0.1f * Prog2CraftGame.conf.getHoehe()) {
	    cam.setY(cam.getY() + 5);
	} else if (lastY > 0.9f * Prog2CraftGame.conf.getHoehe()) {
	    cam.setY(cam.getY() - 5);
	}
    }
}
