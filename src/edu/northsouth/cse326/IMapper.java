package edu.northsouth.cse326;

public interface IMapper {

	public Object get(int oid);
	public void put(int oid, Object object);
	public void update(int oid, Object object);
	public void delete(int oid);
	
}
