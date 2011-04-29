package view;

import static view.Prog2Craft2D.*;

public class Camera {

	static private float x = 0.0f;
	static private float y = 0.0f;
	static private float z = 0.5f;
	
	static public void setZ(float zneu) {
		System.out.println(Camera.x);
		System.out.println(Camera.y);
		System.out.println(Camera.z);
		System.out.println();
		Camera.x = Camera.x + (Camera.x + breite) * (Camera.z - zneu);
		Camera.y = Camera.y + (Camera.y + hoehe) * (Camera.z - zneu);
		Camera.z = zneu;
	}
	static public float getZ() {
		return Camera.z;
	}
	static public void setY(float y) {
		Camera.y = y;
	}
	static public float getY() {
		return Camera.y;
	}
	static public void setX(float x) {
		Camera.x = x;
	}
	static public float getX() {
		return Camera.x;
	}
	
}
