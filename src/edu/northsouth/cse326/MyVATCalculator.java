package edu.northsouth.cse326;

public class MyVATCalculator implements IVATCalculator {

	@Override
	public int getVATAmount(int saleTotal) {
		return (int) Math.round(saleTotal * 0.05);
	}

}
