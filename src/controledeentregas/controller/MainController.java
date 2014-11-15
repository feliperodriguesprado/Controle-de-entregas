package controledeentregas.controller;

import java.util.Date;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.SkyGreen;

import controledeentregas.model.bi.DeliveryBI;
import controledeentregas.model.dao.SituationDAO;
import controledeentregas.model.to.DeliveryTO;
import controledeentregas.model.to.NumberDeliveriesForProducts;
import controledeentregas.model.to.ProductReportDelivery;
import controledeentregas.model.to.SituationTO;
import controledeentregas.view.FrameMain;
import controledeentregas.view.FrameReportDeliveries;
import controledeentregas.view.FrameReportNumberDeliveriesForProducts;
import controledeentregas.view.listener.FrameMainListener;

public class MainController implements FrameMainListener {

	private SituationDAO situationDAO;
	private DeliveryBI deliveryBI;
	private FrameMain frMain;
	private ProductsController productsController;
	private SituationsController frameListCadastreSituationsController;
	private DeliverysController deliverysController;
	private FrameReportDeliveries frameReportDeliveries;
	private FrameReportNumberDeliveriesForProducts frameReportNumberDeliveriesForProducts;

	public void initialize() {
		
		/******************************************************************************
		 * TEMAS DO JGOODIES:
		 * 
		 * Sky Yellow, Sky Red, Sky Pink, Sky Krupp, Sky Green, Sky Bluer, Sky
		 * Blue, Silver, Light Gray, Experience Royale, Experience Green,
		 * Experience Blue, Desert Yellow, Desert Red, Desert Green, Desert
		 * Bluer, Desert Blue, Dark Star e Brown Sugar
		 ******************************************************************************/
		PlasticXPLookAndFeel.setPlasticTheme(new SkyGreen());
		try {
			UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UIManager.put("OptionPane.yesButtonText", LanguageResources.getResource("yes"));
		UIManager.put("OptionPane.noButtonText", LanguageResources.getResource("no"));
		UIManager.put("OptionPane.cancelButtonText", LanguageResources.getResource("cancel"));
		
		frMain = new FrameMain(this);
		frMain.setVisible(true);
	}

	@Override
	public void frameListProduct() {
		productsController = new ProductsController();
		productsController.initialize();

	}

	@Override
	public void frameListSituations() {
		frameListCadastreSituationsController = new SituationsController();
		frameListCadastreSituationsController.initialize();
	}

	@Override
	public void frameListDeliverys() {
		deliverysController = new DeliverysController();
		deliverysController.initialize();
	}
	
	@Override
	public void frameReportDeliveries() {
		frameReportDeliveries = new FrameReportDeliveries(this);
		frameReportDeliveries.setVisible(true);
	}
	
	@Override
	public void frameReportNumberDeliveriesForProducts() {
		frameReportNumberDeliveriesForProducts = new FrameReportNumberDeliveriesForProducts(this);
		frameReportNumberDeliveriesForProducts.setVisible(true);
	}
	
	@Override
	public List<SituationTO> getAllSituatiosActive() {
		situationDAO = new SituationDAO();
		return situationDAO.getAllActive();	
	}
	
	@Override
	public List<DeliveryTO> getReportDeliveriesForSituations(Date dateInitial, Date dateFinal, int situation) {
		deliveryBI = new DeliveryBI();
		return deliveryBI.ReportDeliveriesForSituation(dateInitial, dateFinal, situation);
	}
	
	@Override
	public List<ProductReportDelivery> getReportProdutctsDeliveriesForSituations(int codeDelivery) {
		deliveryBI = new DeliveryBI();
		return deliveryBI.ReportItemsDeliveriesForSituation(codeDelivery);
	}
	
	@Override
	public List<NumberDeliveriesForProducts> getNumberDeliveriesForProducts(Date dateInitial, Date dateFinal, int situation) {
		deliveryBI = new DeliveryBI();
		return deliveryBI.ReportNumberDeliveriesForProducts(dateInitial, dateFinal, situation);
	}
}
