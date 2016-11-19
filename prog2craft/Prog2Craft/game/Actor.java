package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

import enums.ActorType;

public class Actor implements Mover {
    private static Image health;
    private static Image gruen;
    private static Image rot;
    static {
	try {
	    health = new Image("data/health.png");
	    gruen = new Image("data/gruen.png");
	    rot = new Image("data/rot.png");
	} catch (SlickException e) {
	    e.printStackTrace();
	}
    }
    private AStarPathFinder finder;
    boolean selected = false;
    private Map map;
    private ActorType type;
    private Field field;
    private Image img;
    private int leben;
    private int ruestung;
    private int angriff;
    private boolean alive = true;
    private Player owner;
    private Actor attackTarget = null;

    private Field target;

    public Field getTarget() {
	return target;
    }

    public void setTarget(Field target) {
	this.target = target;
	
	if (target.getActor() != null && target.getActor().owner != this.owner) {
	    this.attackTarget = target.getActor();
	}
    }

    public int getAngriff() {
	return this.angriff;
    }

    public void setAngriff(int newAngriff) {
	this.angriff = newAngriff;
    }

    public void setAttackTarget(Actor target) {
	this.attackTarget = target;
    }

    public int getRuestung() {
	return ruestung;
    }

    public int setRuestung(int neueRuestung) {
	if (this.ruestung >= 0) {
	    this.ruestung = neueRuestung;
	}
	return ruestung;
    }

    private boolean tryAttack() {
	int distance = Math.round(this.getField().getDistance(attackTarget.field));

	if (distance <= this.type.getReichweite()) {
	    int dmg = Math.max(attackTarget.getRuestung() - this.getAngriff(), 0);
	    attackTarget.setLeben(attackTarget.getLeben() - dmg);
	} else {
	    return false;
	}

	if (attackTarget.alive == false) {
	    attackTarget = null;
	}
	return true;
    }

    public Actor(Field field, Player owner, ActorType type, Map map) throws SlickException {
	this.map = map;
	this.field = field;
	this.target = field;
	this.type = type;
	this.leben = type.getLeben();
	this.angriff = type.getAngriff();
	this.ruestung = type.getVerteidigung();
	owner.addEinheit(this);
	this.owner = owner;
	field.setActor(this);
	this.img = type.getImage();
	this.finder = new AStarPathFinder(map, 600, true);
    }

    public void setType(ActorType type) {
	this.type = type;
    }

    public boolean canWalkOn(Field f) {
	return this.type.canWalkOn(f.getType());
    }

    public boolean setField(Field field) {
	if (field.getActor() != null || canWalkOn(field) == false) {
	    return false;
	} else {
	    this.field.setActor(null);
	    this.field = field;
	    this.field.setActor(this);
	    return true;
	}
    }

    public Field getField() {
	return field;
    }

    public void render(float localX, float localY, Graphics g, Camera cam) {
	g.setColor(owner.getColor());
	g.fillRect(localX, localY, 15 * cam.getZ(), 15 * cam.getZ());
	img.draw(localX, localY, img.getWidth() * cam.getZ(), img.getHeight() * cam.getZ());
	if (selected) {
	    int offset = (100 - health.getWidth()) / 2;
	    health.draw(localX + (offset - 1) * cam.getZ(), localY + 2 * cam.getZ(), health.getWidth() * cam.getZ(), health.getHeight() * cam.getZ());
	    int balkenbreite = (health.getWidth() - 2);
	    int pixel = Math.round(((float)leben / type.getLeben()) * balkenbreite);
	    gruen.draw(localX + offset * cam.getZ(), localY + 3 * cam.getZ(), pixel * cam.getZ(), gruen.getHeight() * cam.getZ());
	    rot.draw(localX + (offset + pixel) * cam.getZ(), localY + 3 * cam.getZ(), (balkenbreite - pixel) * cam.getZ(), rot.getHeight() * cam.getZ());
	}
    }

    public void setLeben(int leben) {
	if (leben > 0 && leben <= type.getLeben()) {
	    this.leben = leben;
	}
	if (leben <= 0) {
	    owner.removeKilledActorFromSelection(this);
	    alive = false;
	    field.resetActor(this);
	}
    }

    public boolean isAlive() {
	return alive;
    }

    public int getLeben() {
	return leben;
    }

    public void logic() {
	if (!field.Equals(target)) {
	    tryMove();
	}
	if (attackTarget != null) {
	    tryAttack();
	}
    }

    public void tryMove() {
	Field target = map.getNearestFreeField(this.target);
	if (target == null) {
	    return;
	}
	Path path = this.finder.findPath(this, field.getX(), field.getY(), target.getX(), target.getY());
	if (path != null && path.getLength() > 1) {
	    Step s = path.getStep(1);
	    this.setField(map.getField(s.getX(), s.getY()));
	}
	
    }

    public Player getTeam() {
	return owner;
    }
}