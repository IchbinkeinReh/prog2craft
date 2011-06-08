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
	private boolean alive = true;
	private Player owner;

	@SuppressWarnings("unused")
	private int x, y; //TODO: prepare for bewegung!
	private Field target;
	
	public Field getTarget() {
		return target;
	}

	public void setTarget(Field target) {
		//TODO Welche Einheit kann über welche Felder?
		if(this.getType().canWalkOn(target.getType())){
			this.target = target;
		}
	}

	public Actor(Field field, Player owner, ActorType type) throws SlickException{
		this.field = field;
		this.target = field;
		this.type = type;
		this.leben = type.getLeben();
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
		
		if (this.setField(next)) {
			old.setActor(null);
			next.setActor(this);
		}else{
			next = randomMove(game, next, old);
			if(next.Equals(target) && target.getActor()!=null){ target=old; return; }
			if (this.setField(next)) {
				old.setActor(null);
				next.setActor(this);
			}
			/*
			boolean moved = false;
			while(!moved){
				int newX = 0;
				int newY = 0;

				int diffX = target.getX() - field.getX();
				int diffY = target.getY() - field.getY();
				
				if(Math.abs(xDir)+diffX > Math.abs(yDir)+diffY){
					newX = next.getX() + xDir;
					newY = next.getY() + diffY;
				}else{
					newX = next.getX() + diffX;
					newY = next.getY() + yDir;
				}
				newX = newX % 2;
				newY = newY % 2;
				next = game.getMap().getField(field.getX()+newX, field.getY()+newY);
				
				if(this.setField(next)){
					moved = true;
					old.setActor(null);
					next.setActor(this);
				}
				
			}
			*/
		}
		
	}
	
	private static Field randomMove(Prog2CraftGame game, Field next, Field old) {
		Random change = new Random(System.currentTimeMillis());
		change.nextInt(2);
		int newX = old.getX() + change.nextInt(3) - 1;
		int newY = old.getY() + change.nextInt(3) - 1;
		return game.getMap().getField(newX, newY);
	}
}