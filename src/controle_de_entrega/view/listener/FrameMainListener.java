package controle_de_entrega.view.listener;

import java.util.Date;
import java.util.List;

import controle_de_entrega.model.to.DeliveryTO;
import controle_de_entrega.model.to.NumberDeliveriesForProducts;
import controle_de_entrega.model.to.ProductReportDelivery;
import controle_de_entrega.model.to.SituationTO;

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