package controle_de_entrega.view;

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
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import controle_de_entrega.controller.LanguageResources;
import controle_de_entrega.model.to.DeliveryTO;
import controle_de_entrega.model.to.ProductsResearched;
import controle_de_entrega.model.to.SituationTO;
import controle_de_entrega.view.listener.DeliverysActions;

public class FrameRegistrationDelivery extends JDialog {

	private static final long serialVersionUID = 1L;

	private SimpleDateFormat simpleDateFormat;
	private Date date;
	private DeliverysActions deliverysActions;
	private DeliveryTO deliveryTO;
	private GridBagLayout gridBagLayout;
	
	private Container panelMain;
	
	private JPanel panelForm;
	private JLabel labelDatePrevision;
	private JFormattedTextField fieldDatePrevision;
	private JLabel labelDateDelivery;
	private JFormattedTextField fieldDateDelivery;
	private JLabel labelCode;
	private JLabel labelDescription;
	private JTextField fieldDescription;
	private JLabel labelDateCreate;
	private JLabel labelSituation;
	private JComboBox<SituationTO> comboBoxSituations;
	private List<SituationTO> situations;
	
	private JPanel panelSearchProduct;
	private JLabel labelSearchProduct;
	private JTextField fieldSearchProduct;
	
	private JPanel panelButtonsAddRemove;
	private JButton buttonAdd;
	private JButton buttonRemove;
	
	private JScrollPane scrollTableSearch;
	private JTable tableSearch;
	private String[] columnsTableSearch = new String[] {LanguageResources.getResource("code"),
											 LanguageResources.getResource("description"),
											 LanguageResources.getResource("value"),
											 LanguageResources.getResource("select")};
	private List<ProductsResearched> produtctsResearched;
	
	private JScrollPane scrollTableProducs;
	private JTable tableProducts;
	private String[] columnsTableProducts = new String[] {LanguageResources.getResource("code"),
			 LanguageResources.getResource("description"),
			 LanguageResources.getResource("value"),
			 LanguageResources.getResource("quantity"),
			 LanguageResources.getResource("select")};
	private List<ProductsResearched> productsTable;
	
	private JPanel panelButtons;
	private JButton buttonSave;
	private JButton buttonCancel;
	
	public FrameRegistrationDelivery(DeliverysActions deliverysActions, DeliveryTO deliveryTO) {
		this.deliverysActions = deliverysActions;
		this.deliveryTO = deliveryTO;
		setProductsResearched("");
		setProductsTable();
		simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		date = new Date();
		init();
	}

	private void init() {
		setTitle(LanguageResources.getResource("registration_deliveries"));
		setSize(900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);

		panelMain = getContentPane();

		gridBagLayout = new GridBagLayout();
		Constraints constraints = new Constraints();

		panelMain.setLayout(gridBagLayout);

		constraints.setInsets(new Insets(20, 5, 5, 5));
		constraints.setAnchor(GridBagConstraints.NORTH);
		constraints.setGridyGridx(0, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(1, 1);
		panelMain.add(getPanelForm(), constraints);

		constraints.setInsets(new Insets(50, 5, 0, 5));
		constraints.setAnchor(GridBagConstraints.CENTER);
		constraints.setGridyGridx(1, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(1, 1);
		panelMain.add(getPanelSearchProduct(), constraints);
		
		constraints.setInsets(new Insets(0, 5, 5, 5));
		constraints.setAnchor(GridBagConstraints.NORTH);
		constraints.setGridyGridx(2, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(1, 1);
		panelMain.add(getScrollTableSearch(), constraints);

		constraints.setInsets(new Insets(5, 5, 0, 5));
		constraints.setAnchor(GridBagConstraints.NORTH);
		constraints.setGridyGridx(3, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(1, 1);
		panelMain.add(getPanelButtonsAddRemove(), constraints);
		
		constraints.setInsets(new Insets(0, 5, 5, 5));
		constraints.setAnchor(GridBagConstraints.NORTH);
		constraints.setGridyGridx(4, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(1, 1);
		panelMain.add(getScrollTableProducts(), constraints);
		
		constraints.setInsets(new Insets(5, 5, 5, 5));
		constraints.setAnchor(GridBagConstraints.SOUTH);
		constraints.setGridyGridx(5, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(1, 1);
		panelMain.add(getPanelButtons(), constraints);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (Messages.getMessageOptionClose()) {
					deliverysActions.actionButtonCancelFrameRegistrationDelivery();
				} else {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		
	}
	
	private void setProductsResearched(String search) {
		this.produtctsResearched = deliverysActions.getProductResearched(search);
	}

	private void setProductsTable() {
		
		if (deliveryTO.getCode() == null) {
			this.productsTable = new ArrayList<ProductsResearched>();			
		} else {
			this.productsTable = deliverysActions.getProductsDelivery(this.deliveryTO);
		}
		
	}
	
	private JPanel getPanelForm() {
		if (panelForm == null) {
			panelForm = new JPanel();
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			Constraints constraints = new Constraints();

			panelForm.setLayout(gridBagLayout);
			constraints.setFill(GridBagConstraints.HORIZONTAL);

			constraints.setInsets(new Insets(5, 5, 5, 5));
			constraints.setAnchor(GridBagConstraints.EAST);
			constraints.setGridyGridx(0, 0);
			constraints.setGridwidthGridheight(1, 1);
			constraints.setWeightyWeightx(0, 0);
			constraints.setIpadxIpadY(0, 0);
			panelForm.add(getLabelDatePrevision(), constraints);
			
			constraints.setInsets(new Insets(5, 5, 5, 5));
			constraints.setAnchor(GridBagConstraints.WEST);
			constraints.setGridyGridx(0, 1);
			constraints.setGridwidthGridheight(1, 1);
			constraints.setWeightyWeightx(0, 0);
			constraints.setIpadxIpadY(0, 0);
			panelForm.add(getFieldDatePrevision(), constraints);
			
			constraints.setInsets(new Insets(5, 5, 5, 5));
			constraints.setAnchor(GridBagConstraints.EAST);
			constraints.setGridyGridx(0, 3);
			constraints.setGridwidthGridheight(1, 1);
			constraints.setWeightyWeightx(0, 0);
			constraints.setIpadxIpadY(0, 0);
			panelForm.add(getLabelDateDelivery(), constraints);
			
			constraints.setInsets(new Insets(5, 5, 5, 5));
			constraints.setAnchor(GridBagConstraints.WEST);
			constraints.setGridyGridx(0, 4);
			constraints.setGridwidthGridheight(1, 1);
			constraints.setWeightyWeightx(0, 0);
			constraints.setIpadxIpadY(10, 0);
			panelForm.add(getFieldDateDelivery(), constraints);
			
			constraints.setInsets(new Insets(5, 50, 5, 5));
			constraints.setAnchor(GridBagConstraints.WEST);
			constraints.setGridyGridx(0, 7);
			constraints.setGridwidthGridheight(1, 1);
			constraints.setWeightyWeightx(0, 0);
			constraints.setIpadxIpadY(0, 0);
			panelForm.add(getLabelDateCreate(), constraints);

			constraints.setInsets(new Insets(5, 5, 5, 5));
			constraints.setAnchor(GridBagConstraints.EAST);
			constraints.setGridyGridx(1, 0);
			constraints.setGridwidthGridheight(1, 1);
			constraints.setWeightyWeightx(0, 0);
			constraints.setIpadxIpadY(0, 0);
			panelForm.add(getLabelDescription(), constraints);
			
			constraints.setInsets(new Insets(5, 5, 5, 5));
			constraints.setAnchor(GridBagConstraints.WEST);
			constraints.setGridyGridx(1, 1);
			constraints.setGridwidthGridheight(5, 1);
			constraints.setWeightyWeightx(0, 1);
			constraints.setIpadxIpadY(0, 0);
			panelForm.add(getFieldDescription(), constraints);
		
			if (deliveryTO.getCode() != null) {
				constraints.setInsets(new Insets(5, 50, 5, 5));
				constraints.setAnchor(GridBagConstraints.WEST);
				constraints.setGridyGridx(1, 7);
				constraints.setGridwidthGridheight(1, 1);
				constraints.setWeightyWeightx(0, 0);
				constraints.setIpadxIpadY(0, 0);
				panelForm.add(getLabelCode(), constraints);
			}
			
			constraints.setInsets(new Insets(5, 5, 5, 5));
			constraints.setAnchor(GridBagConstraints.EAST);
			constraints.setGridyGridx(2, 0);
			constraints.setGridwidthGridheight(1, 1);
			constraints.setWeightyWeightx(0, 0);
			constraints.setIpadxIpadY(0, 0);
			panelForm.add(getLabelSituation(), constraints);
			
			constraints.setInsets(new Insets(5, 5, 5, 5));
			constraints.setAnchor(GridBagConstraints.WEST);
			constraints.setGridyGridx(2, 1);
			constraints.setGridwidthGridheight(2, 1);
			constraints.setWeightyWeightx(0, 0);
			constraints.setIpadxIpadY(0, 0);
			panelForm.add(getComboBoxSituations(), constraints);
		}
		return panelForm;
	}
	
	private JLabel getLabelDatePrevision() {
		if (labelDatePrevision == null) {
			labelDatePrevision = new JLabel(LanguageResources.getResource("date_prevision"));
		}
		return labelDatePrevision;
	}
	
	private JFormattedTextField getFieldDatePrevision() {
		if (fieldDatePrevision == null) {
			fieldDatePrevision = new JFormattedTextField(FormatMaskField.getMaskDateField());
			
			if (deliveryTO.getDatePrevisionString() == "") {
				fieldDatePrevision.setText(simpleDateFormat.format(date));
			} else {
				fieldDatePrevision.setText(deliveryTO.getDatePrevisionString());
			}
		}
		return fieldDatePrevision;
	}
	
	private JLabel getLabelDateDelivery() {
		if (labelDateDelivery == null) {
			labelDateDelivery = new JLabel(LanguageResources.getResource("date_delivery"));
		}
		return labelDateDelivery;
	}
	
	private JFormattedTextField getFieldDateDelivery() {
		if (fieldDateDelivery == null) {
			fieldDateDelivery = new JFormattedTextField(FormatMaskField.getMaskDateField());	
			
			fieldDateDelivery.setText(deliveryTO.getDateDeliveryString());
		}
		return fieldDateDelivery;
	}
	
	private JLabel getLabelDescription() {
		if (labelDescription == null) {
			labelDescription = new JLabel(LanguageResources.getResource("description"));
		}
		return labelDescription;
	}
	
	private JTextField getFieldDescription() {
		if (fieldDescription == null) {
			fieldDescription = new JTextField();
			fieldDescription.setDocument(new FormatTextField(100, ""));
			fieldDescription.setText(deliveryTO.getDescription());
		}
		return fieldDescription;
	}
	
	private JLabel getLabelCode() {
		if (labelCode == null) {
			labelCode = new JLabel(LanguageResources.getResource("number") + ": " + deliveryTO.getCode());
		}
		return labelCode;
	}

	private JLabel getLabelDateCreate() {
		if (labelDateCreate == null) {
			if (deliveryTO.getDateCreateString() == "") {
				labelDateCreate = new JLabel(LanguageResources.getResource("date_creation") + ": " + simpleDateFormat.format(date));
			} else {
				labelDateCreate = new JLabel(LanguageResources.getResource("date_creation") + ": " + deliveryTO.getDateCreateString());				
			}
		}
		return labelDateCreate;
	}
	
	private JLabel getLabelSituation() {
		if (labelSituation == null) {
			labelSituation = new JLabel(LanguageResources.getResource("situation"));
		}
		return labelSituation;
	}
	
	private JComboBox<SituationTO> getComboBoxSituations() {
		if (comboBoxSituations == null) {
			comboBoxSituations = new JComboBox<SituationTO>();
			situations = deliverysActions.getAllSituatiosActive();
			for (SituationTO situationTO : situations) {
				comboBoxSituations.addItem(situationTO);
				if (deliveryTO.getIdSituation() == situationTO.getId()) {
					comboBoxSituations.setSelectedIndex(situations.indexOf(situationTO));
				}
			}
		}
		return comboBoxSituations;
	}
	
	private JPanel getPanelSearchProduct() {
		if (panelSearchProduct == null) {
			panelSearchProduct = new JPanel();

			panelSearchProduct.setLayout(gridBagLayout);
			
			Constraints constraints = new Constraints();

			constraints.setInsets(new Insets(5, 5, 5, 5));
			constraints.setAnchor(GridBagConstraints.EAST);
			constraints.setGridyGridx(0, 0);
			constraints.setGridwidthGridheight(1, 1);
			constraints.setWeightyWeightx(0, 0);
			constraints.setIpadxIpadY(0, 0);
			panelSearchProduct.add(getLabelSearchProduct(), constraints);

			constraints.setInsets(new Insets(5, 5, 5, 5));
			constraints.setAnchor(GridBagConstraints.WEST);
			constraints.setGridyGridx(0, 1);
			constraints.setGridwidthGridheight(1, 1);
			constraints.setWeightyWeightx(0, 1);
			constraints.setIpadxIpadY(200, 0);
			panelSearchProduct.add(getFieldSearchProduct(), constraints);

		}
		return panelSearchProduct;
	}
	
	private JLabel getLabelSearchProduct() {
		if (labelSearchProduct == null) {
			labelSearchProduct = new JLabel(LanguageResources.getResource("product"));
		}
		return labelSearchProduct;
	}
	
	private JTextField getFieldSearchProduct() {
		if (fieldSearchProduct == null) {
			fieldSearchProduct = new JTextField();
			fieldSearchProduct.setDocument(new FormatTextField(50, "U"));
			fieldSearchProduct.addKeyListener(new KeyListener() {
				
				@Override
				public void keyReleased(KeyEvent arg0) {
					AbstractTableModel model = (AbstractTableModel)getTableSearch().getModel();
					setProductsResearched(fieldSearchProduct.getText());
					model.fireTableStructureChanged();
				}

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return fieldSearchProduct;
	}
	
	private JScrollPane getScrollTableSearch() {
		if (scrollTableSearch == null) {
			scrollTableSearch = new JScrollPane(getTableSearch());
		}
		return scrollTableSearch;
	}
	
	private JTable getTableSearch() {
		if (tableSearch == null) {
			tableSearch = new JTable();
			tableSearch.setModel(new ModelTableSearch());
			tableSearch.setPreferredScrollableViewportSize(new Dimension(650, 100));
			//tableSearch.setFillsViewportHeight(true);
			
			tableSearch.addMouseListener(new MouseListener() {
				
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
					
					int indexRow = getTableSearch().getSelectedRow();
					int indexColumn = getTableSearch().getSelectedColumn();
					
					if (indexColumn == 3) {
						
						ProductsResearched productsResearched = produtctsResearched.get(indexRow);
						
						if (productsResearched.isSelect()) {
							productsResearched.setSelect(false);
						} else {
							productsResearched.setSelect(true);							
						}
						produtctsResearched.set(indexRow, productsResearched);
						AbstractTableModel model = (AbstractTableModel)getTableSearch().getModel();
						model.fireTableDataChanged();
					}		
							
				}
			});
		}
		return tableSearch;
	}

	class ModelTableSearch extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return columnsTableSearch.length;
		}

		@Override
		public int getRowCount() {
			return produtctsResearched.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			ProductsResearched productsResearched = produtctsResearched.get(row);
			if (column == 0) {
				return productsResearched.getCode();
			} else if (column == 1) {
				return productsResearched.getDescription();
			} else if (column == 2) {
				return productsResearched.getValue();
			} else if (column == 3) {
				return productsResearched.isSelect();
			}
			else {
				return "error";
			}
		}

		@Override public Class<?> getColumnClass(int columnIndex) {       
	        if (columnIndex == 3) 
	            return Boolean.class;
	        else 
	            return String.class;
	    }
		
		@Override
		public String getColumnName(int column) {
			return columnsTableSearch[column];
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
	
	private JPanel getPanelButtonsAddRemove() {
		if (panelButtonsAddRemove == null) {
			panelButtonsAddRemove = new JPanel();
			panelButtonsAddRemove.add(getButtonAdd());
			panelButtonsAddRemove.add(getButtonRemove());
		}
		return panelButtonsAddRemove;
	}
	
	private JButton getButtonAdd() {
		if (buttonAdd == null) {
			buttonAdd = new JButton(LanguageResources.getResource("add"));
			
			buttonAdd.addActionListener(new ActionListener() {

				private boolean exists; 
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					for (ProductsResearched productsResearched : produtctsResearched) {

						if (productsResearched.isSelect()) {
							
							if (productsTable.size() == 0) {
								
								productsResearched.setSelect(false);
								productsTable.add(productsResearched);							
								AbstractTableModel modelTableProducts = (AbstractTableModel)getTableProducts().getModel();
								modelTableProducts.fireTableDataChanged();									
								setProductsResearched(fieldSearchProduct.getText());
								AbstractTableModel modelProductsResearched = (AbstractTableModel)getTableSearch().getModel();
								modelProductsResearched.fireTableDataChanged();

							} else {
								
								for (ProductsResearched productsResearched2 : productsTable) {
									if (productsResearched.getCode().equals(productsResearched2.getCode())) {
										exists = true;
									}	
								}
								
								if (exists) {
									Messages.getMessageItemAlreadyInsered(productsResearched.getDescription());
								} else {
									productsResearched.setSelect(false);
									productsTable.add(productsResearched);
									AbstractTableModel modelTableProducts = (AbstractTableModel)getTableProducts().getModel();
									modelTableProducts.fireTableDataChanged();
									setProductsResearched(fieldSearchProduct.getText());
									AbstractTableModel modelProductsResearched = (AbstractTableModel)getTableSearch().getModel();
									modelProductsResearched.fireTableDataChanged();
									
								}
								exists = false;
							}
						}
					}
				}
			});
		}
		return buttonAdd;
	}
	
	private JButton getButtonRemove() {
		if (buttonRemove == null) {
			buttonRemove = new JButton(LanguageResources.getResource("remove"));
			
			buttonRemove.addActionListener(new ActionListener() {
				
				private List<ProductsResearched> p = new ArrayList<ProductsResearched>();
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					for (ProductsResearched productsResearched : productsTable) {
						p.add(productsResearched);
					}
					
					for (ProductsResearched productsResearched : p) {

						if (productsResearched.isSelect()) {
							productsTable.remove(productsResearched);
							AbstractTableModel modelTableProducts = (AbstractTableModel)getTableProducts().getModel();
							modelTableProducts.fireTableDataChanged();
						}
					}
				}
			});
		}
		return buttonRemove;
	}

	private JScrollPane getScrollTableProducts() {
		if (scrollTableProducs == null) {
			scrollTableProducs = new JScrollPane(getTableProducts());
		}
		return scrollTableProducs;
	}
	
	private JTable getTableProducts() {
		if (tableProducts == null) {
			tableProducts = new JTable();
			tableProducts.setModel(new ModelTableProducts());
			tableProducts.setPreferredScrollableViewportSize(new Dimension(650, 100));
			
			tableProducts.addMouseListener(new MouseListener() {
				
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
					
					int indexRow = getTableProducts().getSelectedRow();
					int indexColumn = getTableProducts().getSelectedColumn();
					
					if (indexColumn == 4) {
						
						ProductsResearched productsResearched = productsTable.get(indexRow);
						
						if (productsResearched.isSelect()) {
							productsResearched.setSelect(false);
						} else {
							productsResearched.setSelect(true);							
						}
						productsTable.set(indexRow, productsResearched);
						AbstractTableModel model = (AbstractTableModel)getTableProducts().getModel();
						model.fireTableDataChanged();
					}
					
				}
			});

			
		}
		return tableProducts;
	}

	class ModelTableProducts extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return columnsTableProducts.length;
		}

		@Override
		public int getRowCount() {
			return productsTable.size();
		}

		@Override
		public Object getValueAt(int row, int column) {

			ProductsResearched productsResearched = productsTable.get(row);
			if (column == 0) {
				return productsResearched.getCode();
			} else if (column == 1) {
				return productsResearched.getDescription();
			} else if (column == 2) {
				return productsResearched.getValue();
			} else if (column == 3) {
				return productsResearched.getQuantity();
			} else if (column == 4) {
				return productsResearched.isSelect();
			}
			
			else {
				return "error";
			}

		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

			if (columnIndex == 3) {
				ProductsResearched productsResearched = productsTable.get(rowIndex);
				
				try {					
					productsResearched.setQuantity(Float.parseFloat((String)aValue));					
				} catch (Exception e) {
					Messages.getMessageErrorFloat();
				}
				
				fireTableCellUpdated(rowIndex, columnIndex);
			}
		}

		
		@Override public Class<?> getColumnClass(int columnIndex) {       
	        if (columnIndex == 4) 
	            return Boolean.class;
	        else 
	            return String.class;
	    }
		
		@Override
		public String getColumnName(int column) {
			return columnsTableProducts[column];
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			if (column == 3) {
				return true;
			}
			return false;
		}
	}
	
	private JPanel getPanelButtons() {
		if (panelButtons == null) {
			panelButtons = new JPanel();
			
			panelButtons.add(getButtonSave());
			panelButtons.add(getButtonCancel());
			
			
		}
		return panelButtons;
	}

	private JButton getButtonSave() {
		if (buttonSave == null) {
			buttonSave = new JButton(LanguageResources.getResource("save"));

			buttonSave.addActionListener(new ActionListener() {

				boolean result;
				
				@Override
				public void actionPerformed(ActionEvent arg0) {

					
					if (Messages.getMessageSaveRegistration()) {
						
						boolean fieldsOk = true;
						
						SituationTO situationSelected = (SituationTO) comboBoxSituations.getSelectedItem();
						
						
						if (fieldDatePrevision.getText().isEmpty() ||
							fieldDescription.getText().isEmpty() || situationSelected == null) {
							
							Messages.getMessageFieldsIsEmpty();
							fieldsOk = false;
							
						} else if (productsTable.size() == 0) {
							Messages.getMessageNoItemInsered();
							fieldsOk = false;
						}
						
						for (ProductsResearched productsResearched : productsTable) {
							
							if (productsResearched.getQuantity() == null) {
								Messages.getMessageQuantityEmpty(productsResearched.getDescription());
								fieldsOk = false;
							}
						}
						
						if (fieldsOk) {
							
							deliveryTO.setIdSituation(situationSelected.getId());
							
							if (deliveryTO.getDateCreateString() == "") {
								deliveryTO.setDateCreate(simpleDateFormat.format(date));
							}
							deliveryTO.setDatePrevision(fieldDatePrevision.getText());
							
							if (fieldDateDelivery.getText().equals("__/__/____")) {
								deliveryTO.setDateDelivery("");
							} else {
								deliveryTO.setDateDelivery(fieldDateDelivery.getText());								
							}
							deliveryTO.setDescription(fieldDescription.getText());
							
							Float total = 0f;
							for (ProductsResearched productsResearched : productsTable) {
								total = ((productsResearched.getQuantity() * productsResearched.getValue()) + total);
							}
							
							deliveryTO.setValue(total);
							
							deliveryTO = deliverysActions.actionButtonSaveFrameRegistrationDelivery(deliveryTO);
							
							result = deliverysActions.actionButtonSaveFrameRegistrationDelivery(deliveryTO, productsTable);
							
							if (result) {
								Messages.getMessageDeliveryRegisteredSuccessfully();
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
						deliverysActions.actionButtonCancelFrameRegistrationDelivery();
					}
					
				}
			});
		}
		return buttonCancel;
	}
}
