package edu.northsouth.cse327;

public class DeletedState extends PObjectState {

	static DeletedState dsinstace = null;
	
	public static PObjectState getInstance() {
		if (dsinstace == null)
			dsinstace = new DeletedState();
		return dsinstace;
	}
	
}
