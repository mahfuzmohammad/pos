package edu.northsouth.cse327;

public class BDVATAdapter implements IVATCalculator {

	@Override
	public int getVATAmount(int saleTotal) {
		BDVATCalculator bdvat = new BDVATCalculator();
		return Math.round(bdvat.calculateVATAmount(saleTotal));
	}

}
