package controledeentregas.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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
import controledeentregas.model.dao.ProductsDAO;
import controledeentregas.model.to.ProductsTO;
import controledeentregas.view.listener.ProductsListener;

public class FrameListProducts extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton btBack, btRegister, btRemove, btEdit;
	private JPanel pnButton, panelListProducts;
	ProductsListener productsAction;
	private JScrollPane scroll;
	private List<ProductsTO> products = new ArrayList<ProductsTO>();
	private JTable table;
	private String[] columns = new String[] {LanguageResources.getResource("code"), LanguageResources
			.getResource("products"), LanguageResources.getResource("value") };

	public FrameListProducts(ProductsListener listener) {
		this.productsAction = listener;
		initFrame();
	}

	public void initFrame() {
		setSize(600, 400);
		setLocationRelativeTo(null);
		setTitle(LanguageResources.getResource("products"));
		setModal(true);
		Container panel = getContentPane();
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		panel.add(getScroll(), BorderLayout.NORTH);
		panel.add(getPanelListProducts(), BorderLayout.CENTER);
		panel.add(getPnBotao(), BorderLayout.SOUTH);

		ProductsDAO d = new ProductsDAO();
		products = d.getAllActive();
		
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

	public JButton getRegister() {
		if (btRegister == null) {
			btRegister = new JButton(LanguageResources.getResource("new"));

			ActionListener listener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					productsAction.actionButtonNewFrameListProduct();
					AbstractTableModel model = (AbstractTableModel) getTable()
							.getModel();
					getListAllProducts();
					model.fireTableDataChanged();
				}
			};
			btRegister.addActionListener(listener);
		}
		return btRegister;
	}

	public JButton getBack() {
		if (btBack == null) {
			btBack = new JButton(LanguageResources.getResource("return"));
			ActionListener listener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (Messages.getMessageOptionClose()) {
						dispose();
					} else {
						setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					}
				}
			};
			btBack.addActionListener(listener);
		}
		return btBack;
	}

	public JButton getEdit() {
		if (btEdit == null) {
			btEdit = new JButton(LanguageResources.getResource("edit"));

			btEdit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					int index = getTable().getSelectedRow();

					if (index == -1) {
						JOptionPane.showMessageDialog(null, LanguageResources
								.getResource("no_item_selected"));
					} else {

						ProductsTO productsTO = products.get(index);
						productsAction
								.actionButtonEditFrameListProduct(productsTO);
						getListAllProducts();
						AbstractTableModel model = (AbstractTableModel) getTable()
								.getModel();
						model.fireTableDataChanged();
					}
				}
			});
		}
		return btEdit;
	}

	public JButton getRemove() {
		if (btRemove == null) {
			btRemove = new JButton(LanguageResources.getResource("remove"));
			btRemove.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					int i = getTable().getSelectedRow();

					if (i == -1) {
						JOptionPane.showMessageDialog(null, LanguageResources
								.getResource("no_item_selected"));
					} else {

						ProductsTO productsTO = products.get(i);

						if (Messages.getMessageRemoveRegistration(productsTO
								.getDescription())) {

							productsAction
									.actionButtonRemoveFrameRegistrationProduct(productsTO);
							AbstractTableModel model = (AbstractTableModel) getTable()
									.getModel();
							getListAllProducts();
							model.fireTableDataChanged();
						}
					}
				}
			});
		}
		return btRemove;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new Model());
			table.setPreferredScrollableViewportSize(new Dimension(500, 200));
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
						ProductsTO productsTO = products.get(index);

						productsAction
								.actionButtonEditFrameListProduct(productsTO);
						getListAllProducts();
						AbstractTableModel model = (AbstractTableModel) getTable()
								.getModel();
						model.fireTableRowsUpdated(0,
								getTable().getRowCount() - 1);
						getTable().setModel(model);
					}
				}
			});

		}
		return table;
	}

	protected void getListAllProducts() {
		this.products = productsAction.getListProducts();

	}

	class Model extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public int getRowCount() {
			return products.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			ProductsTO productsTO = products.get(row);
			if (column == 0) {
				return productsTO.getCod();
			} else if (column == 1) {
				return productsTO.getDescription();
				
			} else if (column == 2) {
				return productsTO.getValue();
				
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

	public JPanel getPnBotao() {
		if (pnButton == null) {
			pnButton = new JPanel();
			pnButton.add(getRegister());
			pnButton.add(getEdit());
			pnButton.add(getRemove());
			pnButton.add(getBack());

		}
		return pnButton;
	}

	private JPanel getPanelListProducts() {
		if (panelListProducts == null) {
			panelListProducts = new JPanel();
			panelListProducts.add(getScroll());
		}
		return panelListProducts;
	}

	private JScrollPane getScroll() {
		if (scroll == null) {
			scroll = new JScrollPane(getTable());
		}
		return scroll;
	}
}