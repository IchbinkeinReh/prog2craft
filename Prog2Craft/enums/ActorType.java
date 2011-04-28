package enums;

import static enums.FieldType.*;

import java.util.ArrayList;
import java.util.List;

public enum ActorType {
	INFANTARIE("data/inf.png", 10, 1, 1, 1, LEER, FELSIG, GEBIRGE),
	PANZER("data/inf.png", 20, 2, 2, 2, LEER),
	LAUFPANZER("data/inf.png", 30, 3, 3, 3, LEER),
	FLUGZEUG("data/inf.png", 50, 5, 5, 5, LEER, FELSIG, GEBIRGE, MEER);
	
	private final int punkte;
	private final int reichweite;
	private final int angriff;
	private final int verteidigung;
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
	
	private ActorType (String str, int punkte, int reichweite, int angriff, int verteidigung, FieldType... felder){
		this.punkte = punkte;
		this.reichweite = reichweite;
		this.angriff = angriff;
		this.verteidigung = verteidigung;
		this.str = str;
		for (int i = 0; i < felder.length; i++)
			this.felder.add(felder[i]);
	}

	public String getStr() {
		return str;
	}
}
