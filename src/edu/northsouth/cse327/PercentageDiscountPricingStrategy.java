package edu.northsouth.cse327;

public class PercentageDiscountPricingStrategy implements ISalePricingStrategy {

	private float percentage = (float) 0;
	
	public PercentageDiscountPricingStrategy(float p) {
		this.percentage = p;
	}
	
	public void setPercentage( float p ) {
		this.percentage = p;
	}
	
	public float getPercentage() {
		return this.percentage;
	}
	
	@Override
	public int getTotal(Sale sale) {
		return (int) Math.round(sale.getPreDiscountTotal() * (1.0 - this.percentage));
	}

}
