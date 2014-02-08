package controle_de_entrega.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controle_de_entrega.controller.LanguageResources;
import controle_de_entrega.model.to.SituationTO;
import controle_de_entrega.view.listener.SituationsActions;

public class FrameRegistrationSituations extends JDialog {

	private static final long serialVersionUID = 1L;

	private SituationsActions situationsActions;
	private SituationTO situationTO;

	private Container panelMain;
	private JPanel panelForm;
	private JPanel panelButtons;

	private JLabel labelSituation;
	private JTextField fieldSituation;

	private JButton buttonSave;
	private JButton buttonCancel;

	public FrameRegistrationSituations(SituationsActions situationsActions, SituationTO situationTO) {
		this.situationsActions = situationsActions;
		this.situationTO = situationTO;
		init();
	}

	private void init() {

		setTitle(LanguageResources.getResource("registration_situations"));
		setSize(400, 140);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);

		panelMain = getContentPane();
		GridBagLayout gridBagLayout = new GridBagLayout();
		Constraints constraints = new Constraints();
		panelMain.setLayout(gridBagLayout);

		constraints.setInsets(new Insets(20, 5, 5, 5));
		constraints.setAnchor(GridBagConstraints.NORTH);
		constraints.setGridyGridx(0, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(1, 1);
		panelMain.add(getPanelForm(), constraints);

		constraints.setInsets(new Insets(5, 5, 5, 5));
		constraints.setAnchor(GridBagConstraints.SOUTH);
		constraints.setGridyGridx(1, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(1, 1);
		panelMain.add(getPanelButtons(), constraints);
		
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

	private JPanel getPanelForm() {
		if (panelForm == null) {
			panelForm = new JPanel();

			GridBagLayout gridBagLayout = new GridBagLayout();
			Constraints constraints = new Constraints();
			panelForm.setLayout(gridBagLayout);

			constraints.setInsets(new Insets(5, 5, 5, 5));

			constraints.setAnchor(GridBagConstraints.NORTHEAST);
			constraints.setGridyGridx(0, 0);
			constraints.setGridwidthGridheight(1, 1);
			constraints.setWeightyWeightx(1, 1);
			panelForm.add(getLabelSituation(), constraints);

			constraints.setAnchor(GridBagConstraints.NORTHWEST);
			constraints.setGridyGridx(0, 1);
			constraints.setGridwidthGridheight(1, 1);
			constraints.setWeightyWeightx(1, 1);
			constraints.setIpadxIpadY(300, 0);
			panelForm.add(getFieldSituation(), constraints);

		}
		return panelForm;
	}

	private JPanel getPanelButtons() {
		if (panelButtons == null) {
			panelButtons = new JPanel();
			panelButtons.add(getButtonSave());
			panelButtons.add(getButtonCancel());
		}
		return panelButtons;
	}

	private JLabel getLabelSituation() {
		if (labelSituation == null) {
			labelSituation = new JLabel(
					LanguageResources.getResource("situation"));
		}
		return labelSituation;
	}

	private JTextField getFieldSituation() {
		if (fieldSituation == null) {
			fieldSituation = new JTextField();
			fieldSituation.setDocument(new FormatTextField(40, "U"));
			fieldSituation.setText(this.situationTO.getDescription());
		}
		return fieldSituation;
	}

	public JButton getButtonSave() {
		if (buttonSave == null) {
			buttonSave = new JButton(LanguageResources.getResource("save"));

			buttonSave.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					if (Messages.getMessageSaveRegistration()) {
						
						if (fieldSituation.getText().isEmpty()) {
							Messages.getMessageFieldsIsEmpty();
						} else if (!fieldSituation.getText().isEmpty()) {
							
							situationTO.setDescription(fieldSituation.getText());
							boolean result = situationsActions.actionButtonSaveFrameRegistrationSituation(situationTO);

							if (result) {
								dispose();
							} else {
								Messages.getMessageUnique();
							}
						}
					}
				}
			});
		}
		return buttonSave;
	}

	public JButton getButtonCancel() {
		if (buttonCancel == null) {
			buttonCancel = new JButton(LanguageResources.getResource("cancel"));
			
			buttonCancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (Messages.getMessageOptionCancel()) {
						situationsActions.actionButtonCancelFrameRegistrationSituation();						
					}
					
				}
			});
		}
		return buttonCancel;
	}

}
