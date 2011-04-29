package view;

public class Camera {

	static private float x = 0.0f;
	static private float y = 0.0f;
	static private float z = 0.6767f;
	
	static public void setZ(float z) {
		Camera.z = z;
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
