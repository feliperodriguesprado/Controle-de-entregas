package controledeentregas.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controledeentregas.controller.LanguageResources;
import controledeentregas.model.to.ProductsTO;
import controledeentregas.view.listener.ProductsListener;

public class DataPanelGBL extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lbCode = new JLabel(LanguageResources.getResource("code"));
	private JLabel lbDescription = new JLabel(LanguageResources.getResource("description"));
	private JLabel lbValue = new JLabel(LanguageResources.getResource("value"));
	private JTextField txCode;
	private JTextField txDescription;
	private JTextField txValue;
	private ProductsTO productsTO;

	ProductsListener botaoListener;

	public DataPanelGBL(ProductsTO productsTO) {
		this.productsTO = productsTO;
		init();
	}

	public JTextField getTxCode() {
		if (txCode == null) {
			txCode = new JTextField();
			txCode.setDocument(new FormatTextField(13, "U"));
			txCode.setText(this.productsTO.getCod());
		}
		return txCode;
	}

	public JTextField getTxDescription() {
		if (txDescription == null) {
			txDescription = new JTextField();
			txDescription.setDocument(new FormatTextField(50, "U"));
			txDescription.setText(this.productsTO.getDescription());
		}
		return txDescription;
	}

	public JTextField getTxValue() {
		if (txValue == null) {
			txValue = new JTextField();
			txValue.setDocument(new FormatNumericField(6, 2));			
			
			if (productsTO.getValue() != null) {
				String str = this.productsTO.getValue().toString();
				StringBuffer value = new StringBuffer(str);
				char c = '.';
				
				if (str.length() > 2) {
					int number = str.length() - 2;
					
					char h = str.charAt(number -1);
					
					if (h != c) {
						value.insert(str.length(), '0');
					}
					
				}
				txValue.setText(value.toString());
			}
		}
		return txValue;
	}

	private void init() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		add(lbCode, getConstraints(0, 0, 1));
		add(getTxCode(), getConstraints(1, 0, 5, 80));
		add(lbDescription, getConstraints(6, 0, 1));
		add(getTxDescription(), getConstraints(7, 0, 10, 200));
		add(lbValue, getConstraints(6, 1, 1));
		add(getTxValue(), getConstraints(7, 1, 1, 80));
	}

	private GridBagConstraints getConstraints(int x, int y, int largura) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.insets = new Insets(4, 4, 4, 4);
		constraints.gridwidth = largura;

		constraints.fill = GridBagConstraints.HORIZONTAL;
		return constraints;

	}

	private GridBagConstraints getConstraints(int x, int y, int largura,
			int ipadx) {
		GridBagConstraints constraints = getConstraints(x, y, largura);
		constraints.ipadx = ipadx;
		return constraints;
	}
}