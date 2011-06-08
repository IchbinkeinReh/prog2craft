package enums;

import java.util.Random;

public enum Direction {
	
	LEFT, DOWNLEFT, DOWN, DOWNRIGHT, RIGHT, UPRIGHT, UP, UPLEFT;

	private Random rnd = new Random(System.currentTimeMillis());
	
	public Direction getRandomDirection() {
		return Direction.values()[rnd.nextInt(Direction.values().length)];
	}
	
	public Direction getSimilar() {
		switch(this){
			case LEFT      : return rnd.nextBoolean() ? DOWNLEFT : UPLEFT;
			case DOWNLEFT  : return rnd.nextBoolean() ? LEFT : DOWN;
			case DOWN      : return rnd.nextBoolean() ? DOWNLEFT : DOWNRIGHT;
			case DOWNRIGHT : return rnd.nextBoolean() ? DOWN : RIGHT;
			case RIGHT     : return rnd.nextBoolean() ? DOWNRIGHT : UPRIGHT;
			case UPRIGHT   : return rnd.nextBoolean() ? RIGHT : UP;
			case UP        : return rnd.nextBoolean() ? UPRIGHT : UPLEFT;
			case UPLEFT    : return rnd.nextBoolean() ? UP : LEFT;
			default        : return null;
		}
	}

}