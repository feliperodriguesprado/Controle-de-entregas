package controle_de_entrega.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controle_de_entrega.controller.ConnectionDatabase;
import controle_de_entrega.model.to.DeliveryTO;
import controle_de_entrega.model.to.ProductsResearched;

public class DeliveryDAO {

	private Connection connection;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public DeliveryDAO() {
		this.connection = ConnectionDatabase.createConnection();
	}

	public DeliveryTO saveDelivery(DeliveryTO deliveryTO) {		
		String sql = "insert into entregas (idsituacao, datacriacao, dataprevista, dataentrega, descricao, valor) values (?, ?, ?, ?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, deliveryTO.getIdSituation());			
			preparedStatement.setDate(2, new Date(deliveryTO.getDateCreate().getTime()));	// OU preparedStatement.setTimestamp(3, new Timestamp(to.getDateCreate().getTime()));
																							// QUANDO ENVOLVER HORA.
			preparedStatement.setDate(3, new Date(deliveryTO.getDatePrevision().getTime()));
			if (deliveryTO.getDateDelivery() == null) {
				preparedStatement.setDate(4, null);
			} else {
				preparedStatement.setDate(4, new Date(deliveryTO.getDateDelivery().getTime()));				
			}
			preparedStatement.setString(5, deliveryTO.getDescription());
			preparedStatement.setFloat(6, deliveryTO.getValue());
			preparedStatement.execute();
			sql = "select codigo from entregas order by codigo desc limit 1";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				deliveryTO.setCode(resultSet.getInt(1));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return deliveryTO;
		}
		return deliveryTO;
	}
	
	public void removeDelivery(DeliveryTO deliveryTO) {
		String sql = "delete from entregas where codigo = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, deliveryTO.getCode());
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean saveProductDelivery(DeliveryTO deliveryTO, ProductsResearched productsResearched) {
		String sql = "insert into produtosxentregas (codigoentrega, idproduto, quantidade) values (?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, deliveryTO.getCode());
			preparedStatement.setInt(2, productsResearched.getId());
			preparedStatement.setFloat(3, productsResearched.getQuantity());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void updateDelivery(DeliveryTO deliveryTO) {
		String sql = "update entregas set idsituacao = ?, datacriacao = ?, dataprevista = ?, dataentrega = ?, descricao = ?, valor = ? where codigo = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, deliveryTO.getIdSituation());			
			preparedStatement.setDate(2, new Date(deliveryTO.getDateCreate().getTime()));	// OU preparedStatement.setTimestamp(3, new Timestamp(to.getDateCreate().getTime()));
																							// QUANDO ENVOLVER HORA.
			preparedStatement.setDate(3, new Date(deliveryTO.getDatePrevision().getTime()));
			if (deliveryTO.getDateDelivery() == null) {
				preparedStatement.setDate(4, null);
			} else {
				preparedStatement.setDate(4, new Date(deliveryTO.getDateDelivery().getTime()));				
			}
			preparedStatement.setString(5, deliveryTO.getDescription());
			preparedStatement.setFloat(6, deliveryTO.getValue());
			preparedStatement.setInt(7, deliveryTO.getCode());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeProductsDelivery(DeliveryTO deliveryTO) {
		String sql = "delete from produtosxentregas where codigoentrega = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, deliveryTO.getCode());
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
