package game;

import org.newdawn.slick.GameContainer;

public class Camera {
    
	private float x = -55.0f;
	private float y = -74.5f;
	private float z = 0.795f;
	private int breite, hoehe;
	
	public Camera(GameContainer gc) {
		this.breite = gc.getWidth();
		this.hoehe = gc.getHeight();
	}

	public void setZ(float zneu) {
		x += ((-x + breite/2)  / z * (z - zneu));
		y += ((-y + hoehe/2) / z * (z - zneu));
		z = zneu;
	}
	
	public float getZ() {
		return z;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getX() {
		return x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getY() {
		return y;
	}
}
