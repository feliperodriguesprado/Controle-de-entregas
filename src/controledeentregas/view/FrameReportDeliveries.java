package controledeentregas.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import controledeentregas.model.to.DeliveryTO;
import controledeentregas.model.to.ProductReportDelivery;
import controledeentregas.model.to.SituationTO;
import controledeentregas.view.listener.FrameMainListener;

public class FrameReportDeliveries extends JDialog {

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
	
	private JScrollPane scrollDeliveries;
	private JTable tableDeliveries;
	private String[] columnsDeliveries = new String[] {LanguageResources.getResource("code"),
											 LanguageResources.getResource("date_creation"),
											 LanguageResources.getResource("date_prevision"),
											 LanguageResources.getResource("date_delivery"),
											 LanguageResources.getResource("description"),
											 LanguageResources.getResource("value")};
	private List<DeliveryTO> deliveries;
	
	private JScrollPane scrollProducts;
	private JTable tableProducts;
	private String[] columnsProducts = new String[] {LanguageResources.getResource("code"),
											 LanguageResources.getResource("description"),
											 LanguageResources.getResource("value"),
											 LanguageResources.getResource("quantity"),
											 LanguageResources.getResource("value_total")};
	private List<ProductReportDelivery> products;
	
	public FrameReportDeliveries(FrameMainListener frameMainListener) {
		this.frameMainListener = frameMainListener;
		deliveries = new ArrayList<DeliveryTO>();
		getProducts(0);
		initi();
	}
	
	private void initi() {
		setTitle(LanguageResources.getResource("deliveries_for_situation"));
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
		panelMain.add(getScrollDeliveries(), constraints);
		
		constraints.setInsets(new Insets(5, 5, 5, 5));
		constraints.setAnchor(GridBagConstraints.NORTH);
		constraints.setGridyGridx(2, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(0, 0);
		panelMain.add(getScrollProducts(), constraints);
		
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
	
	private void getDeliveries() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		try {
			Date startDate = null;
			Date endDate = null;
			startDate = simpleDateFormat.parse(getFieldStartDate().getText());
			endDate = simpleDateFormat.parse(getFieldEndDate().getText());
			SituationTO situationSelected = (SituationTO) comboBoxSituations.getSelectedItem();
			deliveries = frameMainListener.getReportDeliveriesForSituations(startDate, endDate, situationSelected.getId());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getProducts(int codeDelivery) {
		
		if (codeDelivery == 0) {
			products = new ArrayList<ProductReportDelivery>();
		} else {
			products = frameMainListener.getReportProdutctsDeliveriesForSituations(codeDelivery);			
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
						AbstractTableModel modelDeliveries = (AbstractTableModel) getTableDeliveries().getModel();
						AbstractTableModel modelProducts = (AbstractTableModel) getTableProducts().getModel();
						getDeliveries();
						getProducts(0);
						modelDeliveries.fireTableDataChanged();	
						modelProducts.fireTableDataChanged();
					}
				}
			});
		}
		return buttonAply;
	}
	
	private JScrollPane getScrollDeliveries() {
		if (scrollDeliveries == null) {
			scrollDeliveries = new JScrollPane(getTableDeliveries());
		}
		return scrollDeliveries;
	}
	
	private JTable getTableDeliveries() {
		if (tableDeliveries == null) {
			tableDeliveries = new JTable();
			tableDeliveries.setModel(new ModelTableDeliveries());
			tableDeliveries.setPreferredScrollableViewportSize(new Dimension(800, 230));
			
			tableDeliveries.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void keyReleased(KeyEvent arg0) {
					int index = getTableDeliveries().getSelectedRow();
					DeliveryTO deliveryTO = deliveries.get(index);
					AbstractTableModel model = (AbstractTableModel)getTableProducts().getModel();
					getProducts(deliveryTO.getCode());
					model.fireTableDataChanged();
					
				}
				
				@Override
				public void keyPressed(KeyEvent arg0) {
					
				}
			});
			
			tableDeliveries.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					int index = getTableDeliveries().getSelectedRow();
					DeliveryTO deliveryTO = deliveries.get(index);
					AbstractTableModel model = (AbstractTableModel)getTableProducts().getModel();
					getProducts(deliveryTO.getCode());
					model.fireTableDataChanged();
					
				}
			});
		}
		return tableDeliveries;
	}
	
	class ModelTableDeliveries extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return columnsDeliveries.length;
		}

		@Override
		public int getRowCount() {
			return deliveries.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			DeliveryTO deliveryTO = deliveries.get(row);
			if (column == 0) {
				return deliveryTO.getCode();
			} else if (column == 1) {
				return deliveryTO.getDateCreateString();
			} else if (column == 2) {
				return deliveryTO.getDatePrevisionString();
			} else if (column == 3) {
				return deliveryTO.getDateDeliveryString();
			} else if (column == 4) {
				return deliveryTO.getDescription();
			} else if (column == 5) {
				return deliveryTO.getValue();
			}
			else {
				return "error";
			}
		}
		
		@Override
		public String getColumnName(int column) {
			return columnsDeliveries[column];
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
	
	private JScrollPane getScrollProducts() {
		if (scrollProducts == null) {
			scrollProducts = new JScrollPane(getTableProducts());
		}
		return scrollProducts;
	}
	
	private JTable getTableProducts() {
		if (tableProducts == null) {
			tableProducts = new JTable();
			tableProducts.setModel(new ModelTableProducts());
			tableProducts.setPreferredScrollableViewportSize(new Dimension(800, 230));
		}
		return tableProducts;
	}

	class ModelTableProducts extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return columnsProducts.length;
		}

		@Override
		public int getRowCount() {
			return products.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			ProductReportDelivery productReportDelivery = products.get(row);
			if (column == 0) {
				return productReportDelivery.getCode();
			} else if (column == 1) {
				return productReportDelivery.getDescription();
			} else if (column == 2) {
				return productReportDelivery.getValue();
			} else if (column == 3) {
				return productReportDelivery.getQuantidy();
			} else if (column == 4) {
				return productReportDelivery.getValueTotal();
			} else {
				return "error";
			}
		}
		
		@Override
		public String getColumnName(int column) {
			return columnsProducts[column];
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
	
}
