package enums;

import static enums.FieldType.*;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;

public enum ActorType {
	INFANTARIE("data/inf.png", 10, 5, 5, 5, 5, LEER, FELSIG, GEBIRGE),
	PANZER("data/inf.png", 20, 10, 10, 10, 10, LEER),
	LAUFPANZER("data/inf.png", 30, 15, 15, 15, 15, LEER),
	FLUGZEUG("data/inf.png", 50, 25, 25, 25, 25, LEER, FELSIG, GEBIRGE, MEER);
	
	private final int punkte;
	private final int reichweite;
	private final int angriff;
	private final int verteidigung;
	private final int leben;
	private final String str;
	private final List<FieldType> felder = new ArrayList<FieldType>();
	
	public int getPunkte() {
		return punkte;
	}
	
	public int getReichweite() {
		return reichweite;
	}
	
	public int getAngriff() {
		return angriff;
	}
	
	public int getVerteidigung() {
		return verteidigung;
	}
	
	public boolean canWalkOn(FieldType type)
	{
	return this.felder.contains(type);
	}
	
	private ActorType (String str, int punkte, int reichweite, int angriff, int verteidigung, int leben, FieldType... felder){
		this.punkte = punkte;
		this.reichweite = reichweite;
		this.angriff = angriff;
		this.verteidigung = verteidigung;
		this.str = str;
		this.leben = leben;
		for (int i = 0; i < felder.length; i++)
			this.felder.add(felder[i]);
	}

	public Image getImage() {
			try{
				return new Image(str);
			}catch(Exception e){
				System.exit(1);
				return null;
			} /* TODO return dummy image instead of terminating the application */
	}

	public int getLeben() {
		return leben;
	}
}
