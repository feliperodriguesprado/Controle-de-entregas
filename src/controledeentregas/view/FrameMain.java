package controledeentregas.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controledeentregas.controller.LanguageResources;
import controledeentregas.view.listener.FrameMainListener;


public class FrameMain extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuBar bar;
	private JMenu register, deliverys, menuReports;
	private JMenuItem manageDelivery;
	private JMenuItem itemReportDeliveries;
	private JMenuItem itemReportNumberDeliveriesForProducts;
	private FrameMainListener menuListener;
	
	public FrameMain(FrameMainListener listener) {
		this.menuListener = listener;
		initFrame();
	}

	public void initFrame() {
		setExtendedState(MAXIMIZED_BOTH);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(LanguageResources.getResource("delivery_control"));
		Container panel = getContentPane();
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		setJMenuBar(getBar());
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (Messages.getMessageOptionCloseSystem()) {
					dispose();
				} else {
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}

	public JMenuBar getBar() {
		if (bar == null) {
			bar = new JMenuBar();
			bar.add(getRegister());
			bar.add(getDelivery());
			bar.add(getMenuReports());
		}
		return bar;
	}

	public JMenu getRegister() {
		if (register == null) {
			register = new JMenu(LanguageResources.getResource("registrations"));
			JMenuItem opRegisterSituation = new JMenuItem(LanguageResources.getResource("situations"));
			JMenuItem opRegisterProduct = new JMenuItem(LanguageResources.getResource("products"));
			opRegisterProduct.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					menuListener.frameListProduct();
				}
			});
			opRegisterSituation.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					menuListener.frameListSituations();
				}
			});
			register.add(opRegisterProduct);
			register.add(opRegisterSituation);
		}
		return register;
	}

	public JMenu getDelivery() {
		if (deliverys == null) {
			deliverys = new JMenu(LanguageResources.getResource("deliveries"));
			deliverys.add(getManageDelivery());
		}
		return deliverys;
	}

	public JMenuItem getManageDelivery() {
		if (manageDelivery == null) {
			manageDelivery = new JMenuItem(LanguageResources.getResource("manage_deliveries"));
			manageDelivery.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					menuListener.frameListDeliverys();
				}
			});
		}
		return manageDelivery;
	}
	
	private JMenu getMenuReports() {
		if (menuReports == null) {
			menuReports = new JMenu(LanguageResources.getResource("reports"));
			menuReports.add(getItemReportDeliveries());
			menuReports.add(getItemReportNumberDeliveriesForProducts());
		}
		return menuReports;
	}
	
	private JMenuItem getItemReportDeliveries() {
		if (itemReportDeliveries == null) {
			itemReportDeliveries = new JMenuItem(LanguageResources.getResource("deliveries_for_situation"));
			itemReportDeliveries.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					menuListener.frameReportDeliveries();
				}
			});
		}
		return itemReportDeliveries;
	}

	public JMenuItem getItemReportNumberDeliveriesForProducts() {
		if (itemReportNumberDeliveriesForProducts == null) {
			itemReportNumberDeliveriesForProducts = new JMenuItem(LanguageResources.getResource("number_deliveries_for_products"));
			
			itemReportNumberDeliveriesForProducts.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					menuListener.frameReportNumberDeliveriesForProducts();
				}
			});
		}
		return itemReportNumberDeliveriesForProducts;
	}
}