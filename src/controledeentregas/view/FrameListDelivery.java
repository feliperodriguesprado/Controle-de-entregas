package controledeentregas.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import controledeentregas.controller.LanguageResources;
import controledeentregas.model.to.Delivery;
import controledeentregas.model.to.DeliveryTO;
import controledeentregas.view.listener.DeliverysActions;

public class FrameListDelivery extends JDialog {

	private static final long serialVersionUID = 1L;

	private DeliverysActions deliverysActions;

	private Container panelMain;

	private JPanel panelButtons;
	private JPanel panelListDeliveries;

	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonRemove;
	private JButton buttonReturn;
	
	private JScrollPane scrollPane;
	private JTable tableDeliveries;
	
	private String[] columns = new String[] {LanguageResources.getResource("number"),
											 LanguageResources.getResource("date_creation"),
											 LanguageResources.getResource("date_prevision"),
											 LanguageResources.getResource("date_delivery"),
											 LanguageResources.getResource("description"),
											 LanguageResources.getResource("value"),
											 LanguageResources.getResource("situation")};
	
	private List<Delivery> deliveries;

	public FrameListDelivery(DeliverysActions deliverysActions) {
		this.deliverysActions = deliverysActions;
		getListAllDeliveries();
		init();
	}

	private void init() {
		setTitle(LanguageResources.getResource("deliveries"));
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(true);
		setModal(true);
		
		panelMain = getContentPane();
		GridBagLayout gridBagLayout = new GridBagLayout();
		Constraints constraints = new Constraints();

		panelMain.setLayout(gridBagLayout);
		
		constraints.setAnchor(GridBagConstraints.NORTH);
		constraints.setGridyGridx(0, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(1, 1);
		panelMain.add(getPanelListDeliveries(), constraints);
		
		constraints.setAnchor(GridBagConstraints.NORTH);
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
	
	private void getListAllDeliveries() {
		this.deliveries = deliverysActions.getAllListDeliveries();
	}
	
	private JPanel getPanelListDeliveries() {
		if (panelListDeliveries == null) {
			panelListDeliveries = new JPanel();
			panelListDeliveries.add(getScrollPane());
			
			
		}
		return panelListDeliveries;
	}
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getTableDeliveries());
		}
		return scrollPane;
	}
	
	private JTable getTableDeliveries() {
		if (tableDeliveries == null) {
			tableDeliveries = new JTable();
			tableDeliveries.setModel(new Model());
			tableDeliveries.setPreferredScrollableViewportSize(new Dimension(770, 480));
			
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
					if (arg0.getClickCount() == 2) {
						int index = getTableDeliveries().getSelectedRow();
						Delivery delivery = deliveries.get(index);
						DeliveryTO deliveryTO = new DeliveryTO();
						deliveryTO.setCode(delivery.getCode());
						deliveryTO.setIdSituation(delivery.getIdSituation());
						deliveryTO.setDateCreate(delivery.getDateCreateString());
						deliveryTO.setDatePrevision(delivery.getDatePrevisionString());
						deliveryTO.setDateDelivery(delivery.getDateDelivery());
						deliveryTO.setDescription(delivery.getDescription());
						deliveryTO.setValue(delivery.getValue());
						deliverysActions.actionButtonEditFrameListDelivery(deliveryTO);
						AbstractTableModel model = (AbstractTableModel) getTableDeliveries().getModel();
						getListAllDeliveries();
						model.fireTableDataChanged();
					}
				}
			});
		}
		return tableDeliveries;
	}
	
	class Model extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		
		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public int getRowCount() {
			return deliveries.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			Delivery delivery = deliveries.get(row);
			if (column == 0) {
				return delivery.getCode();
			} else if (column == 1) {
				return delivery.getDateCreateString();
			} else if (column == 2) {
				return delivery.getDatePrevisionString();
			} else if (column == 3) {
				return delivery.getDateDeliveryString();
			} else if (column == 4) {
				return delivery.getDescription();
			} else if (column == 5) {
				return delivery.getValue();
			} else if (column == 6) {
				return delivery.getSituation();
			}
			else {
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
		
	}
	
	private JPanel getPanelButtons() {
		if (panelButtons == null) {
			panelButtons = new JPanel();
			panelButtons.add(getButtonNew());
			panelButtons.add(getButtonEdit());
			panelButtons.add(getButtonRemove());
			panelButtons.add(getButtonReturn());
		}
		return panelButtons;
	}
	
	private JButton getButtonNew() {
		if (buttonNew == null) {
			buttonNew = new JButton(LanguageResources.getResource("new"));

			buttonNew.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					deliverysActions.actionButtonNewFramListDelivery();
					
					AbstractTableModel model = (AbstractTableModel) getTableDeliveries().getModel();
					getListAllDeliveries();
					model.fireTableDataChanged();
				}
			});
		}
		return buttonNew;
	}
	
	public JButton getButtonEdit() {
		if (buttonEdit == null) {
			buttonEdit = new JButton(LanguageResources.getResource("edit"));

			buttonEdit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					int index = getTableDeliveries().getSelectedRow();
					
					if (index == -1) {
						JOptionPane.showMessageDialog(null, LanguageResources.getResource("no_item_selected"));
					} else {
						
						Delivery delivery = deliveries.get(index);
						
						DeliveryTO deliveryTO = new DeliveryTO();
						deliveryTO.setCode(delivery.getCode());
						deliveryTO.setIdSituation(delivery.getIdSituation());
						deliveryTO.setDateCreate(delivery.getDateCreateString());
						deliveryTO.setDatePrevision(delivery.getDatePrevisionString());
						deliveryTO.setDateDelivery(delivery.getDateDelivery());
						deliveryTO.setDescription(delivery.getDescription());
						deliveryTO.setValue(delivery.getValue());
						
						deliverysActions.actionButtonEditFrameListDelivery(deliveryTO);
						AbstractTableModel model = (AbstractTableModel) getTableDeliveries().getModel();
						getListAllDeliveries();
						model.fireTableDataChanged();
					}
					
					
					
				}
			});
		}
		return buttonEdit;
	}
	
	public JButton getButtonRemove() {
		if (buttonRemove == null) {
			buttonRemove = new JButton(LanguageResources.getResource("remove"));
			buttonRemove.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int index = getTableDeliveries().getSelectedRow();
					if (index == -1) {
						JOptionPane.showMessageDialog(null, LanguageResources.getResource("no_item_selected"));
					} else {
						Delivery delivery = deliveries.get(index);
						DeliveryTO deliveryTO = new DeliveryTO();
						deliveryTO.setCode(delivery.getCode());
						deliveryTO.setIdSituation(delivery.getIdSituation());
						deliveryTO.setDateCreate(delivery.getDateCreateString());
						deliveryTO.setDatePrevision(delivery.getDatePrevisionString());
						deliveryTO.setDateDelivery(delivery.getDateDelivery());
						deliveryTO.setDescription(delivery.getDescription());
						deliveryTO.setValue(delivery.getValue());
						if (Messages.getMessageRemoveRegistration(delivery.getCode().toString())) {
							deliverysActions.actionButtonRemoveFrameListDelivery(deliveryTO);
							AbstractTableModel model = (AbstractTableModel) getTableDeliveries().getModel();
							getListAllDeliveries();
							model.fireTableDataChanged();
						}
					}
				}
			});
		}
		return buttonRemove;
	}
	
	public JButton getButtonReturn() {
		if (buttonReturn == null) {
			buttonReturn = new JButton(LanguageResources.getResource("return"));
			buttonReturn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (Messages.getMessageOptionClose()) {
						dispose();
					} else {
						setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					}
				}
			});
		}
		return buttonReturn;
	}
	
}
