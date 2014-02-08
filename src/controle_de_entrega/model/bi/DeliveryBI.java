package controle_de_entrega.model.bi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controle_de_entrega.controller.ConnectionDatabase;
import controle_de_entrega.model.dao.DeliveryDAO;
import controle_de_entrega.model.to.Delivery;
import controle_de_entrega.model.to.DeliveryTO;
import controle_de_entrega.model.to.NumberDeliveriesForProducts;
import controle_de_entrega.model.to.ProductReportDelivery;
import controle_de_entrega.model.to.ProductsResearched;

public class DeliveryBI {

	private Connection connection;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	DeliveryDAO deliveryDAO;
	
	public DeliveryBI() {
		connection = ConnectionDatabase.createConnection();
	}
	
	public List<DeliveryTO> getAlldeliveryTO() {
		
		List<DeliveryTO> deliveries = new ArrayList<DeliveryTO>();
		String sql = "select codigo, "
						  + "idsituacao, "
						  + "datacriacao, "
						  + "dataprevista, "
						  + "dataentrega, "
						  + "descricao, "
						  + "valor "
					+ "from entregas "
					+ "order by datacriacao, codigo";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DeliveryTO deliveryTO = new DeliveryTO();
				deliveryTO.setCode(resultSet.getInt(1));
				deliveryTO.setIdSituation(resultSet.getInt(2));
				deliveryTO.setDateCreate(new java.util.Date(resultSet.getDate(3).getTime()));
				deliveryTO.setDatePrevision(new java.util.Date(resultSet.getDate(4).getTime()));
				if (resultSet.getString(5) != null) {
					deliveryTO.setDateDelivery(new java.util.Date(resultSet.getDate(5).getTime()));
				}
				deliveryTO.setDescription(resultSet.getString(6));
				deliveryTO.setValue(resultSet.getFloat(7));
				
				deliveries.add(deliveryTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deliveries;
	}
	
	public List<Delivery> getAllDelivery() {
		
		List<Delivery> deliveries = new ArrayList<Delivery>();
		String sql = "select e.codigo, "
				  		  + "e.datacriacao, "
				  		  + "e.dataprevista, "
				  		  + "e.dataentrega, "
				  		  + "e.descricao, "
				  		  + "e.valor, "
				  		  + "s.descricao, "
				  		  + "s.id "
				  	+ "from entregas e "
				  	+ "join situacoes s on s.id = e.idsituacao "
				  	+ "order by datacriacao, codigo";
	
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Delivery delivery = new Delivery();
				delivery.setCode(resultSet.getInt(1));
				delivery.setDateCreate(new Date(resultSet.getDate(2).getTime()));
				delivery.setDatePrevision(new Date(resultSet.getDate(3).getTime()));
				if (resultSet.getString(4) != null) {
					delivery.setDateDelivery(new Date(resultSet.getDate(4).getTime()));
				}
				delivery.setDescription(resultSet.getString(5));
				delivery.setValue(resultSet.getFloat(6));
				delivery.setSituation(resultSet.getString(7));
				delivery.setIdSituation(resultSet.getInt(8));

				deliveries.add(delivery);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deliveries;
		}
	
	public DeliveryTO SaveDelivery(DeliveryTO deliveryTO) {
		deliveryDAO = new DeliveryDAO();
		if (deliveryTO.getCode() == null) {
			deliveryTO = deliveryDAO.saveDelivery(deliveryTO);
		} else {
			String sql = "select codigo from entregas where codigo = ?";
			int codigoDelivery = 0;
			
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, deliveryTO.getCode());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					codigoDelivery = resultSet.getInt(1);
				}
				if (codigoDelivery == deliveryTO.getCode()) {
					deliveryDAO.updateDelivery(deliveryTO);					
					return deliveryTO;
				} else {
					deliveryTO.setCode(null);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return deliveryTO;
	}
	
	public boolean SaveDelivery(DeliveryTO deliveryTO, List<ProductsResearched> productsTable) {
		deliveryDAO = new DeliveryDAO();
		boolean result = false;
		if (deliveryTO.getCode() != null) {
			String sql = "select p.id from produtosxentregas p where p.codigoentrega = ? order by p.id limit 1";
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, deliveryTO.getCode());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					deliveryDAO.removeProductsDelivery(deliveryTO);
				}
				for (ProductsResearched productsResearched : productsTable) {
					result = deliveryDAO.saveProductDelivery(deliveryTO, productsResearched);
				}
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<DeliveryTO> ReportDeliveriesForSituation(java.util.Date dateInitial, java.util.Date dateFinal, int situation) {
		List<DeliveryTO> deliveries = new ArrayList<DeliveryTO>();
		String sql = "select codigo, datacriacao, dataprevista, dataentrega, descricao, valor from entregas where idsituacao = ? and datacriacao between ? and ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, situation);
			preparedStatement.setDate(2, new Date(dateInitial.getTime()));
			preparedStatement.setDate(3, new Date(dateFinal.getTime()));
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet != null) {
				while (resultSet.next()) {
					DeliveryTO deliveryTO = new DeliveryTO();
					deliveryTO.setCode(resultSet.getInt(1));
					deliveryTO.setDateCreate(new Date(resultSet.getDate(2).getTime()));
					deliveryTO.setDatePrevision(new Date(resultSet.getDate(3).getTime()));
					if (resultSet.getString(4) != null) {
						deliveryTO.setDateDelivery(new Date(resultSet.getDate(4).getTime()));
					}
					deliveryTO.setDescription(resultSet.getString(5));
					deliveryTO.setValue(resultSet.getFloat(6));
					deliveries.add(deliveryTO);
				}				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deliveries;
	}

	public List<ProductReportDelivery> ReportItemsDeliveriesForSituation(int codeDelivery) {
		List<ProductReportDelivery> products = new ArrayList<ProductReportDelivery>();
		String sql = "select p.id, p.codigo, p.descricao, p.valor, e.quantidade, (e.quantidade * p.valor) as valortotal from produtosxentregas e join produtos p on p.id = e.idproduto where e.codigoentrega = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, codeDelivery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProductReportDelivery productReportDelivery = new ProductReportDelivery();				
				productReportDelivery.setId(resultSet.getInt(1));
				productReportDelivery.setCode(resultSet.getString(2));
				productReportDelivery.setDescription(resultSet.getString(3));
				productReportDelivery.setValue(resultSet.getFloat(4));
				productReportDelivery.setQuantidy(resultSet.getFloat(5));
				productReportDelivery.setValueTotal(resultSet.getFloat(6));
				products.add(productReportDelivery);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	public List<NumberDeliveriesForProducts> ReportNumberDeliveriesForProducts(java.util.Date dateInitial, java.util.Date dateFinal, int situation) {
		
		List<NumberDeliveriesForProducts> deliveriesForProducts = new ArrayList<NumberDeliveriesForProducts>();
		
		String sql = "select"
					+ " p.codigo,"
					+ " p.descricao,"
					+ " count(e.codigo) as numeroentregas"
				  + " from produtos p"
				  	+ " join produtosxentregas x"
				  		+ " join entregas e"
				  		+ " on e.codigo = x.codigoentrega"
				  	+ " on x.idproduto = p.id"
				  	+ " where e.idsituacao = ?"
				  	+ " and e.datacriacao between ? and ?"
				  	+ " group by p.codigo, p.descricao"
				  	+ " order by p.descricao";	
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, situation);
			preparedStatement.setDate(2, new Date(dateInitial.getTime()));
			preparedStatement.setDate(3, new Date(dateFinal.getTime()));
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				NumberDeliveriesForProducts numberDeliveriesForProducts = new NumberDeliveriesForProducts();
				numberDeliveriesForProducts.setCode(resultSet.getString(1));
				numberDeliveriesForProducts.setDescription(resultSet.getString(2));
				numberDeliveriesForProducts.setNumberDeliveries(resultSet.getInt(3));
				
				deliveriesForProducts.add(numberDeliveriesForProducts);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deliveriesForProducts;
	}
}
