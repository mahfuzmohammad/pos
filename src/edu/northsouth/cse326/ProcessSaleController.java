package edu.northsouth.cse326;

public class ProcessSaleController {
	private Sale sale;
	
	public ProcessSaleController() {
		sale = new Sale();
	}
	
	public void makeNewSale() {
		sale = new Sale();
	}
	
	public void addItem(int id, int quantity) {
		sale.addSaleLineItem(id, quantity);
	}
	
	public Sale getSale() {
		return this.sale;
	}
	
	public ProductSpecification getProductSpecification(int id) {
		SaleFactory factory = new SaleFactory();
		return factory.getProductSpecification(id);
	}
}
