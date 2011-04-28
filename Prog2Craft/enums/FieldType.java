package enums;

public enum FieldType {
	
	LEER("data/wald"), FELSIG("data/felsig"), GEBIRGE("data/gebirgig"), MEER("data/meer");
	
	private final String str;
	
	private FieldType (String str){
		this.str = str;
	}
	
	public String getPath() {
		return str;
	}
}
