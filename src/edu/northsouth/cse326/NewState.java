package edu.northsouth.cse326;

public class NewState extends PObjectState {

	static NewState nsinstance = null;
	
	public PObjectState getInstance() {
		if (nsinstance == null)
			nsinstance = new NewState();
		return nsinstance;
	}
	
	@Override
	public void commit(PersistentObject obj) {
		PersistenceFacade.getInstance().insert(obj);
		obj.setState(OldCleanState.getInstance());
	}
	
}
