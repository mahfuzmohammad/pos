package edu.northsouth.cse327;

public class OldCleanState extends PObjectState {

	static OldCleanState ocsinstance = null;
	
	public static PObjectState getInstance() {
		if (ocsinstance == null)
			ocsinstance = new OldCleanState();
		return ocsinstance;
	}

	@Override
	public void delete(PersistentObject obj) {
		obj.setState(OldDeleteState.getInstance());
	}
	
	@Override
	public void save(PersistentObject obj) {
		obj.setState(OldDirtyState.getInstance());
	}
	
}
