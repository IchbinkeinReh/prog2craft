import static Prog2Craft.FieldType;

public enum ActorType {
	Infantarie(10, 1, 1, 1, Leer, Felsig,),
	Panzer(20, 2, 2, 2),
	Laufpanzer(30, 3, 3, 3),
	Flugzeug(50, 5, 5, 5);
	
	private final int punkte;
	private final int reichweite;
	private final int angriff;
	private final int verteidigung;
	
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
	
	private ActorType (int punkte, int reichweite, int angriff, int verteidigung){
		this.punkte = punkte;
		this.reichweite = reichweite;
		this.angriff = angriff;
		this.verteidigung = verteidigung;
	}
}
