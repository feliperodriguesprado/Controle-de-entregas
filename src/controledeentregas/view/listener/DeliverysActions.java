package controledeentregas.view.listener;

import java.util.List;

import controledeentregas.model.to.Delivery;
import controledeentregas.model.to.DeliveryTO;
import controledeentregas.model.to.ProductsResearched;
import controledeentregas.model.to.SituationTO;

public interface DeliverysActions {

	public List<SituationTO> getAllSituatiosActive();

	public List<Delivery> getAllListDeliveries();

	public void actionButtonNewFramListDelivery();
	
	public void actionButtonEditFrameListDelivery(DeliveryTO deliveryTO);
	
	public void actionButtonRemoveFrameListDelivery(DeliveryTO deliveryTO);
	
	public List<ProductsResearched> getProductResearched(String search);
	
	public List<ProductsResearched> getProductsDelivery(DeliveryTO deliveryTO);
	
	public DeliveryTO actionButtonSaveFrameRegistrationDelivery(DeliveryTO deliveryTO);
	
	public boolean actionButtonSaveFrameRegistrationDelivery(DeliveryTO deliveryTO, List<ProductsResearched> productsTable);
	
	public void actionButtonCancelFrameRegistrationDelivery();

}
