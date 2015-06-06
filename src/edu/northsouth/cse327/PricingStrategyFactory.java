package edu.northsouth.cse327;

public class PricingStrategyFactory {

	static PricingStrategyFactory instance = null;
	private ISalePricingStrategy strategy = null;
	
	public static synchronized PricingStrategyFactory getInstance() {
		if(instance == null)
			instance = new PricingStrategyFactory();
		return instance;
	}
	
	public ISalePricingStrategy getSeniorPricingStrategy(float p) {
		this.strategy = new PercentageDiscountPricingStrategy(p);
		return this.strategy;
	}
	
	public ISalePricingStrategy getSalePricingStrategy(int th, int dis) {
		this.strategy = new AbsoluteDiscountOverThresholdPricingStrategy(dis, th);
		return this.strategy;
	}
	
	public ISalePricingStrategy getBestForCustomerPricingStrategy() {
		CompositeBestForCustomerPricingStrategy cust = new CompositeBestForCustomerPricingStrategy();
		cust.add( this.getSeniorPricingStrategy((float) 0.1) );
		cust.add( this.getSalePricingStrategy(1000, 100) );
		this.strategy = cust;
		return this.strategy;
	}
	
	public ISalePricingStrategy getBestForStorePricingStrategy() {
		CompositeBestForStorePricingStrategy store = new CompositeBestForStorePricingStrategy();
		store.add( this.getSeniorPricingStrategy((float) 0.1) );
		store.add( this.getSalePricingStrategy(1000, 100) );
		this.strategy = store;
		return this.strategy;
	}
}
