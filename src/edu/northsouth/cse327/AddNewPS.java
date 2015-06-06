package edu.northsouth.cse327;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddNewPS extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddNewPS dialog = new AddNewPS();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddNewPS() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 11, 46, 14);
		contentPanel.add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 36, 46, 14);
		contentPanel.add(lblName);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 61, 77, 14);
		contentPanel.add(lblDescription);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 178, 46, 14);
		contentPanel.add(lblPrice);
		
		textField = new JTextField();
		textField.setBounds(96, 8, 286, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(96, 36, 286, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(97, 73, 285, 93);
		contentPanel.add(textArea);
		
		textField_2 = new JTextField();
		textField_2.setBounds(96, 175, 86, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Insert");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						ProductSpecification ps = new ProductSpecification( Integer.parseInt(textField.getText()) , textField_1.getText(), textArea.getText(), Integer.valueOf(textField_2.getText()) );
						SaleFactory.getInstance().add(ps);
					}
				});
				
				JButton btnDelete = new JButton("Delete");
				btnDelete.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						ProductSpecification ps = new ProductSpecification( Integer.parseInt(textField.getText()) , textField_1.getText(), textArea.getText(), Integer.valueOf(textField_2.getText()) );
						SaleFactory.getInstance().delete(ps);
					}
				});
				buttonPane.add(btnDelete);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Update");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						ProductSpecification ps = new ProductSpecification( Integer.parseInt(textField.getText()) , textField_1.getText(), textArea.getText(), Integer.valueOf(textField_2.getText()) );
						SaleFactory.getInstance().update(ps);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
