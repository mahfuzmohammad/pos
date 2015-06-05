package edu.northsouth.cse326;

import java.util.ArrayList;

public class CompositePricingStrategy implements ISalePricingStrategy {
	
	protected ArrayList<ISalePricingStrategy> pricingStrategies = new ArrayList<ISalePricingStrategy>();
	
	void add( ISalePricingStrategy isps ) {
		this.pricingStrategies.add(isps);
	}

	@Override
	public int getTotal(Sale sale) {
		int total = 0;
		
		for (ISalePricingStrategy iSalePricingStrategy : pricingStrategies) {
			total += iSalePricingStrategy.getTotal(sale);
		}
		
		return total;
	}

}
