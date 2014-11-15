package controledeentregas.view.listener;

import java.util.Date;
import java.util.List;

import controledeentregas.model.to.DeliveryTO;
import controledeentregas.model.to.NumberDeliveriesForProducts;
import controledeentregas.model.to.ProductReportDelivery;
import controledeentregas.model.to.SituationTO;

public interface FrameMainListener {
	
	public void frameListProduct();
	
	public void frameListSituations();
	
	public void frameListDeliverys();
	
	public void frameReportDeliveries();
	
	public void frameReportNumberDeliveriesForProducts();
	
	public List<SituationTO> getAllSituatiosActive();
	
	public List<DeliveryTO> getReportDeliveriesForSituations(Date dateInitial, Date dateFinal, int situation);
	
	public List<ProductReportDelivery> getReportProdutctsDeliveriesForSituations(int codeDelivery);
	
	public List<NumberDeliveriesForProducts> getNumberDeliveriesForProducts(Date dateInitial, Date dateFinal, int situation);
	
}