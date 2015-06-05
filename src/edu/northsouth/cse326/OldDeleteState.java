package edu.northsouth.cse326;

public class OldDeleteState extends PObjectState {

	static OldDeleteState odelsinstace = null;
	
	public static PObjectState getInstance() {
		if (odelsinstace == null)
			odelsinstace = new OldDeleteState();
		return odelsinstace;
	}
	
	@Override
	public void commit(PersistentObject obj) {
		PersistenceFacade.getInstance().delete(obj);
		obj.setState(DeletedState.getInstance());
	}
	
	@Override
	public void rollback(PersistentObject obj) {
		PersistenceFacade.getInstance().reload(obj);
		obj.setState(OldCleanState.getInstance());
	}
	
}
