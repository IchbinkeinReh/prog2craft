package view;

import static view.Prog2Craft2D.*;

public class Camera {

	private float x = 0.0f;
	private float y = 0.0f;
	private float z = 0.5f;
	
	public void setZ(float zneu) {
		x += ((-x + breite/2)  / z * (z - zneu));
		y -= ((-y + hoehe/2) / z * (z - zneu));
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
