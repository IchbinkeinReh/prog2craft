package enums;

public enum FieldType {
	
	LEER("data/wald.jpg"), FELSIG("data/felsig.jpg"), GEBIRGE("data/gebirgig.jpg"), MEER("data/meer.jpg");
	
	private final String str;
	
	private FieldType (String str){
		this.str = str;
	}
	
	public String getPath() {
		return str;
	}
}
