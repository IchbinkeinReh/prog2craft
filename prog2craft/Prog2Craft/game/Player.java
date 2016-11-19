package game;

import java.util.HashSet;

import org.newdawn.slick.Color;

import enums.Modus;

public class Player {

    private HashSet<Actor> einheiten = new HashSet<Actor>();
    private Color color;
    private Modus mode = Modus.NOTHING;

    public Player(Color color) {
	this.color = color;
    }
    
    public Color getColor() {
	return color;
    }
    
    public HashSet<Actor> getSelected() {
	HashSet<Actor> ret = new HashSet<Actor>();
	for (Actor cur : einheiten) {
	    if (cur.selected) {
		ret.add(cur);
	    }
	}
	return ret;
    }

    public void addEinheit(Actor actor) {
	einheiten.add(actor);
    }

    public void removeEinheit(Actor actor) {
	einheiten.remove(actor);
    }

    public void removeKilledActorFromSelection(Actor a) {
	this.unselect(a);
    }

    public void logic() {
	for (Actor einheit : einheiten) {
	    einheit.logic();
	}
    }

    public void select(Actor actor) {
	actor.selected = true;
    }

    public void unselect(Actor actor) {
	actor.selected = false;
    }

    public void deselectall() {
	for (Actor cur : einheiten) {
	    cur.selected = false;
	}
    }

    public Modus getMode() {
	return mode;
    }
    
    public void setMode(Modus mode) {
	this.mode = mode;
    }
}