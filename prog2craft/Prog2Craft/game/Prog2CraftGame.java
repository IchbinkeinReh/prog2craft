package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import enums.ActorType;

public class Prog2CraftGame extends BasicGame {

    private KeyboardL keyL;
    private MouseL mouseL;

    private int moveTime = 333;
    private int actTime;

    private Map map;
    private List<Player> spieler = new ArrayList<Player>();
    private Camera cam;
    private Player self;
    public static Config conf = new Config();

    public Prog2CraftGame() throws SlickException {
	super("Prog2Craft");
    }

    public Player getPlayer(int i) {
	return spieler.get(i);
    }

    public Camera getCam() {
	return cam;
    }

    public Player getSelf() {
	return self;
    }

    public Map getMap() {
	return map;
    }

    public static void main(String[] args) throws SlickException {
	AppGameContainer app = new AppGameContainer(new Prog2CraftGame());
	int breite = conf.getBreite();
	int hoehe = conf.getHoehe();
	app.setDisplayMode(breite, hoehe, conf.isFullscreen());
	app.start();
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
	map.render(g, cam);
	mouseL.render(g, cam, gc);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
	map = Map.parse("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM", "MMGGGGGGGGGGGGGGGGGGGGGGGGGGMM", "MMGGGGGFFFFFFFFFFFFFFFFGGGGGMM",
		"MMGGFFFFFFLLLLLLLLLLFFFFFFGGMM", "MMGGFFLLLLLLLLLLLLLLLLLLFFGGMM", "MMGGFFLLLLLLLLLLLLLLLLLLFFGGMM", "MMGGFFLLLLLLLLLLLLLLLLLLFFGGMM",
		"MMGFFLLLLLLLLLLLLLLLLLLLLFFGMM", "MMGFFLLLLLLLLLLLLLLLLLLLLFFGMM", "MMGFFLLLLLLLLLLLLLLLLLLLLFFGMM", "MMGGFFLLLLLLLLLLLLLLLLLLFFGGMM",
		"MMGGFFLLLLLLLLLLLLLLLLLLFFGGMM", "MMGGFFLLLLLLLLLLLLLLLLLLFFGGMM", "MMGGFFFFFFLLLLLLLLLLFFFFFFGGMM", "MMGGGGGFFFFFFFFFFFFFFFFGGGGGMM",
		"MMGGGGGGGGGGGGGGGGGGGGGGGGGGMM", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
	self = new Player(Color.red);
	spieler.add(getSelf());
	Field f1 = getMap().getField(5, 8);
	new Actor(f1, getSelf(), ActorType.PANZER, getMap());
	Field f2 = getMap().getField(5, 9);
	new Actor(f2, getSelf(), ActorType.PANZER, getMap());
	Field f3 = getMap().getField(5, 10);
	new Actor(f3, getSelf(), ActorType.PANZER, getMap());

	Field f4 = getMap().getField(6, 8);
	new Actor(f4, getSelf(), ActorType.LAUFPANZER, getMap());
	Field f5 = getMap().getField(6, 9);
	new Actor(f5, getSelf(), ActorType.CHUCKNORRIS, getMap());
	Field f6 = getMap().getField(6, 10);
	new Actor(f6, getSelf(), ActorType.LAUFPANZER, getMap());

	Field f7 = getMap().getField(7, 8);
	new Actor(f7, getSelf(), ActorType.ZIVILIST, getMap());
	Field f8 = getMap().getField(7, 9);
	new Actor(f8, getSelf(), ActorType.INFANTARIE, getMap());
	Field f9 = getMap().getField(7, 10);
	new Actor(f9, getSelf(), ActorType.FLUGZEUG, getMap());

	this.cam = new Camera(gc);
	mouseL = new MouseL(this, gc);
	gc.getInput().addMouseListener(mouseL);
	keyL = new KeyboardL(this, gc);
	gc.setTargetFrameRate(120);
	gc.setMultiSample(4);
	gc.setVSync(true);
	gc.getGraphics().setAntiAlias(true);

    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
	actTime += delta;
	keyL.input(gc, delta);
	if (actTime > moveTime) {
	    for (Player p : spieler) {
		p.logic();
	    }
	    actTime = 0;
	}
	mouseL.logic();
    }
}
