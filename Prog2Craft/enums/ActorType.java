package enums;

import static enums.FieldType.*;

import java.util.ArrayList;
import java.util.List;

public enum ActorType {
	Infantarie(10, 1, 1, 1, Leer, Felsig, Gebirge),
	Panzer(20, 2, 2, 2, Leer),
	Laufpanzer(30, 3, 3, 3, Leer),
	Flugzeug(50, 5, 5, 5, Leer, Felsig, Gebirge, Meer);
	
	private final int punkte;
	private final int reichweite;
	private final int angriff;
	private final int verteidigung;
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
	
	private ActorType (int punkte, int reichweite, int angriff, int verteidigung, FieldType... felder){
		this.punkte = punkte;
		this.reichweite = reichweite;
		this.angriff = angriff;
		this.verteidigung = verteidigung;
		for (int i = 0; i < felder.length; i++)
			this.felder.add(felder[i]);
	}
}
