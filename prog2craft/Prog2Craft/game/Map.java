package game;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import factory.FieldFactory;

public class Map implements TileBasedMap {
    private Field[][] spielfeld;

    public Map(Field[][] spielfeld) {
	this.spielfeld = spielfeld;
    }

    public static Map parse(String... mapDescription) throws SlickException {
	int hoehe = mapDescription.length;
	int laenge = mapDescription[0].length();
	Field[][] map = new Field[laenge][hoehe];
	int x = 0;
	int y = 0;
	for (String act : mapDescription) {

	    if (laenge != act.length())
		throw new IllegalArgumentException("0x000017");
	    x = 0;
	    for (char token : act.toCharArray()) {
		map[x][y] = FieldFactory.create(token, x, y);
		x++;
	    }
	    y++;
	}
	return new Map(map);
    }

    public Field getField(int x, int y) {
	if ((x < 0) || (x > spielfeld.length - 1) || (y < 0) || (y > spielfeld[0].length - 1)) {
	    throw new IndexOutOfBoundsException();
	}
	return this.spielfeld[x][y];
    }

    public void render(Graphics g, Camera cam) {
	for (Field[] out : spielfeld) {
	    for (Field in : out) {
		in.render(g, cam);
	    }
	}
    }

    public int getWidthInTiles() {
	return spielfeld.length;
    }

    public int getHeightInTiles() {
	return spielfeld[0].length;
    }

    @Override
    public boolean blocked(PathFindingContext pfc, int tx, int ty) {
	Actor mover = (Actor) pfc.getMover();
	Field target = getField(tx, ty);
	return target.getActor() != null || mover.canWalkOn(target) == false;
    }

    public Field getNearestFreeField(final Field f) {
	PriorityQueue<Field> pq = new PriorityQueue<Field>(new Comparator<Field>() {
	    @Override
	    public int compare(Field o1, Field o2) {
		double d1 = o1.getDistance(f);
		double d2 = o2.getDistance(f);
		return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
	    }
	});
	int x = f.getX();
	int y = f.getY();
	int dist = 0;
	while (pq.isEmpty() && (dist < getHeightInTiles() || dist < getWidthInTiles())) {
	    for (int cx = x - dist; cx <= x + dist; ++cx) {
		for (int cy = y - dist; cy <= y + dist; ++cy) {
		    try {
			Field tmp = getField(cx, cy);
			if (tmp.getActor() == null) {
			    pq.add(tmp);
			}
		    } catch (Throwable t) {
		    }
		}
	    }
	    ++dist;
	}
	if (pq.isEmpty()) {
	    return null;
	} else {
	    return pq.poll();
	}
	
    }

    @Override
    public float getCost(PathFindingContext pfc, int tx, int ty) {
	return 1.0f;
    }

    @Override
    public void pathFinderVisited(int tx, int ty) {

    }
}
