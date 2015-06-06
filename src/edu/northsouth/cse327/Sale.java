package edu.northsouth.cse327;

import java.util.ArrayList;

public class Sale {
	private int total = 0;
	private ArrayList<SalesLineItem> sli;
	private ArrayList<PropertyListener> properListeners;
	private ISalePricingStrategy pricingStrategy = null;
	
	public Sale() {
		sli = new ArrayList<SalesLineItem>();
		properListeners = new ArrayList<PropertyListener>();
	}
	
	public void addPropertyListener( PropertyListener lis ) {
		this.properListeners.add(lis);
	}
	
	public void publishPropertyEvent( String name, String value ) {
		for (PropertyListener propertyListener : properListeners) {
			propertyListener.onPropertyEvent("", name, value);
		}
	}
	
	public int getItemNumber() {
		return sli.size();
	}
	
	public SalesLineItem getLine(int i){
		return sli.get(i);
	}
	
	public void addSaleLineItem( int id, int quantity ) {
		sli.add(new SalesLineItem(id, quantity));
	}
	
	public int getPreDiscountTotal() {
		total = 0;
		for (SalesLineItem item : sli) {
			total += item.getSubTotal();
		}
		
		return this.total;
	}
	
	public void setPricingStrategy( String str ) {
		if( str.equals("Eid Discount 100 Tk. Over 1000 Tk.") ) {
			this.pricingStrategy = PricingStrategyFactory.getInstance().getSalePricingStrategy(1000, 100);
		} else if( str.equals("Senior Discount 10%") ) {
			this.pricingStrategy = PricingStrategyFactory.getInstance().getSeniorPricingStrategy((float) 0.1);
		} else if( str.equals("Best for Customer") ) {
			this.pricingStrategy = PricingStrategyFactory.getInstance().getBestForCustomerPricingStrategy();
		} else if( str.equals("Best for Store") ) {
			this.pricingStrategy = PricingStrategyFactory.getInstance().getBestForStorePricingStrategy();
		}
	}
	
	public int getTotal() {		
		return this.pricingStrategy.getTotal(this);
	}
	
	public int getDiscount() {
		int t = this.getTotal();
		return this.getPreDiscountTotal() - t;
	}
	
	public int getGrandTotal() {
		return this.getTotal() + this.getVATAmount();
	}
	
	public int getVATAmount() {
		IVATCalculator ivac = SaleFactory.getInstance().getVatCalculator();
		return ivac.getVATAmount(this.getPreDiscountTotal());
	}
}
