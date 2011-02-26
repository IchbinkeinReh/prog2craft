package factory;

import org.lwjgl.opengl.GL11;

import game.Field;

public class RenderFactory {
	
	public static void renderField(Field f) {
		  GL11.glBegin(GL11.GL_QUADS);
	      
	      switch (f.getType())
	      {
	      	case LEER:
	      		GL11.glColor3b((byte)0, (byte)127, (byte)127);
	    	  	break;
	      	case FELSIG:
	      		GL11.glColor3b((byte)0, (byte)127, (byte)0);
	      		break;
	      	case GEBIRGE:
	      		GL11.glColor3b((byte)127, (byte)0, (byte)0);
	      		break;
	      	case MEER:
	      		GL11.glColor3b((byte)0, (byte)0, (byte)127);
	      		break;
	      }
	      int x = f.getX()*100;
	      int y = f.getY()*100;
	      GL11.glVertex2i(x,y);
	      GL11.glVertex2i(x+100,y);
	      GL11.glVertex2i(x+100,y+100);
	      GL11.glVertex2i(x,y+100);
	      GL11.glEnd();	 
	}
	
}
