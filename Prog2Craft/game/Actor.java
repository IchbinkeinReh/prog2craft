package game;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import enums.ActorType;

public class Actor {

	private ActorType type;
	private Field field;
	private Image img;
	private int leben;
	private int ruestung;
	private int angriff;
	private boolean alive = true;
	private Player owner;
	private boolean randomMove = false;
	private boolean keepAttacking = false;
	private Actor attackTarget = null;
	
	@SuppressWarnings("unused")
	private int x, y; //TODO: prepare for bewegung!
	private Field target;
	
	public Field getTarget() {
		return target;
	}

	public void setTarget(Field target) {
		//TODO Welche Einheit kann über welche Felder?
		if(this.getType().canWalkOn(target.getType())){
			if(target.getActor()==null){
				this.target = target;
			}
		}
		if(keepAttacking){
			Attack(attackTarget.getField());
		}else if(target.getActor()!=null && target.getActor().getTeam() == this.getTeam()){
			// Attack-Funktion
			Attack(target);
		}
	}
	
	public int getAngriff(){
		return this.angriff;
	}
	
	public void setAngriff(int newAngriff){
		this.angriff = newAngriff;
	}
	
	public boolean setAttackTarget(Actor target){
		if(this.attackTarget==null){
			this.attackTarget = target;
			this.keepAttacking = true;
		}
		return (this.attackTarget == target);
	}
	
	public int getRuestung(){
		return ruestung;
	}
	
	public int setRuestung(int neueRuestung){
		if(this.ruestung>=0){
			this.ruestung = neueRuestung;
		}
		return ruestung;
	}
	
	private boolean Attack(Field target) {
		int distance = this.getField().getDistance(target);
		attackTarget = target.getActor();
		keepAttacking = true;
		
		if(distance <= this.type.getReichweite()){
			int ruestung = attackTarget.getRuestung();
			int leben    = attackTarget.getLeben();
			int mydmg    = this.getAngriff();
			
			int neueRuestung = Math.round(ruestung-mydmg);
			int newDmg   = (ruestung/10-mydmg); // > 0 => Rüstung hat vollen Dmg abgefangen
			int neuesLeben = leben;
			
			if(newDmg<0){
				neuesLeben += Math.round(newDmg);
			}
			
			System.out.println(mydmg + " ; " + leben + " => " + neuesLeben + " ; " + ruestung + " => " + neueRuestung);
			
			attackTarget.setRuestung(neueRuestung);
			attackTarget.setLeben(neuesLeben);
			
			if(attackTarget.getType().getReichweite() <= distance){
				// mache Schritt von Einheit weg
			}
			System.out.println("Attack!!!");
		}else{
			// mache Schritt auf Einheit zu
		}
		
		/* Actor gekillt? */
		if(target.getActor()==null){
			System.out.println("Target killed!");
			keepAttacking = false;
		}
		return true;
	}

	public Actor(Field field, Player owner, ActorType type) throws SlickException{
		this.field = field;
		this.target = field;
		this.type = type;
		this.leben = type.getLeben();
		this.angriff = type.getAngriff();
		this.ruestung = type.getVerteidigung();
		owner.addEinheit(this);
		this.owner = owner;
		field.setActor(this);
		img = type.getImage();
	}

	public void setType(ActorType type) {
		this.type = type;
	}

	public ActorType getType() {
		return type;
	}
	
	public boolean setField(Field field) {
		if (field.getActor() != null) {
			return false;
		}
		else
		{
			this.field = field;
			field.setActor(this);
			return true;
		}
	}
	
	public Field getField() {
		return field;
	}
	
	public void render(float localX, float localY, Graphics g, Camera cam) {
		img.draw(localX 
				,localY
				,img.getWidth() * cam.getZ()
				,img.getHeight() * cam.getZ() );
	}

	public void setLeben(int leben) {
		// nicht negatives / mehr als max Leben
		// sonst Anzeige- und Logikfehler!!
		
		if(leben>0 && leben<=type.getLeben()){
			this.leben = leben;
		}
		if(leben<=0){
			owner.removeKilledActorFromSelection(this);
			alive = false;
			field.resetActor(this);
		}
	}
	
	public boolean isAlive(){
		return alive;
	}

	public int getLeben() {
		return leben;
	}	
	
	public void logic(Prog2CraftGame game) {
		if (!field.Equals(target))
			move(game);
	}

	public void move(Prog2CraftGame game) {
		int xDir = 0;
		if (field.getX() < target.getX()) 		xDir = +1;
		else if (field.getX() > target.getX()) 	xDir = -1;
		int yDir = 0;
		if (field.getY() < target.getY()) 		yDir = +1;
		else if (field.getY() > target.getY()) 	yDir = -1;
		
		Field next = game.getMap().getField(field.getX()+xDir, field.getY()+yDir);
		Field old = field;
		
		//if(!game.getMap().canMove(field)){return;}
		
		if(next.Equals(target) && target.getActor()!=null){ target=old; return; }
		
		if (this.setField(next) && !randomMove) {
			old.setActor(null);
			next.setActor(this);
		}else{
			
			randomMove = (randomMove ? false : true);
			
			next = randomMove(game, next, old);
			if(next.Equals(target) && target.getActor()!=null){ target=old; return; }
			
			if (this.setField(next)) {
				old.setActor(null);
				next.setActor(this);
			}

		}
		
	}
	
	private static Field randomMove(Prog2CraftGame game, Field next, Field old) {
		Random change = new Random(System.currentTimeMillis());
		change.nextInt(2);
		int newX = old.getX() + change.nextInt(3) - 1;
		int newY = old.getY() + change.nextInt(3) - 1;
		return game.getMap().getField(newX, newY);
	}
	
	public Player getTeam() {
		return owner;
	}
}