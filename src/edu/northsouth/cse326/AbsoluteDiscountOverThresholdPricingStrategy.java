package edu.northsouth.cse326;

public class AbsoluteDiscountOverThresholdPricingStrategy implements
		ISalePricingStrategy {

	private int discount;
	private int threshold;
	
	public AbsoluteDiscountOverThresholdPricingStrategy(int discount, int threshold) {
		this.discount = discount;
		this.threshold = threshold;
	}
	
	public int getDiscount() {
		return this.discount;
	}
	
	public int getThreshold() {
		return this.threshold;
	}
	
	@Override
	public int getTotal(Sale sale) {
		int pdt = sale.getPreDiscountTotal();
		
		if( pdt < this.threshold ) {
			return pdt;
		}
		
		return pdt - this.discount;
	}

}
