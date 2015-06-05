package edu.northsouth.cse326;

public class PersistentObject {
	private int id;
	private PObjectState state;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void commit() {
		this.state.commit(this);
	}
	
	public void delete() {
		this.state.delete(this);
	}
	
	public void rollback() {
		this.state.rollback(this);
	}
	
	public void save() {
		this.state.save(this);
	}
	
	public void setState(PObjectState pos) {
		this.state = pos;
	}
}
