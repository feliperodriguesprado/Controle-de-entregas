package controle_de_entrega.model.bi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controle_de_entrega.controller.ConnectionDatabase;
import controle_de_entrega.model.to.DeliveryTO;
import controle_de_entrega.model.to.ProductsResearched;

public class ProductBI {

	private Connection connection;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public ProductBI() {
		connection = ConnectionDatabase.createConnection();
	}

	public List<ProductsResearched> getProductSearch(String search) {

		List<ProductsResearched> products = new ArrayList<ProductsResearched>();

		String sql = "select id, codigo, descricao, valor from produtos where (descricao like ? or codigo like ?) and ativo = true";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setString(2, "%" + search + "%");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ProductsResearched productsResearched = new ProductsResearched();
				productsResearched.setId(resultSet.getInt(1));
				productsResearched.setCode(resultSet.getString(2));
				productsResearched.setDescription(resultSet.getString(3));
				productsResearched.setValue(resultSet.getFloat(4));
				productsResearched.setQuantity(null);
				productsResearched.setSelect(false);
				
				products.add(productsResearched);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	public List<ProductsResearched> getProductsDelivery(DeliveryTO deliveryTO) {
		
		List<ProductsResearched> products = new ArrayList<ProductsResearched>();
		
		String sql = "select p.id, p.codigo, p.descricao, p.valor, e.quantidade from produtos p join produtosxentregas e on e.idproduto = p.id where e.codigoentrega = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, deliveryTO.getCode());
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				ProductsResearched productsResearched = new ProductsResearched();
				productsResearched.setId(resultSet.getInt(1));
				productsResearched.setCode(resultSet.getString(2));
				productsResearched.setDescription(resultSet.getString(3));
				productsResearched.setValue(resultSet.getFloat(4));
				productsResearched.setQuantity(resultSet.getFloat(5));
				productsResearched.setSelect(false);
				
				products.add(productsResearched);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
}
