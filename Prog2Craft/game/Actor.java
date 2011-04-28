package game;

import enums.ActorType;

public class Actor {

	private ActorType type;
	@SuppressWarnings("unused")
	private Field field;
	
	public Actor(Field field){
	this.field = field;
	field.setActor(this);
	}

	public void setType(ActorType type) {
		this.type = type;
	}

	public ActorType getType() {
		return type;
	}
	
	public boolean setField(Field field) {
		if (field.getActor() != null) {
			return false;
		}
		else
		{
			this.field = field;
			field.setActor(this);
			return true;
		}
	}
	
	
	
}
