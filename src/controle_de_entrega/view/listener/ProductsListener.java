package controle_de_entrega.view.listener;

import java.util.List;

import controle_de_entrega.model.to.ProductsTO;

public interface ProductsListener {

	public void actionButtonBackFrameListProduct();

	public void actionButtonNewFrameListProduct();

	public boolean actionButtonSaveFrameRegistrationProduct(ProductsTO productsTO);
	
	public void actionButtonRemoveFrameRegistrationProduct(ProductsTO productsTO);

	public List<ProductsTO> getListProducts();

	public void actionButtonEditFrameListProduct(ProductsTO productsTO);
	
}
