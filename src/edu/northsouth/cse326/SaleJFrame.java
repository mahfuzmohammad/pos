package edu.northsouth.cse326;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class SaleJFrame extends JFrame {

	protected JPanel contentPane;
	protected JTextField tfItemID;
	protected JTextField tfQuantity;
	protected JTable table;
	protected JScrollPane scrollPane;
	protected ProcessSaleController psc;
	protected JTextField tfVAT;
	protected JTextField tfGrandTotal;
	protected JTextField tfDiscount;
	protected JButton btnAddItem;
	protected JButton btnNewSale;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleFrame1 frame = new SaleFrame1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public SaleJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 502);
		
		final AddNewPS dialog = new AddNewPS();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnProductSpecification = new JMenu("Product Specification");
		menuBar.add(mnProductSpecification);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dialog.setVisible(true);
			}
		});
		mnProductSpecification.add(mntmNew);
		
		JMenuItem mntmUpdate = new JMenuItem("Update");
		mntmUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dialog.setVisible(true);
			}
		});
		mnProductSpecification.add(mntmUpdate);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dialog.setVisible(true);
			}
		});
		mnProductSpecification.add(mntmDelete);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		psc = new ProcessSaleController();
		
		btnNewSale = new JButton("New Sale");
		btnNewSale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				psc.makeNewSale();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				while(model.getRowCount() > 0) {
					model.removeRow(0);
				}
				
				model.addRow(new Object[]{"", "", "", "Total", 0});
				tfItemID.setText("");
				tfQuantity.setText("");
				tfVAT.setText("0");
				tfGrandTotal.setText("0");
			}
		});
		btnNewSale.setBounds(97, 11, 89, 23);
		
		contentPane.setLayout(null);
		contentPane.add(btnNewSale);
		
		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setBounds(20, 53, 60, 14);
		contentPane.add(lblItemId);
		
		tfItemID = new JTextField();
		tfItemID.setBounds(97, 50, 302, 20);
		contentPane.add(tfItemID);
		tfItemID.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(20, 88, 60, 14);
		contentPane.add(lblQuantity);
		
		tfQuantity = new JTextField();
		tfQuantity.setBounds(97, 85, 302, 20);
		contentPane.add(tfQuantity);
		tfQuantity.setColumns(10);
		
		btnAddItem = new JButton("Add Item");
		btnAddItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				psc.addItem(Integer.valueOf(tfItemID.getText()), Integer.valueOf(tfQuantity.getText()));
				Sale sale = psc.getSale();

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				while(model.getRowCount() > 0) {
					model.removeRow(0);
				}
				
				for(int i = 0; i < sale.getItemNumber(); i++) {
					SalesLineItem line = sale.getLine(i);
					model.addRow(new Object[]{i+1, line.getProductSpecifiaction().getName(), line.getProductSpecifiaction().getPrice(), line.getQuantity(), line.getSubTotal()});
				}
				
				sale.publishPropertyEvent("sale.total", String.valueOf(sale.getPreDiscountTotal()));
				
				tfVAT.setText(String.valueOf(sale.getVATAmount()));
				tfGrandTotal.setText(String.valueOf( sale.getPreDiscountTotal() + sale.getVATAmount() ));
			}
		});
		
		btnAddItem.setBounds(97, 116, 89, 23);
		contentPane.add(btnAddItem);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 150, 583, 197);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", "", "Total", 0},
			},
			new String[] {
				"SL#", "Item Name", "Unit Price", "Quantity", "Sub Total"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		tfVAT = new JTextField();
		tfVAT.setText("0");
		tfVAT.setEditable(false);
		tfVAT.setBounds(594, 358, 86, 20);
		contentPane.add(tfVAT);
		tfVAT.setColumns(10);
		
		tfGrandTotal = new JTextField();
		tfGrandTotal.setText("0");
		tfGrandTotal.setEditable(false);
		tfGrandTotal.setBounds(594, 428, 86, 20);
		contentPane.add(tfGrandTotal);
		tfGrandTotal.setColumns(10);
		
		JLabel lblVAT = new JLabel("VAT");
		lblVAT.setBounds(505, 358, 79, 14);
		contentPane.add(lblVAT);
		
		JLabel lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setBounds(505, 431, 79, 14);
		contentPane.add(lblGrandTotal);
		
		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setBounds(505, 395, 79, 14);
		contentPane.add(lblDiscount);
		
		tfDiscount = new JTextField();
		tfDiscount.setEditable(false);
		tfDiscount.setText("0");
		tfDiscount.setBounds(594, 392, 86, 20);
		contentPane.add(tfDiscount);
		tfDiscount.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox myCombox = (JComboBox) e.getSource();
				String selected = (String) myCombox.getSelectedItem();
				
				psc.getSale().setPricingStrategy(selected);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Senior Discount 10%", "Eid Discount 100 Tk. Over 1000 Tk.", "Best for Customer", "Best for Store"}));
		comboBox.setBounds(97, 358, 244, 20);
		contentPane.add(comboBox);
		
		JButton btnCalculateDiscount = new JButton("Calculate Discount");
		btnCalculateDiscount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Sale sale = psc.getSale();
				tfDiscount.setText(String.valueOf(sale.getDiscount()));
				tfGrandTotal.setText(String.valueOf(sale.getGrandTotal()));
			}
		});
		btnCalculateDiscount.setBounds(97, 391, 156, 23);
		contentPane.add(btnCalculateDiscount);
	}
}
