package controledeentregas.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import controledeentregas.controller.LanguageResources;
import controledeentregas.model.to.NumberDeliveriesForProducts;
import controledeentregas.model.to.SituationTO;
import controledeentregas.view.listener.FrameMainListener;

public class FrameReportNumberDeliveriesForProducts extends JDialog {

	private static final long serialVersionUID = 1L;
	private FrameMainListener frameMainListener;
	private Container panelMain;
	private GridBagLayout gridBagLayout;
	
	private JPanel panelFilters;
	private JLabel labelStartDate;
	private JFormattedTextField fieldStartDate;
	private JLabel labelEndDate;
	private JFormattedTextField fieldEndDate;
	private JComboBox<SituationTO> comboBoxSituations;
	private List<SituationTO> situations;
	private JButton buttonAply;
	
	private JScrollPane scrollNumberDeliveries;
	private JTable tableNumberDeliveries;
	private String[] columns = new String[] {LanguageResources.getResource("code"),
											 LanguageResources.getResource("description"),
											 LanguageResources.getResource("quantity_of_deliveries")};
	private List<NumberDeliveriesForProducts> numberDeliveries;
	
	public FrameReportNumberDeliveriesForProducts(FrameMainListener frameMainListener) {
		this.frameMainListener = frameMainListener;
		numberDeliveries = new ArrayList<NumberDeliveriesForProducts>();
		initi();
	}
	
	private void initi() {
		setTitle(LanguageResources.getResource("number_deliveries_for_products"));
		setSize(900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		panelMain = getContentPane();
		gridBagLayout = new GridBagLayout();
		Constraints constraints = new Constraints();
		panelMain.setLayout(gridBagLayout);
		
		constraints.setInsets(new Insets(5, 5, 5, 5));
		constraints.setAnchor(GridBagConstraints.NORTH);
		constraints.setGridyGridx(0, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(0, 0);
		panelMain.add(getPanelFilters(), constraints);
		
		constraints.setInsets(new Insets(5, 5, 5, 5));
		constraints.setAnchor(GridBagConstraints.NORTH);
		constraints.setGridyGridx(1, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(0, 0);
		panelMain.add(getScrollNumberDeliveries(), constraints);
		
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
	
	private void getNumberDeliveries() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date startDate = null;
			Date endDate = null;
			startDate = simpleDateFormat.parse(getFieldStartDate().getText());
			endDate = simpleDateFormat.parse(getFieldEndDate().getText());
			SituationTO situationSelected = (SituationTO) comboBoxSituations
					.getSelectedItem();
			numberDeliveries = frameMainListener.getNumberDeliveriesForProducts(startDate, endDate, situationSelected.getId());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private JPanel getPanelFilters() {
		if (panelFilters == null) {
			panelFilters = new JPanel();
			panelFilters.add(getLabelStartDate());
			panelFilters.add(getFieldStartDate());
			panelFilters.add(getLabelEndDate());
			panelFilters.add(getFieldEndDate());
			panelFilters.add(getComboBoxSituations());
			panelFilters.add(getButtonAply());
		}
		return panelFilters;
	}
	
	private JLabel getLabelStartDate() {
		if (labelStartDate == null) {
			labelStartDate = new JLabel(LanguageResources.getResource("start_date"));
		}
		return labelStartDate;
	}
	
	private JFormattedTextField getFieldStartDate() {
		if (fieldStartDate == null) {
			fieldStartDate = new JFormattedTextField(FormatMaskField.getMaskDateField());
		}
		return fieldStartDate;
	}
	
	private JLabel getLabelEndDate() {
		if (labelEndDate == null) {
			labelEndDate = new JLabel(LanguageResources.getResource("end_date"));
		}
		return labelEndDate;
	}
	
	private JFormattedTextField getFieldEndDate() {
		if (fieldEndDate == null) {
			fieldEndDate = new JFormattedTextField(FormatMaskField.getMaskDateField());
		}
		return fieldEndDate;
	}
	
	private JComboBox<SituationTO> getComboBoxSituations() {
		if (comboBoxSituations == null) {
			comboBoxSituations = new JComboBox<SituationTO>();
			situations = frameMainListener.getAllSituatiosActive();
			for (SituationTO situationTO : situations) {
				comboBoxSituations.addItem(situationTO);
			}
		}
		return comboBoxSituations;
	}
	
	private JButton getButtonAply() {
		if (buttonAply == null) {
			buttonAply = new JButton(LanguageResources.getResource("aply"));
			
			buttonAply.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					SituationTO situationSelected = (SituationTO) comboBoxSituations.getSelectedItem();
					
					if (fieldStartDate.getText().equals("__/__/____") || fieldEndDate.getText().equals("__/__/____") || situationSelected == null) {
						Messages.getMessageFiltersIsEmpty();
					} else {
						AbstractTableModel model = (AbstractTableModel) getTableNumberDeliveries().getModel();
						getNumberDeliveries();
						model.fireTableDataChanged();	
					}
				}
			});
		}
		return buttonAply;
	}
	
	private JScrollPane getScrollNumberDeliveries() {
		if (scrollNumberDeliveries == null) {
			scrollNumberDeliveries = new JScrollPane(getTableNumberDeliveries());
		}
		return scrollNumberDeliveries;
	}
	
	private JTable getTableNumberDeliveries() {
		if (tableNumberDeliveries == null) {
			tableNumberDeliveries = new JTable();
			tableNumberDeliveries.setModel(new ModelTableDeliveries());
			tableNumberDeliveries.setPreferredScrollableViewportSize(new Dimension(800, 470));
		}
		return tableNumberDeliveries;
	}
	
	class ModelTableDeliveries extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public int getRowCount() {
			return numberDeliveries.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			
			NumberDeliveriesForProducts numberDeliveriesForProducts = numberDeliveries.get(row);
			if (column == 0) {
				return numberDeliveriesForProducts.getCode();
			} else if (column == 1) {
				return numberDeliveriesForProducts.getDescription();
			} else if (column == 2) {
				return numberDeliveriesForProducts.getNumberDeliveries();
			} else {
				return "error";
			}
		}
		
		@Override
		public String getColumnName(int column) {
			return columns[column];
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}}
