package controle_de_entrega.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controle_de_entrega.controller.LanguageResources;
import controle_de_entrega.model.to.ProductsTO;
import controle_de_entrega.view.listener.ProductsListener;

public class FrameRegisterProduct extends JDialog {
	ProductsListener btLisntener;
	private static final long serialVersionUID = 1L;

	private JButton save, back;
	private JPanel pnBotoes;
	private DataPanelGBL painelDadosGBL;
	private ProductsTO productsTO;

	public FrameRegisterProduct(ProductsListener btlistener,
			ProductsTO productsTO) {
		this.btLisntener = btlistener;
		this.productsTO = productsTO;

		initCadastro();
	}

	public void initCadastro() {
		setSize(480, 180);
		setLocationRelativeTo(null);
		setTitle(LanguageResources.getResource("registration_products"));
		setModal(true);

		Container panel = getContentPane();
		painelDadosGBL = new DataPanelGBL(productsTO);
		panel.add(painelDadosGBL, BorderLayout.CENTER);
		panel.add(getPnBotoes(), BorderLayout.SOUTH);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (Messages.getMessageOptionClose()) {
					dispose();
				} else {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}

	public JPanel getPnBotoes() {
		if (pnBotoes == null) {
			pnBotoes = new JPanel();
			pnBotoes.add(getSave());
			pnBotoes.add(getBack());
		}
		return pnBotoes;
	}

	public JButton getBack() {
		if (back == null) {
			back = new JButton(LanguageResources.getResource("cancel"));

			ActionListener listener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (Messages.getMessageOptionCancel()) {
						btLisntener.actionButtonBackFrameListProduct();
					}

				}
			};

			back.addActionListener(listener);
		}
		return back;
	}

	public JButton getSave() {
		if (save == null) {
			save = new JButton(LanguageResources.getResource("save"));
			ActionListener listener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (Messages.getMessageSaveRegistration()) {

						if (painelDadosGBL.getTxCode().getText().isEmpty()
								|| painelDadosGBL.getTxDescription().getText()
										.isEmpty()
								|| painelDadosGBL.getTxValue().getText()
										.isEmpty()) {
							Messages.getMessageFieldsIsEmpty();
						} else {

							productsTO.setCod(painelDadosGBL.getTxCode()
									.getText());
							productsTO.setDescription(painelDadosGBL
									.getTxDescription().getText());

							StringBuffer value = new StringBuffer(
									painelDadosGBL.getTxValue().getText());
							String str = value.toString().replaceAll("[.]", "");
							value = new StringBuffer(str);
							str = value.toString().replaceAll("[,]", ".");

							productsTO.setValue(Float.parseFloat(str));

							boolean result = btLisntener
									.actionButtonSaveFrameRegistrationProduct(productsTO);

							if (result) {
								dispose();
							} else {
								Messages.getMessageUnique();
							}
						}
					}

				}
			};
			save.addActionListener(listener);
		}
		return save;
	}
}