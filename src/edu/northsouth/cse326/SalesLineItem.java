package edu.northsouth.cse326;

public class SalesLineItem {
	private ProductSpecification ps;
	private int quantity;
	
	public SalesLineItem( int _id, int _quantity ) {
		ps = new ProductSpecification();
		this.quantity = _quantity;
//		SaleFactory factory = new SaleFactory();
//		ps = factory.getProductSpecification(_id);
		ps = SaleFactory.getInstance().getProductSpecification(_id);
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public ProductSpecification getProductSpecifiaction() {
		return this.ps;
	}
	
	public int getSubTotal() {
		return this.quantity * ps.getPrice();
	}
}
