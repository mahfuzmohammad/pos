package edu.northsouth.cse327;

public class CompositeBestForStorePricingStrategy extends
		CompositePricingStrategy {

	@Override
	public int getTotal(Sale sale) {
		int highestTotal = Integer.MIN_VALUE;
		
		for (ISalePricingStrategy iSalePricingStrategy : pricingStrategies) {
			int total = iSalePricingStrategy.getTotal(sale);
			highestTotal = Math.max(highestTotal, total);
		}
		
		return highestTotal;
	}
}
