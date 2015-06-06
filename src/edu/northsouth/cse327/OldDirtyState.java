package edu.northsouth.cse327;

public class OldDirtyState extends PObjectState {
	
	static OldDirtyState odsinstace = null;
	
	public static PObjectState getInstance() {
		if (odsinstace == null)
			odsinstace = new OldDirtyState();
		return odsinstace;
	}

	@Override
	public void commit(PersistentObject obj) {
		PersistenceFacade.getInstance().update(obj);
		obj.setState(OldCleanState.getInstance());
	}
	
	@Override
	public void delete(PersistentObject obj) {
		obj.setState(OldDeleteState.getInstance());
	}
	
	@Override
	public void rollback(PersistentObject obj) {
		PersistenceFacade.getInstance().reload(obj);
		obj.setState(OldCleanState.getInstance());
	}
	
}
