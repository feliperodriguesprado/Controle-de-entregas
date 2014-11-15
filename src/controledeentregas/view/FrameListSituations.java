package controledeentregas.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import controledeentregas.controller.LanguageResources;
import controledeentregas.model.to.SituationTO;
import controledeentregas.view.listener.SituationsActions;

public class FrameListSituations extends JDialog {

	private static final long serialVersionUID = 1L;

	private SituationsActions situationsActions;
	
	private Container panelMain;

	private JPanel panelButtons;
	private JPanel panelListSituations;

	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonRemove;
	private JButton buttonReturn;
	
	private JScrollPane scroll;
	private JTable table;
	private String[] columns = new String[] {LanguageResources.getResource("situation")};
	private List<SituationTO> situations = new ArrayList<SituationTO>();

	public FrameListSituations(SituationsActions situationsActions) {
		this.situationsActions = situationsActions;
		getListAllSituations();
		init();
	}

	private void init() {

		setTitle(LanguageResources.getResource("situations"));
		setSize(550, 330);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);		
		
		panelMain = getContentPane();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		Constraints constraints = new Constraints();
		
		panelMain.setLayout(gridBagLayout);
		
		constraints.setInsets(new Insets(5, 5, 5, 5));
		
		constraints.setAnchor(GridBagConstraints.NORTH);
		constraints.setGridyGridx(0, 0);
		constraints.setGridwidthGridheight(1, 1);
		constraints.setWeightyWeightx(1, 1);
		panelMain.add(getPanelListSituations(), constraints);
		
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

	private void getListAllSituations() {
		this.situations = situationsActions.getListSituations();
	}

	private JPanel getPanelListSituations() {
		if (panelListSituations == null) {
			panelListSituations = new JPanel();
			panelListSituations.add(getScroll());
		}
		return panelListSituations;
	}

	private JScrollPane getScroll() {
		if (scroll == null) {
			scroll = new JScrollPane(getTable());
		}
		return scroll;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new Model());
			table.setPreferredScrollableViewportSize(new Dimension(500, 200));
	        //table.setFillsViewportHeight(true);
			//table.setAutoCreateRowSorter(true);
			
			table.addMouseListener(new MouseListener() {
				
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

						int index = getTable().getSelectedRow();
						SituationTO situationTO = situations.get(index);
						
						situationsActions.actionButtonEditFrameListSituation(situationTO);
						getListAllSituations();
						AbstractTableModel model = (AbstractTableModel)getTable().getModel();
						model.fireTableRowsUpdated(0, getTable().getRowCount() - 1);
						getTable().setModel(model);
					}
				}
			});
			
		}
		return table;
	}

	class Model extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public int getRowCount() {
			return situations.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			SituationTO situationsTO = situations.get(row);
			if (column == 0) {
				return situationsTO.getDescription();
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
					
					
					
					situationsActions.actionButtonNewFrameListSituation();
					getListAllSituations();
					AbstractTableModel model = (AbstractTableModel)getTable().getModel();
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
					
					int index = getTable().getSelectedRow();
					
					if (index == -1) {
						JOptionPane.showMessageDialog(null, LanguageResources.getResource("no_item_selected"));
					} else {
						
						SituationTO situationTO = situations.get(index);
						situationsActions.actionButtonEditFrameListSituation(situationTO);
						getListAllSituations();
						AbstractTableModel model = (AbstractTableModel)getTable().getModel();
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
					
					int i = getTable().getSelectedRow();
					
					if (i == -1) {
						JOptionPane.showMessageDialog(null, LanguageResources.getResource("no_item_selected"));
					} else {
						
						SituationTO situationTO = situations.get(i);
						
						if (Messages.getMessageRemoveRegistration(situationTO.getDescription())) {
							
							situationsActions.actionButtonRemoveFrameRegistrationSituation(situationTO);							
							AbstractTableModel model = (AbstractTableModel)getTable().getModel();
							getListAllSituations();
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
