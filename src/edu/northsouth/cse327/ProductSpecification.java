package edu.northsouth.cse327;

public class ProductSpecification extends PersistentObject {

	private int price;
	private String name, description;
	
	public ProductSpecification(int _i, String _name, String _des, int _price) {
		super.setId(_i); this.name = _name; this.description = _des; this.price = _price;
	}
	
	public ProductSpecification() {
	
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setDescription(String des) {
		this.description = des;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setPrice(int p) {
		this.price = p;
	}
	
	public int getPrice() {
		return this.price;
	}
}
