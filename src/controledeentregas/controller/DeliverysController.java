package controledeentregas.controller;

import java.util.List;

import controledeentregas.model.bi.DeliveryBI;
import controledeentregas.model.bi.ProductBI;
import controledeentregas.model.dao.DeliveryDAO;
import controledeentregas.model.dao.SituationDAO;
import controledeentregas.model.to.Delivery;
import controledeentregas.model.to.DeliveryTO;
import controledeentregas.model.to.ProductsResearched;
import controledeentregas.model.to.SituationTO;
import controledeentregas.view.FrameListDelivery;
import controledeentregas.view.FrameRegistrationDelivery;
import controledeentregas.view.listener.DeliverysActions;

public class DeliverysController implements DeliverysActions {

	private SituationDAO situationDAO;
	private DeliveryDAO deliveryDAO;
	private DeliveryBI deliveryBI;
	private ProductBI productBI;
	private List<ProductsResearched> productsResearched;
	private List<ProductsResearched> productsDelivery;
	
	private FrameRegistrationDelivery frameRegistrationDelivery;
	
	public void initialize() {
		FrameListDelivery frameListDelivery = new FrameListDelivery(this);
		frameListDelivery.setVisible(true);
	}

	@Override
	public List<SituationTO> getAllSituatiosActive() {
		situationDAO = new SituationDAO();
		return situationDAO.getAllActive();
	}

	@Override
	public List<Delivery> getAllListDeliveries() {
		deliveryBI = new DeliveryBI();
		return deliveryBI.getAllDelivery();
	}

	@Override
	public void actionButtonNewFramListDelivery() {
		frameRegistrationDelivery = new FrameRegistrationDelivery(this, new DeliveryTO());
		frameRegistrationDelivery.setVisible(true);
	}
	
	@Override
	public void actionButtonEditFrameListDelivery(DeliveryTO deliveryTO) {
		frameRegistrationDelivery = new FrameRegistrationDelivery(this, deliveryTO);
		frameRegistrationDelivery.setVisible(true);
	}
	
	@Override
	public void actionButtonRemoveFrameListDelivery(DeliveryTO deliveryTO) {
		deliveryDAO = new DeliveryDAO();
		deliveryDAO.removeDelivery(deliveryTO);
	}
	
	@Override
	public List<ProductsResearched> getProductResearched(String search) {
		productBI = new ProductBI();
		productsResearched = productBI.getProductSearch(search);
		return productsResearched;
	}
	
	@Override
	public List<ProductsResearched> getProductsDelivery(DeliveryTO deliveryTO) {
		productBI = new ProductBI();
		productsDelivery = productBI.getProductsDelivery(deliveryTO); 
		return productsDelivery;
	}
	
	@Override
	public DeliveryTO actionButtonSaveFrameRegistrationDelivery(DeliveryTO deliveryTO) {
		
		deliveryBI = new DeliveryBI();
		deliveryTO = deliveryBI.SaveDelivery(deliveryTO);
		return deliveryTO;
	}
	
	@Override
	public boolean actionButtonSaveFrameRegistrationDelivery(DeliveryTO deliveryTO, List<ProductsResearched> productsTable) {
		
		boolean result = false;
		
		deliveryBI = new DeliveryBI();
		result = deliveryBI.SaveDelivery(deliveryTO, productsTable);
		
		if (result) {
			frameRegistrationDelivery.dispose();
			return result;
		} else {
			return result;
		}
	
	}
	
	@Override
	public void actionButtonCancelFrameRegistrationDelivery() {
		frameRegistrationDelivery.dispose();
		
	}
	
}
