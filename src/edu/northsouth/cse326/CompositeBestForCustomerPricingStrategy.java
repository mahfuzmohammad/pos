package edu.northsouth.cse326;

public class CompositeBestForCustomerPricingStrategy extends
		CompositePricingStrategy {

	@Override
	public int getTotal(Sale sale) {
		int lowestTotal = Integer.MAX_VALUE;
		for (ISalePricingStrategy iSalePricingStrategy : super.pricingStrategies) {
			int total = iSalePricingStrategy.getTotal(sale);
			lowestTotal = Math.min(lowestTotal, total);
		}
		
		return lowestTotal;
	}
}
