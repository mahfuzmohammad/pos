package edu.northsouth.cse326;

import java.util.HashMap;

public class PersistenceFacade {
	
	static PersistenceFacade persistenceinstance = null;
	private HashMap<Class, IMapper> mappers = new HashMap<Class, IMapper>();
	
	public PersistenceFacade() {
		mappers.put(new ProductSpecification().getClass(), (IMapper)new ProductSpecificationRDBMapper());
	}
	
	public static PersistenceFacade getInstance() {
		if(persistenceinstance == null)
			persistenceinstance = new PersistenceFacade();
		return persistenceinstance;
	}
	
	private Object get(int oid, Class persistenceClass) {
		IMapper mapper = mappers.get(persistenceClass);
		return mapper.get(oid);
	}
	
	private void put(int oid, Object object) {
		IMapper mapper = mappers.get(object.getClass());
		mapper.put(oid, object);
	}
	
	private void update(int oid, Object object) {
		IMapper mapper = mappers.get(object.getClass());
		mapper.update(oid, object);
	}
	
	private void delete(int oid, Object object) {
		IMapper mapper = mappers.get(object.getClass());
		mapper.delete(oid);
	}
	
	public void insert(PersistentObject obj) {
		this.getInstance().put(obj.getId(), obj);
	}
	
	public void update(PersistentObject obj) {
		this.getInstance().update(obj.getId(), obj);
	}
	
	public void reload(PersistentObject obj) {
		obj = (PersistentObject) this.getInstance().get(obj.getId(), obj.getClass());
	}
	
	public void delete(PersistentObject obj) {
		this.getInstance().delete(obj.getId(), obj);
	}
}
