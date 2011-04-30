package game;

import enums.Modus;

public class Mode {

	private Modus modus;

	private int x,y;

	public Mode(Modus modus) {
		this.modus = modus;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public void setModus(Modus modus) {
		this.modus = modus;
	}

	public Modus getModus() {
		return modus;
	}
}
