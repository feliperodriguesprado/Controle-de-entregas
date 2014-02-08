package controle_de_entrega.controller;

import java.util.ArrayList;
import java.util.List;

import controle_de_entrega.model.dao.ProductsDAO;
import controle_de_entrega.model.to.ProductsTO;
import controle_de_entrega.view.FrameListProducts;
import controle_de_entrega.view.FrameRegisterProduct;
import controle_de_entrega.view.listener.ProductsListener;

public class ProductsController implements ProductsListener {

	private FrameListProducts frListProduct;
	private FrameRegisterProduct frRegisterProduct;
	private ProductsDAO productsDAO;
	private List<ProductsTO> listProducts;


	public void initialize() {

		frListProduct = new FrameListProducts(this);
		frListProduct.setVisible(true);

	}

	@Override
	public void actionButtonNewFrameListProduct() {
		frRegisterProduct = new FrameRegisterProduct(this, new ProductsTO());
		frRegisterProduct.setVisible(true);

	}

	@Override
	public void actionButtonBackFrameListProduct() {
		frRegisterProduct.dispose();
	}

	@Override
	public boolean actionButtonSaveFrameRegistrationProduct(
			ProductsTO productsTO) {

		productsDAO = new ProductsDAO();

		Integer id = productsDAO.checkRegistrationProductNewUpdate(productsTO);

		if (id == null) {

			boolean result = productsDAO.save(productsTO);

			if (result) {
				frRegisterProduct.dispose();
				return result;
			} else {
				return result;
			}

		} else {

			productsTO.setId(id);
			boolean result = productsDAO.update(productsTO);

			if (result) {
				frRegisterProduct.dispose();
				return result;
			} else {
				return result;
			}
		}
	}
	@Override
	public List<ProductsTO> getListProducts() {
		productsDAO = new ProductsDAO();
		listProducts = new ArrayList<ProductsTO>();
		listProducts = productsDAO.getAllActive();
		return listProducts;
	}

	@Override
	public void actionButtonRemoveFrameRegistrationProduct(ProductsTO productsTO) {
		productsDAO = new ProductsDAO();
		productsDAO.remove(productsTO);
	}

	@Override
	public void actionButtonEditFrameListProduct(ProductsTO productsTO) {
		frRegisterProduct = new FrameRegisterProduct(this, productsTO);
		frRegisterProduct.setVisible(true);		
	}

}
