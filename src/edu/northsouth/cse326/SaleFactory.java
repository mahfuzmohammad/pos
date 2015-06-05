package edu.northsouth.cse326;

import java.util.ArrayList;

public class SaleFactory {
	private ArrayList< ProductSpecification > psList;
	private ProductSpecification ps;
	static SaleFactory instance = null;
	private IVATCalculator vatCalculator = null;
	
	public SaleFactory() {
		psList = new ArrayList< ProductSpecification >();
	}
	
	public void add(ProductSpecification ps) {
		this.psList.add(ps);
		ps.setState(new NewState());
		ps.commit();
	}
	
	public void update(ProductSpecification ps) {
		for(int i = 0; i < psList.size(); i++) {
			if(ps.getId() == psList.get(i).getId()) {
				psList.get(i).setName(ps.getName());
				psList.get(i).setDescription(ps.getDescription());
				psList.get(i).setPrice(ps.getPrice());
				psList.get(i).setState(OldDirtyState.getInstance());
				psList.get(i).commit();
				break;
			}
		}
	}
	
	public void delete(ProductSpecification ps) {
		for(int i = 0; i < psList.size(); i++) {
			if(ps.getId() == psList.get(i).getId()) {
				psList.get(i).delete();
				psList.get(i).commit();
				psList.remove(i);
				break;
			}
		}
	}
	
	public ProductSpecification getProductSpecification( int id ) {
		ProductSpecification ret = null;
		for (ProductSpecification item : psList) {
			if( item.getId() == id ) {
				ret = item;
				break;
			}
		}
		
		return ret;
	}
	
	public static synchronized SaleFactory getInstance() {
		if(instance == null)
			instance = new SaleFactory();
		return instance;
	}
	
	public IVATCalculator getVatCalculator() {
		if(this.vatCalculator == null) {
			String className = BDVATAdapter.class.getName();
			try {
				vatCalculator = (IVATCalculator) Class.forName(className).newInstance();
			} catch (Exception e) {
				//System.out.println("HERE " + e.toString());
				e.printStackTrace();
			}
		}
		
		return this.vatCalculator;
	}
}
