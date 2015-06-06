package edu.northsouth.cse327;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.midi.Soundbank;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

public class SaleFrame1 extends SaleJFrame implements PropertyListener {
	
	public SaleFrame1() {
		super();
		this.initialize(psc.getSale());
		
		btnNewSale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				psc.makeNewSale();
				initialize(psc.getSale());
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				while(model.getRowCount() > 0) {
					model.removeRow(0);
				}
				
				model.addRow(new Object[]{"", "", "", "Total", 0});
				tfItemID.setText("");
				tfQuantity.setText("");
				tfVAT.setText("0");
				tfDiscount.setText("0");
				tfGrandTotal.setText("0");
			}
		});
	}
	
	@Override
	public void onPropertyEvent(String source, String name, String value) {
		
		Toolkit.getDefaultToolkit().beep();
		
		if( name.equals("sale.total") ) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[]{"", "", "", "Total", value});
		}
	}
	
	public void initialize( Sale sale ) {
		sale.addPropertyListener(this);
	}

}
